package com.macower.sys.biz;



import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.common.core.ApplicationContext;
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


	@SuppressWarnings("unchecked")
	@Override
	public Config findFromCacheByConfigCode(String configCode) {
		ServletContext context =ApplicationContext.getInstance() ;
		List<Config> list = (List<Config>)context.getAttribute("configList") ;
		if(list == null){
			list = this.configDao.findAll() ;
			context.setAttribute("configList", list) ;
			
		}
		for(Config c :list){
			if(configCode!=null && configCode.equals(c.getConfigCode())){
				return c ;
			}
		}
		return null;
	}
	@Override
	public Config findByConfigCode(String configCode) {
		Config param = new Config() ;
		param.setConfigCode(configCode) ;
		List<Config> list = this.configDao.findBy(param) ;
		if(list != null && list.size() > 0){
			return list.get(0) ;
		}
		return null;
	}

}
