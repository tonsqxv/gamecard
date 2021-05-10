package com.macower.sys.biz;


import java.util.List;

import com.macower.core.entity.Page;
import com.macower.sys.entity.RoleModule;

public interface RoleModuleBiz {


	public Page<RoleModule> findPageBy(RoleModule obj, Integer pageNo,
			Integer pageSize);
	
	public List<RoleModule> findBy(RoleModule obj);

	public void save(RoleModule obj);

	public void deleteBatch(List<RoleModule> entities) ;
	
	public void deletes(List<Long> ids) ;

	public void deletes(String ids);
	
	public RoleModule get(Long id) ;



}
