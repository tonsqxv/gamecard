package com.macower.basedata.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.basedata.biz.BrandBiz;
import com.macower.basedata.entity.Brand;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/brand")
public class BrandAction {

	@Autowired
	private BrandBiz brandBiz;

	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "basedata/brand";
	}

	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Brand obj, HttpServletResponse response) {
		List<Brand> root = new ArrayList<Brand>();
		Page<Brand> page = brandBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Brand>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-按条件查询所有
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/listBy" })
	public void listBy(Brand obj, HttpServletResponse response) {
		List<Brand> root = this.brandBiz.findBy(obj) ;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("success", true);
		map.put("root", root);

		JsonUtils.returnJson(response,map) ;
	}

	/**
	 * 后台管理-新增
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(Brand obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			brandBiz.save(obj);
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
	public void update(Brand obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			brandBiz.update(obj);
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
	 *后台管理- AJAX请求批量删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			brandBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	
	
	

}
