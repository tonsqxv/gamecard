package com.macower.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.core.util.JsonUtils;
import com.macower.sys.biz.UserRoleBiz;
import com.macower.sys.entity.Role;
import com.macower.sys.entity.UserRole;

@Controller
@RequestMapping(value = "/userRole")
public class UserRoleAction {

	@Autowired
	private UserRoleBiz userRoleBiz;

	
	/**
	 * 后台管理-根据用户id查询用户可选的角色
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/findNotAssignRoleByUserId" })
	public void findNotAssignRoleByUserId(Long userId, HttpServletResponse response) {
		List<Role> root = new ArrayList<Role>();
		root = userRoleBiz.findNotAssignRoleByUserId(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);

		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-根据用户id查询用户已分配的角色
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/findAssignRoleByUserId" })
	public void findAssignRoleByUserId(Long userId, HttpServletResponse response) {
		List<UserRole> root = new ArrayList<UserRole>();
		root = userRoleBiz.findAssignRoleByUserId(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);

		JsonUtils.returnJson(response,map) ;
	}

	

}
