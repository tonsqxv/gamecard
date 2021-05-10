package com.macower.sys.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.sys.entity.Config;

@Repository
public class ConfigDaoImpl extends BaseEntityDao<Config>{

	public Page<Config> findPageBy(Config obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Config.class);
		
		if(StringUtils.isNotEmpty(obj.getConfigName())){
			dc.add(Restrictions.like("configName", obj.getConfigName(),MatchMode.ANYWHERE)) ;
		}
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
