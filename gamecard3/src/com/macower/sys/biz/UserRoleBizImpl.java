package com.macower.sys.biz;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.sys.dao.UserRoleDaoImpl;
import com.macower.sys.entity.Role;
import com.macower.sys.entity.UserRole;

@Service
public class UserRoleBizImpl implements UserRoleBiz {
	
	@Autowired
	private UserRoleDaoImpl userRoleDao ;
	

	@Override
	public List<Role> findNotAssignRoleByUserId(Long userId) {
		return this.userRoleDao.findNotAssignRoleByUserId(userId) ;
	}


	@Override
	public List<UserRole> findAssignRoleByUserId(Long userId) {
		return this.userRoleDao.findAssignRoleByUserId(userId) ;
	}
	
	
	



}
