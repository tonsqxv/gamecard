package com.macower.sys.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.sys.entity.RoleModule;

@Repository
public class RoleModuleDaoImpl extends BaseEntityDao<RoleModule>{

	public Page<RoleModule> findPageBy(RoleModule obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(RoleModule.class);
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

	public List<Long> findRoleModuleIds(Long roleId) {
		List<Long> moduleIds = new ArrayList<Long>() ;
		RoleModule param = new RoleModule() ;
		param.setRoleId(roleId) ;
		List<RoleModule> list = this.findBy(param) ;
		if(list ==null || list.size() == 0){
			return moduleIds ;
		}
		for(RoleModule m : list){
			moduleIds.add(m.getModuleId()) ;
		}
		
		return moduleIds;
	}
	


}
