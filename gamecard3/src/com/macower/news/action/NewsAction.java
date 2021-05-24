package com.macower.news.action;

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
import com.macower.news.biz.NewsBiz;
import com.macower.news.entity.News;

@Controller
@RequestMapping(value = "/news")
public class NewsAction {

	@Autowired
	private NewsBiz newsBiz;

	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "news/news";
	}

	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(News obj, HttpServletResponse response) {
		List<News> root = new ArrayList<News>();
		Page<News> page = newsBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<News>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response,map) ;
	}
	

	/**
	 * 后台管理-新增
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(News obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			newsBiz.save(obj);
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
	public void update(News obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			newsBiz.update(obj);
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
			newsBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 *后台管理- 启用/关闭
	 * @param id 
	 * @param type  1:启用  2：关闭
	 * @param response
	 */
	@RequestMapping({ "/updateStatusByType" })
	public void updateStatusByType(Long id,Integer type, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			newsBiz.updateStatusByType(id,type);
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
}
