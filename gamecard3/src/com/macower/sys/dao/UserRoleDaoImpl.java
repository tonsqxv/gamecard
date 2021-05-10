package com.macower.sys.dao;



import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.sys.entity.Role;
import com.macower.sys.entity.UserRole;

@Repository
public class UserRoleDaoImpl extends BaseEntityDao<UserRole>{

	public Page<UserRole> findPageBy(UserRole obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserRole.class);
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findNotAssignRoleByUserId(Long userId) {
		 SQLQuery query = this.createSQLQuery("select t.id , t.role_name roleName  from ts_role t where t.id not in (select role_id from ts_user_role where user_id = "+userId+")") ;
		 query.addScalar("id", StandardBasicTypes.LONG) ;
		 query.addScalar("roleName", StandardBasicTypes.STRING) ;
		 query.setResultTransformer(Transformers.aliasToBean(Role.class)) ;
		 List<Role> list =  (List<Role>)query.list() ;
		return list;
	}

	public List<UserRole> findAssignRoleByUserId(Long userId) {
		UserRole param = new UserRole();
		param.setUserId(userId) ;
		return this.findBy(param) ;
	}


}
