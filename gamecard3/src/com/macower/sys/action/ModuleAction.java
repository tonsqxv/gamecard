package com.macower.sys.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.core.util.JsonUtils;
import com.macower.sys.biz.ModuleBiz;
import com.macower.sys.entity.AsyncTree;

@Controller
@RequestMapping(value = "/module")
public class ModuleAction {

	@Autowired
	private ModuleBiz moduleBiz;

	/**
	 * 后台管理-异步查询权限树
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/findByParentModuleId" })
	public void findByParentModuleId(Long pid, HttpServletResponse response) {
		
		List<AsyncTree> root = new ArrayList<AsyncTree>();
		root = moduleBiz.findByParentModuleId(pid);

		JsonUtils.returnJson(response,root) ;
	}
	


}
