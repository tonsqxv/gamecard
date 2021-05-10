package com.macower.businessdata.biz;



import com.macower.businessdata.entity.Task;
import com.macower.core.entity.Page;

public interface TaskBiz {


	public Page<Task> findPageBy(Task obj, Integer pageNo,
			Integer pageSize);
	
	public void save(Task obj);
	
	public void update(Task obj);

	public void deletes(String ids);
	
	public Task get(Long id) ;

	public void startTask(Long id);

	public void stopTask(Long id);

	public void testEmail(Long id, String email);


}
