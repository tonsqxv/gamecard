package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.Task;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class TaskDaoImpl extends BaseEntityDao<Task>{

	public Page<Task> findPageBy(Task obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Task.class);
		if(StringUtils.isNotEmpty(obj.getTaskName())){
			dc.add(Restrictions.like("taskName", obj.getTaskName().trim(),MatchMode.ANYWHERE)) ;
		}
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

}
