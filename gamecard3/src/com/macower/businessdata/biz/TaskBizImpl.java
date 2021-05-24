package com.macower.businessdata.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.DiscodeDaoImpl;
import com.macower.businessdata.dao.TaskDaoImpl;
import com.macower.businessdata.entity.Discode;
import com.macower.businessdata.entity.Task;
import com.macower.businessdata.task.DiscodeGuestTimerTask;
import com.macower.businessdata.task.DiscodeMemberTimerTask;
import com.macower.businessdata.task.DiscodeTaskEmailTimerTask;
import com.macower.businessdata.task.GuestTimerTask;
import com.macower.businessdata.task.MemberTimerTask;
import com.macower.businessdata.task.TaskEmailTimerTask;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.EmailUtil;

@Service
public class TaskBizImpl extends BaseBiz implements TaskBiz {

	public static Map<Long, Timer> tasks = new HashMap<Long, Timer>();

	@Autowired
	private TaskDaoImpl taskDao;
	
	@Autowired
	private DiscodeDaoImpl discodeDao;

	@Override
	public Page<Task> findPageBy(Task obj, Integer pageNo, Integer pageSize) {
		return taskDao.findPageBy(obj, pageNo, pageSize);
	}

	@Override
	public void save(Task obj) {
		obj.setStatus(0); // 新建
		taskDao.save(obj);

	}

	@Override
	public void update(Task obj) {
		Task old = this.taskDao.get(obj.getId());
		if (old == null) {
			throw new BizException("任务已被删除");
		}
		old.setTaskName(obj.getTaskName());
		old.setEmail(obj.getEmail());
		old.setEmailTpl(obj.getEmailTpl());
		old.setDesc(obj.getDesc());
		old.setPassword(obj.getPassword());
		old.setSubject(obj.getSubject());
		old.setReadDataRows(obj.getReadDataRows());
		old.setRunCycle(obj.getRunCycle());
		old.setTaskType(obj.getTaskType());
		this.taskDao.update(old);
	}

	@Override
	public void deletes(String ids) {
		this.taskDao.deleteByIds(ids);
	}

	@Override
	public Task get(Long id) {
		return this.taskDao.get(id);
	}

	@Override
	public void startTask(Long id) {
		Task old = this.taskDao.get(id);
		if (old == null) {
			throw new BizException("任务已被删除");
		}
		if (old.getStatus() != null && old.getStatus() == 1) {
			throw new BizException("任务已启动，不可重复启动");
		}
		Timer timer = new Timer();
		Date d = new Date();
		final Integer dataRows = old.getReadDataRows();
		final Long taskId = old.getId();
		final String subject = old.getSubject();
		final String emailTpl = old.getEmailTpl();
		final String hostEmail = old.getEmail();
		final String hostPassword = old.getPassword();
		TimerTask task = null;

		if (old.getTaskType() == null) {
			throw new BizException("任务类型为空");
		}
		switch (old.getTaskType()) {
		case 1: //目标客户
			task = new TaskEmailTimerTask(dataRows, taskId, subject, emailTpl,
					hostEmail, hostPassword);
			break;
		case 2: //游客
			task = new GuestTimerTask(dataRows, taskId, subject, emailTpl,
					hostEmail, hostPassword);
			break;
		case 3: //会员
			task = new MemberTimerTask(dataRows, taskId, subject, emailTpl,
					hostEmail, hostPassword);
			break;
		case 4: //4:目标客户-折扣码  
			task = new DiscodeTaskEmailTimerTask(dataRows, taskId, subject, emailTpl,
					hostEmail, hostPassword);
			break ;
		case 5: //5：游客-折扣码 
			task = new DiscodeGuestTimerTask(dataRows, taskId, subject, emailTpl,
					hostEmail, hostPassword);
			break ;
		case 6: //6：会员-折扣码
			task = new DiscodeMemberTimerTask(dataRows, taskId, subject, emailTpl,
					hostEmail, hostPassword);
			break ;
			default:
				break ;
		}
		
		timer.schedule(task, d, 1000 * 60 * old.getRunCycle());// 1000*60
																// =1分钟
		if (tasks.containsKey(old.getId())) {
			tasks.remove(old.getId());
		}
		tasks.put(old.getId(), timer);
		// 更新任务状态
		old.setStatus(1); // 启动
		old.setStartTm(new Date());
		this.taskDao.update(old);
	}

	@Override
	public void stopTask(Long id) {
		Task old = this.taskDao.get(id);
		if (old == null) {
			throw new BizException("任务已被删除");
		}
		if (old.getStatus() != 1) {
			throw new BizException("任务尚未启动");
		}
		Timer timer = tasks.get(id);
		// 更新任务状态
		old.setStatus(3); // 手动停止
		old.setStopTm(new Date());
		this.taskDao.update(old);

		if (timer == null) {
			return;
		} else {
			tasks.remove(old.getId());
			timer.cancel();
		}

	}

	@Override
	public void testEmail(Long id, String email) {
		Task old = this.taskDao.get(id);
		if (old == null) {
			throw new BizException("该任务已删除");
		}
		Discode param = new Discode() ;
		param.setStatus(0) ; //
		Page<Discode> page = this.discodeDao.findPageBy(param, 0, 1);
		List<Discode> list = (List<Discode>)page.getData() ;
		if(list == null || list.size() == 0){
			throw new BizException("没有可用的折扣码");
		}
		Discode code = list.get(0) ;
		String emailTpl = old.getEmailTpl() ;
		if(old.getTaskType() == 4 || old.getTaskType() == 5 || old.getTaskType() == 6){
			emailTpl = emailTpl.replace("{discode}", code.getDiscode()) ;
		}
		EmailUtil emailUtil = new EmailUtil(old.getEmail(), old.getPassword());
		emailUtil.sendGmailEmail(old.getSubject(), emailTpl, email);
	}

}
