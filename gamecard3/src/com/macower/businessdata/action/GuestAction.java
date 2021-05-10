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

import com.macower.businessdata.biz.GuestBiz;
import com.macower.businessdata.entity.Guest;
import com.macower.core.entity.Page;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/guest")
public class GuestAction {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GuestBiz guestBiz;
	
	/**
	 * 后台管理-会员管理主页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/guest";
	}
	
	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Guest obj, HttpServletResponse response) {
		List<Guest> root = new ArrayList<Guest>();
		Page<Guest> page = guestBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Guest>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

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
			guestBiz.deletes(ids);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-同步订单数据
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/sycnData" })
	public void sycnData(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			int count = guestBiz.sycnData();
			map.put("msg", count);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	
	/**
	 * 后台管理-同步会员数据
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/sycnMemberData" })
	public void sycnMemberData(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			int count = guestBiz.sycnMemberData();
			map.put("msg", count);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	/**
	 * 后台管理-初始化任务
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/init" })
	public void init(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			guestBiz.init();
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-初始化任务
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/resetOrderData" })
	public void resetOrderData(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			guestBiz.resetOrderData();
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
}
