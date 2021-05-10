package com.macower.basedata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.AddressLog;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;

@Repository
public class AddressLogDaoImpl extends BaseEntityDao<AddressLog>{

	public Page<AddressLog> findPageBy(AddressLog obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(AddressLog.class);
		
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
