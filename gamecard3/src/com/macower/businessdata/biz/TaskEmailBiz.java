package com.macower.businessdata.biz;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.macower.businessdata.entity.TaskEmail;
import com.macower.core.entity.Page;

public interface TaskEmailBiz {


	public Page<TaskEmail> findPageBy(TaskEmail obj, Integer pageNo,
			Integer pageSize);
	
	public void save(TaskEmail obj);

	public void deletes(String ids);
	
	public TaskEmail get(Long id) ;

	public void init();

	public void importExcel(HttpServletRequest request);

	public int sycnMemberData();

	public void clear();

	public List<TaskEmail> findBy(TaskEmail obj);



}
