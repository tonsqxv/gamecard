package com.macower.sys.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.sys.entity.Role;

@Repository
public class RoleDaoImpl extends BaseEntityDao<Role>{

	public Page<Role> findPageBy(Role obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Role.class);
		
		if(StringUtils.isNotEmpty(obj.getRoleName())){
			dc.add(Restrictions.like("roleName", obj.getRoleName(),MatchMode.ANYWHERE)) ;
		}
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
