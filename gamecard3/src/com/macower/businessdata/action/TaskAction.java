package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.TaskBiz;
import com.macower.businessdata.entity.Task;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/task")
public class TaskAction {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TaskBiz taskBiz;
	
	/**
	 * 后台管理-会员管理主页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/task";
	}
	
	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Task obj, HttpServletResponse response) {
		List<Task> root = new ArrayList<Task>();
		Page<Task> page = taskBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Task>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response,map) ;
	}

	/**
	 * 后台管理-添加
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(Task obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskBiz.save(obj);
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
	public void update(Task obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskBiz.update(obj);
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
	 * 后台管理-AJAX请求批量删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			taskBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	/**
	 * 后台管理-启动任务
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/startTask" })
	public void startTask(Long id, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			taskBiz.startTask(id);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-停止任务
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/stopTask" })
	public void stopTask(Long id, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			taskBiz.stopTask(id);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-邮件测试
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/testEmail" })
	public void testEmail(Long id,String email, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			taskBiz.testEmail(id,email);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
}
