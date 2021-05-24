package com.macower.businessdata.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macower.businessdata.biz.TaskBizImpl;
import com.macower.businessdata.util.DBManager;
import com.macower.core.util.EmailUtil;

public class DiscodeMemberTimerTask extends TimerTask{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private Integer dataRows ;
	
	private Long taskId ;

	private String subject ;
	
	private String emailTpl ;
	
	private String hostEmail ;
	
	private String hostPassword ;
	
	public DiscodeMemberTimerTask(Integer dataRows,Long taskId,String subject,String emailTpl,String hostEmail ,String hostPassword){
		super() ;
		this.dataRows = dataRows ;
		this.taskId = taskId ;
		this.subject = subject ;
		this.emailTpl = emailTpl ;
		this.hostEmail = hostEmail ;
		this.hostPassword = hostPassword ;
		
	}

	@Override
	public void run() {

		try {
			//等待10秒
			Thread.sleep(10000) ;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		DBManager dbManger = new DBManager();
		try {
			// 根据任务配置查找目标客户
			int pageSize = dataRows;
			
			ResultSet rs = dbManger
					.executeQuery("select count(*) as sun from tb_member t where t.task_status = 0 ");
			if (rs.next()) {
				int count = rs.getInt("sun");
				if (count == 0) {
					dbManger.close();
					dbManger = new DBManager();
					dbManger.executeUpdate("update tb_task set status = 2 ,stop_tm = now() where id = "
							+ taskId);
					Timer timer = TaskBizImpl.tasks.get(taskId);
					if (timer != null) {
						TaskBizImpl.tasks.remove(taskId);
						timer.cancel();
					}
					return;
				}
			}
			dbManger.close();
			dbManger = new DBManager();
			rs = dbManger
					.executeQuery("select * from tb_member t where t.task_status = 0 limit 0,"
							+ pageSize);
			String memberId = "";
			String sendEmail = "";
			String memberIds = null;
			EmailUtil emailUtil = new EmailUtil(hostEmail, hostPassword);
			List<Map<String ,String>> list = new ArrayList<Map<String,String>>() ;
			while (rs.next()) {
				Map<String,String> map = new HashMap<String,String>() ;
				memberId = rs.getInt("id")+"";
				sendEmail = rs.getString("email");
				map.put("id", memberId+"") ;
				map.put("email", sendEmail) ;
				list.add(map) ;
			}
			for(Map<String,String> m :list){
				dbManger.close();
				dbManger = new DBManager();
				rs = dbManger
						.executeQuery("select count(*) as sun from tb_discount_code t where t.status = 0");
				if(rs.next()){
					int count = rs.getInt("sun");
					if(count > 0){ //折扣码可用
						dbManger.close();
						dbManger = new DBManager();
						rs = dbManger
						.executeQuery("select * from tb_discount_code t where t.status = 0 limit 0,1"); //只查询一条数据
						if(rs.next()){
							int disocdeId = rs.getInt("id") ;
							String discode = rs.getString("discode") ;
							
							memberId = m.get("id");
							sendEmail = m.get("email");

							emailUtil.sendGmailEmail(subject, emailTpl.replace("{discode}", discode), sendEmail);
							if (memberIds == null) {
								memberIds = memberId + "";
							} else {
								memberIds = memberIds + "," + memberId;
							}
							//发送邮件后更新折扣码状态为未使用
							dbManger.close();
							dbManger = new DBManager();
							dbManger.executeUpdate("update tb_discount_code set status = 1 where id ="+disocdeId); 
						}
					}else{
						log.warn("warn:send discode email:effect discode data not found. ") ;
						//停止任务之前先更新
						dbManger.close();
						dbManger = new DBManager();
						dbManger.executeUpdate("update tb_member set task_status = 1 ,send_tm =now() where id in ("
								+ memberIds + ")");
						//没有折扣码 停止任务   
						dbManger.close();
						dbManger = new DBManager();
						dbManger.executeUpdate("update tb_task set status = 2 ,stop_tm = now() where id = "
								+ taskId);
						Timer timer = TaskBizImpl.tasks.get(taskId);
						if (timer != null) {
							TaskBizImpl.tasks.remove(taskId);
							timer.cancel();
						}
						return;
					}
				}
				
			}
			//更新
			dbManger.close();
			dbManger = new DBManager();
			dbManger.executeUpdate("update tb_member set task_status = 1 ,send_tm =now() where id in ("
					+ memberIds + ")");

		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			Timer timer = TaskBizImpl.tasks.get(taskId);
			if (timer != null) {
				TaskBizImpl.tasks.remove(taskId);
				timer.cancel();
			}
			dbManger.close();
			dbManger = new DBManager();
			dbManger.executeUpdate("update tb_task set status = 4 ,stop_tm = now() where id = "
					+ taskId);
		} finally {
			dbManger.close();

		}
	
		
	}
	
}
