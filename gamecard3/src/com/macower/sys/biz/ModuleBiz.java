package com.macower.sys.biz;


import java.util.List;

import com.macower.core.entity.Page;
import com.macower.sys.entity.Module;
import com.macower.sys.entity.AsyncTree;

public interface ModuleBiz {


	public Page<Module> findPageBy(Module obj, Integer pageNo,
			Integer pageSize);
	
	public List<Module> findBy(Module obj) ;

	public List<AsyncTree> findByParentModuleId(Long id);


}
