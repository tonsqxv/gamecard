package com.macower.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;
import com.macower.sys.biz.RoleBiz;
import com.macower.sys.entity.Role;
import com.macower.sys.entity.SyncTree;

@Controller
@RequestMapping(value = "/role")
public class RoleAction {

	@Autowired
	private RoleBiz roleBiz;

	/**
	 * 后台管理-角色管理主页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "sys/role";
	}
	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Role obj, HttpServletResponse response) {
		List<Role> root = new ArrayList<Role>();
		Page<Role> page = roleBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Role>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-查询全部
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/listAll" })
	public void listAll(Role obj, HttpServletResponse response) {
		List<Role> root = new ArrayList<Role>();
		root = roleBiz.findBy(new Role());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);

		JsonUtils.returnJson(response,map) ;
	}


	/**
	 * 后台管理-添加
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(Role obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			roleBiz.save(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response,map) ;
	}
	/**
	 * 后台管理-更新
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/update" })
	public void update(Role obj,Long[] moduleIds, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			roleBiz.update(obj,moduleIds);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response,map) ;
	}

	/**
	 * 后台管理-AJAX批量删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			roleBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	

	/**
	 * 后台管理-同步树
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/findModulesByRoleId" })
	public void findRoleModulesByModuleAndRole(Long roleId, HttpServletResponse response) {
		
		List<SyncTree> root = new ArrayList<SyncTree>();
		root = roleBiz.findRoleModulesByModuleAndRole(roleId);

		JsonUtils.returnJson(response,root) ;
	}
	

}
