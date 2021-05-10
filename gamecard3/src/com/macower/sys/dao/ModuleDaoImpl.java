package com.macower.sys.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.sys.entity.Module;

@Repository
public class ModuleDaoImpl extends BaseEntityDao<Module>{

	public Page<Module> findPageBy(Module obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Module.class);
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
