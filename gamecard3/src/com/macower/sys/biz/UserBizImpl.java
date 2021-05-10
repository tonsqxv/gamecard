package com.macower.sys.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.MD5Utils;
import com.macower.sys.dao.UserDaoImpl;
import com.macower.sys.dao.UserRoleDaoImpl;
import com.macower.sys.entity.User;
import com.macower.sys.entity.UserRole;

@Service
public class UserBizImpl implements UserBiz {

	@Autowired
	private UserDaoImpl userDao;
	
	@Autowired
	private UserRoleDaoImpl userRoleDao;

	@Override
	public Page<User> findPageBy(User obj, Integer pageNo, Integer pageSize) {
		return userDao.findPageBy(obj, pageNo, pageSize);
	}

	@Override
	public List<User> findBy(User obj) {
		return userDao.findBy(obj);
	}

	@Override
	public void save(User obj) {
		User param = new User();
		param.setUserName(obj.getUserName().trim());
		int count = this.userDao.countBy(param);
		if (count > 0) {
			throw new BizException(obj.getUserName() + "已经存在");
		}
		obj.setUserName(obj.getUserName().trim());
		obj.setPassword(MD5Utils.encode(obj.getPassword().trim())) ;
		userDao.save(obj);

	}

	@Override
	public void update(User obj) {
		User old = userDao.get(obj.getId());
		if (old == null) {
			throw new BizException("记录已被删除");
		}
		if (!old.getUserName().equals(obj.getUserName().trim())) {
			User param = new User();
			param.setUserName(obj.getUserName().trim());
			int count = this.userDao.countBy(param);
			if (count > 0) {
				throw new BizException(obj.getUserName() + "已经存在");
			}
		}
		old.setUserName(obj.getUserName().trim());

		userDao.update(old);

	}

	@Override
	public void deletes(String ids) {

		this.userDao.deleteByIds(ids);
	}

	@Override
	public User get(Long id) {
		return this.userDao.get(id);
	}

	/**
	 * 修改密码
	 */
	@Override
	public void changPassword(User user, String oldPassword,
			String newPassword, String newPasswordConfirm) {
		if (user == null) {
			throw new BizException("用户未登陆");
		}
		if ("admin".equals(user.getUserName())) {

		} else { // 不是admin才可以修改密码
			User old = userDao.get(user.getId());
			if (old == null) {
				throw new BizException("记录已被删除");
			}
			if(!old.getPassword().equals(MD5Utils.encode(oldPassword))){
				throw new BizException("原始密码填写不正确");
			}
			if(!newPassword.equals(newPasswordConfirm)){
				throw new BizException("两次密码输入不一致");
			}
			old.setPassword(MD5Utils.encode(newPassword.trim()));

			userDao.update(old);
		}

	}

	/**
	 * 重置密码
	 */
	@Override
	public void resetPassword(String ids) {
		String[] arr = ids.split(",") ;
		User user = null ;
		for(String id : arr){
			user = userDao.get(Long.parseLong(id));
			user.setPassword(MD5Utils.encode("abcd1234@A")) ;
			this.userDao.update(user) ;
		}
		
		
	}

	/**
	 * 分配角色
	 */
	@Override
	public void assignRole(Long userId, Long[] roleIds) {
		User user = userDao.get(userId);
		if(user == null){
			throw new BizException("该用户已删除");
		}
		if(roleIds.length == 0){
			return ;
		}
		//根据用户id查询所有的角色
		UserRole param = new UserRole() ;
		param.setUserId(userId) ;
		List<UserRole> oldUserRoleList = this.userRoleDao.findBy(param) ;
		//删除就有角色
		this.userRoleDao.deleteBatch(oldUserRoleList) ;
		
		//构建新角色
		List<UserRole> newList = new ArrayList<UserRole>() ;
		for(Long roleId : roleIds){
			UserRole ur = new UserRole() ;
			ur.setUserId(userId) ;
			ur.setRoleId(roleId) ;
			newList.add(ur) ;
		}
		//添加新角色
		for(UserRole ur : newList){
			this.userRoleDao.save(ur) ;
		}
	}

}
