package com.macower.sys.dao;


import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.common.core.ApplicationContext;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.sys.entity.Config;

@SuppressWarnings("unchecked")
@Repository
public class ConfigDaoImpl extends BaseEntityDao<Config>{

	public Page<Config> findPageBy(Config obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Config.class);
		
		if(StringUtils.isNotEmpty(obj.getConfigName())){
			dc.add(Restrictions.like("configName", obj.getConfigName(),MatchMode.ANYWHERE)) ;
		}
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	
	
	public Config findFromCacheByConfigCode(String configCode) {
		ServletContext context =ApplicationContext.getInstance() ;
		List<Config> list = (List<Config>)context.getAttribute("configList") ;
		if(list == null){
			list = this.findAll() ;
			context.setAttribute("configList", list) ;
			
		}
		for(Config c :list){
			if(configCode!=null && configCode.equals(c.getConfigCode())){
				return c ;
			}
		}
		return null;
	}
	public Config findByConfigCode(String configCode){
		Config param = new Config() ;
		param.setConfigCode(configCode) ;
		List<Config> list = this.findBy(param) ;
		if(list != null && list.size() > 0){
			return list.get(0) ;
		}
		return null;
	}

}
