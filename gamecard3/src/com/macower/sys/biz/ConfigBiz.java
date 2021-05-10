package com.macower.sys.biz;


import java.util.List;

import com.macower.core.entity.Page;
import com.macower.sys.entity.Config;

public interface ConfigBiz {


	public Page<Config> findPageBy(Config obj, Integer pageNo,
			Integer pageSize);
	
	public List<Config> findBy(Config obj);

	public void save(Config obj);

	public void update(Config obj);

	public void deletes(String ids);
	
	public Config get(Long id) ;
	
	public Config findFromCacheByConfigCode(String configCode);

	public Config findByConfigCode(String configCode);
}
