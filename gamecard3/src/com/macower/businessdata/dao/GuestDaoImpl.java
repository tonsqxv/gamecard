package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.Guest;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class GuestDaoImpl extends BaseEntityDao<Guest>{

	public Page<Guest> findPageBy(Guest obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Guest.class);
		
		if(StringUtils.isNotEmpty(obj.getEmail())){
			dc.add(Restrictions.like("email", obj.getEmail(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getStatus()!= null){
			dc.add(Restrictions.eq("status", obj.getStatus())) ;
		}
		if(obj.getTaskStatus() != null){
			dc.add(Restrictions.eq("taskStatus", obj.getTaskStatus())) ;
		}
		dc.addOrder(Order.desc("createTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}


}
