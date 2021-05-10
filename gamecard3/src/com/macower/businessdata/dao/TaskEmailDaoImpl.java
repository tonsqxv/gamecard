package com.macower.businessdata.dao;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.TaskEmail;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class TaskEmailDaoImpl extends BaseEntityDao<TaskEmail>{

	public Page<TaskEmail> findPageBy(TaskEmail obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = gerateDetachedCriteria(obj) ;
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

	private DetachedCriteria gerateDetachedCriteria(TaskEmail obj){
		DetachedCriteria dc = DetachedCriteria.forClass(TaskEmail.class);
		if(StringUtils.isNotEmpty(obj.getEmail())){
			dc.add(Restrictions.like("email", obj.getEmail(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getStatus() != null){
			dc.add(Restrictions.eq("status", obj.getStatus())) ;
		}
		if(obj.getIsMember() != null){
			dc.add(Restrictions.eq("isMember", obj.getIsMember())) ;
		}
		return dc ;
	}
	
	public List<TaskEmail> findBy(TaskEmail obj){
		DetachedCriteria dc = gerateDetachedCriteria(obj) ;
		return this.findBy(dc) ;
	}
}
