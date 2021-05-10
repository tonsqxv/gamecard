package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.CountryBiz;
import com.macower.businessdata.entity.Country;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/country")
public class CountryAction {

	@Autowired
	private CountryBiz countryBiz;

	/**
	 * 后台管理-联系我们主页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/country";
	}
	
	/**
	 * 后台管理-分页查询页面
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Country obj, HttpServletResponse response) {
		List<Country> root = new ArrayList<Country>();
		Page<Country> page = countryBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Country>)page.getData() ;
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
	public void add(Country obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			countryBiz.save(obj);
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
	 * 后台管理-修改
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/update" })
	public void update(Country obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			Country old = countryBiz.get(obj.getId()) ;
			old.setCode(obj.getCode()) ;
			old.setCountry(obj.getCountry()) ;
			countryBiz.update(old) ;
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
	 * 后台管理-AJAX请求删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			countryBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	
	
	

}
