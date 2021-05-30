package com.macower.sys.biz;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.sys.dao.ConfigDaoImpl;
import com.macower.sys.entity.Config;

@Service
public class ConfigBizImpl implements ConfigBiz {

	@Autowired
	private ConfigDaoImpl configDao ;
	

	@Override
	public Page<Config> findPageBy(Config obj, Integer pageNo,
			Integer pageSize) {
		return configDao.findPageBy(obj, pageNo, pageSize) ;
	}


	@Override
	public void save(Config obj) {
		Config param = new Config() ;
		param.setConfigName(obj.getConfigName().trim()) ;
		
		int count  = this.configDao.countBy(param) ;
		if(count > 0){
			throw new BizException(obj.getConfigName()+"已经存在") ;
		}
		
		param = new Config() ;
		param.setConfigCode(obj.getConfigCode().trim()) ;
		count  = this.configDao.countBy(param) ;
		if(count > 0){
			throw new BizException(obj.getConfigCode()+"已经存在") ;
		}
		
		obj.setConfigCode(obj.getConfigCode().trim()) ;
		obj.setConfigName(obj.getConfigName().trim()) ;
		configDao.save(obj) ;

	}

	@Override
	public void update(Config obj) {
		Config old = configDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		if(!old.getConfigName().equals(obj.getConfigName().trim())){
			Config param = new Config() ;
			param.setConfigName(obj.getConfigName().trim()) ;
			int count  = this.configDao.countBy(param) ;
			if(count > 0){
				throw new BizException(obj.getConfigName()+"已经存在") ;
			}
		}
		if(!old.getConfigCode().equals(obj.getConfigCode().trim())){
			Config param = new Config() ;
			param.setConfigCode(obj.getConfigCode().trim()) ;
			int count  = this.configDao.countBy(param) ;
			if(count > 0){
				throw new BizException(obj.getConfigCode()+"已经存在") ;
			}
		}
		old.setConfigName(obj.getConfigName().trim()) ;
		old.setConfigCode(obj.getConfigCode().trim()) ;
		old.setConfigValue(obj.getConfigValue().trim()) ;
		old.setDesc(obj.getDesc()) ;
		
		configDao.update(old) ;
		

	}

	@Override
	public void deletes(String ids) {
		
		this.configDao.deleteByIds(ids) ;
	}

	@Override
	public Config get(Long id) {
		return this.configDao.get(id) ;
	}


	@Override
	public List<Config> findBy(Config obj) {
		return this.configDao.findBy(obj) ;
	}

	@Override
	public Config findFromCacheByConfigCode(String configCode) {
		return this.configDao.findFromCacheByConfigCode(configCode) ;
	}
	
	@Override
	public Config findByConfigCode(String configCode) {
		return this.configDao.findByConfigCode(configCode) ;
	}

}
