package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.Member;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class MemberDaoImpl extends BaseEntityDao<Member>{

	public Page<Member> findPageBy(Member obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Member.class);
		
		if(StringUtils.isNotEmpty(obj.getEmail())){
			dc.add(Restrictions.like("email", obj.getEmail(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getLocked() != null){
			dc.add(Restrictions.eq("locked", obj.getLocked())) ;
		}
		if(obj.getTaskStatus() != null){
			dc.add(Restrictions.eq("taskStatus", obj.getTaskStatus())) ;
		}
		dc.addOrder(Order.desc("registerTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

	


}
