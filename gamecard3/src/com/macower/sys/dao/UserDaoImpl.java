package com.macower.sys.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.sys.entity.User;

@Repository
public class UserDaoImpl extends BaseEntityDao<User>{

	public Page<User> findPageBy(User obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		
		if(StringUtils.isNotEmpty(obj.getUserName())){
			dc.add(Restrictions.like("userName", obj.getUserName(),MatchMode.ANYWHERE)) ;
		}
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
