package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.Contact;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class ContactDaoImpl extends BaseEntityDao<Contact>{

	public Page<Contact> findPageBy(Contact obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class);
		
		if(StringUtils.isNotEmpty(obj.getEmail())){
			dc.add(Restrictions.like("email", obj.getEmail(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getStatus()!= null){
			dc.add(Restrictions.eq("status", obj.getStatus())) ;
		}
		dc.addOrder(Order.desc("createTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
