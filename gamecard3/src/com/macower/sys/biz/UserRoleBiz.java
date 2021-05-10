package com.macower.sys.biz;


import java.util.List;

import com.macower.sys.entity.Role;
import com.macower.sys.entity.UserRole;


public interface UserRoleBiz {

	public List<Role> findNotAssignRoleByUserId(Long userId);

	public List<UserRole> findAssignRoleByUserId(Long userId);


}
