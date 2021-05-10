package com.macower.sys.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.entity.Page;
import com.macower.sys.dao.RoleModuleDaoImpl;
import com.macower.sys.entity.RoleModule;

@Service
public class RoleModuleBizImpl implements RoleModuleBiz {

	@Autowired
	private RoleModuleDaoImpl roleModuleDao;

	@Override
	public Page<RoleModule> findPageBy(RoleModule obj, Integer pageNo, Integer pageSize) {
		return roleModuleDao.findPageBy(obj, pageNo, pageSize);
	}

	@Override
	public List<RoleModule> findBy(RoleModule obj) {
		return roleModuleDao.findBy(obj);
	}

	@Override
	public void save(RoleModule obj) {
		roleModuleDao.save(obj);
	}
	

	@Override
	public void deletes(String ids) {

		this.roleModuleDao.deleteByIds(ids);
	}
	
	@Override
	public void deleteBatch(List<RoleModule> entities) {
		this.roleModuleDao.deleteBatch(entities) ;
	}
	
	@Override
	public void deletes(List<Long> ids) {
		this.roleModuleDao.deleteByIds(ids) ;
	}

	@Override
	public RoleModule get(Long id) {
		return this.roleModuleDao.get(id);
	}


}
