package com.macower.sys.biz;


import java.util.List;

import com.macower.core.entity.Page;
import com.macower.sys.entity.User;

public interface UserBiz {


	public Page<User> findPageBy(User obj, Integer pageNo,
			Integer pageSize);
	
	public List<User> findBy(User obj);

	public void save(User obj);

	public void update(User obj);

	public void deletes(String ids);
	
	public User get(Long id) ;

	public void changPassword(User user ,String oldPassword, String newPassword,
			String newPasswordConfirm);

	public void resetPassword(String ids);

	public void assignRole(Long userId, Long[] roleIds);


}
