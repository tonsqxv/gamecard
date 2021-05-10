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

import com.macower.basedata.biz.CategoryBiz;
import com.macower.basedata.entity.Category;
import com.macower.basedata.entity.CategoryTree;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/category")
public class CategoryAction {

	@Autowired
	private CategoryBiz categoryBiz;

	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "basedata/category";
	}

	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Category obj, HttpServletResponse response) {
		List<Category> root = new ArrayList<Category>();
		Page<Category> page = categoryBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Category>)page.getData() ;
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
	public void listBy(Category obj, HttpServletResponse response) {
		List<Category> root = this.categoryBiz.findBy(obj) ;
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
	public void add(Category obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			categoryBiz.save(obj);
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
	public void update(Category obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			categoryBiz.update(obj);
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
			categoryBiz.deletes(ids);
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
	@RequestMapping({ "/findCategoryTree" })
	public void findCategoryTree(HttpServletResponse response) {
		
		List<CategoryTree> root = new ArrayList<CategoryTree>();
		root = categoryBiz.findCategoryTree();

		JsonUtils.returnJson(response,root) ;
	}
	

}
