package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.Discode;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class DiscodeDaoImpl extends BaseEntityDao<Discode>{

	public Page<Discode> findPageBy(Discode obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Discode.class);
		
		if(StringUtils.isNotEmpty(obj.getDiscode())){
			dc.add(Restrictions.like("discode", obj.getDiscode(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getStatus()!= null){
			dc.add(Restrictions.eq("status", obj.getStatus())) ;
		}
		dc.addOrder(Order.desc("createTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
