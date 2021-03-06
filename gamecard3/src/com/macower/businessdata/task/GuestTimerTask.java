package com.macower.businessdata.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macower.businessdata.biz.TaskBizImpl;
import com.macower.businessdata.util.DBManager;
import com.macower.core.util.EmailUtil;

public class GuestTimerTask extends TimerTask{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private Integer dataRows ;
	
	private Long taskId ;

	private String subject ;
	
	private String emailTpl ;
	
	private String hostEmail ;
	
	private String hostPassword ;
	
	public GuestTimerTask(Integer dataRows,Long taskId,String subject,String emailTpl,String hostEmail ,String hostPassword){
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
					.executeQuery("select count(*) as sun from tb_guest t where t.task_status = 0 and t.status = 0 ");
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
					.executeQuery("select * from tb_guest t where t.task_status = 0 and t.status = 0 limit 0,"
							+ pageSize);
			int taskEmailId = -1;
			String sendEmail = "";
			String taskEmailIds = null;
			EmailUtil emailUtil = new EmailUtil(hostEmail, hostPassword);
			while (rs.next()) {
				taskEmailId = rs.getInt("id");
				sendEmail = rs.getString("email");
				emailUtil.sendGmailEmail(subject, emailTpl, sendEmail);
				if (taskEmailIds == null) {
					taskEmailIds = taskEmailId + "";
				} else {
					taskEmailIds = taskEmailIds + "," + taskEmailId;
				}
			}
			dbManger.close();
			dbManger = new DBManager();
			dbManger.executeUpdate("update tb_guest set task_status = 1 ,send_tm =now() where id in ("
					+ taskEmailIds + ")");

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
