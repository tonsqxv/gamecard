package com.macower.sys.biz;


import java.util.List;

import com.macower.core.entity.Page;
import com.macower.sys.entity.Role;
import com.macower.sys.entity.SyncTree;

public interface RoleBiz {


	public Page<Role> findPageBy(Role obj, Integer pageNo,
			Integer pageSize);
	
	public List<Role> findBy(Role obj) ;

	public void save(Role obj);

	public void update(Role obj,Long[] roleModuleIds);

	public void deletes(String ids);
	
	public Role get(Long id) ;
	
	public List<SyncTree> findRoleModulesByModuleAndRole(Long roleId) ;


}
