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
import com.macower.news.biz.NewsCommentsBiz;
import com.macower.news.entity.NewsComments;

@Controller
@RequestMapping(value = "/newsComments")
public class NewsCommentsAction {

	@Autowired
	private NewsCommentsBiz newsCommentsBiz;

	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "news/newsComments";
	}

	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(NewsComments obj, HttpServletResponse response) {
		List<NewsComments> root = new ArrayList<NewsComments>();
		Page<NewsComments> page = newsCommentsBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<NewsComments>)page.getData() ;
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
	public void add(NewsComments obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			newsCommentsBiz.save(obj);
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
			newsCommentsBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
}
