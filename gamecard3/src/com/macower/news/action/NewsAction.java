package com.macower.news.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.entity.Member;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;
import com.macower.news.biz.NewsBiz;
import com.macower.news.biz.NewsCommentsBiz;
import com.macower.news.entity.News;
import com.macower.news.entity.NewsComments;
import com.macower.sys.biz.ConfigBiz;
import com.macower.sys.entity.Config;

@Controller
@RequestMapping(value = "/news")
public class NewsAction {

	@Autowired
	private NewsBiz newsBiz;
	
	@Autowired
	private NewsCommentsBiz newsCommentsBiz;

	@Autowired
	private ConfigBiz configBiz;
	
	/**
	 * 前台-新闻列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/" })
	public String news(HttpServletRequest request,Model model) {
		String pageIndex_Str = (String) request.getParameter("pageIndex");
		Integer pageIndex = 1;
		if (pageIndex_Str == null) {//默认查询第一页
			pageIndex = 1;
		} else{
			pageIndex_Str = (String) request.getParameter("pageIndex");
			pageIndex = Integer.parseInt(pageIndex_Str);
		}
		Config c = this.configBiz.findFromCacheByConfigCode("pageSize") ;
		Integer pageSize = 20 ;
		try {
			pageSize = (c == null)?20:Integer.parseInt(c.getConfigValue()) ;
		} catch (NumberFormatException e) {
			pageSize = 20 ;
		}
		News param = new News() ;
		param.setStatus(1) ; //在线状态
		Page<News> page = this.newsBiz.findPageBy(param, pageIndex-1, pageSize) ;
		model.addAttribute("page",page);
		
		return "product/news";
	}
	
	/**
	 * 查看新闻详情
	 * @param newsId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({ "{newsId}/newsDetail" })
	public String newsDetail(@PathVariable Long newsId,HttpServletRequest request,Model model) {
		News news = this.newsBiz.get(newsId) ;
		model.addAttribute("news",news);
		
		String pageIndex_Str = (String) request.getParameter("pageIndex");
		Integer pageIndex = 1 ;
		if(pageIndex_Str == null){ //默认查询第一页
			pageIndex = 1;
		}else{//分页查询
			pageIndex = Integer.parseInt(pageIndex_Str);
		}
		Config c = this.configBiz.findFromCacheByConfigCode("pageSize") ;
		Integer pageSize = 20 ;
		try {
			pageSize = (c == null)?20:Integer.parseInt(c.getConfigValue()) ;
		} catch (NumberFormatException e) {
			pageSize = 20 ;
		}
		NewsComments param = new NewsComments() ;
		param.setNewsId(newsId) ;
		Page<NewsComments> page = this.newsCommentsBiz.findPageBy(param, pageIndex-1, pageSize) ;
		//对page里的数据反序
		List<NewsComments> data = (List<NewsComments>)page.getData() ;
		List<NewsComments> newData = new ArrayList<NewsComments>() ;
		for(int i= data.size()-1 ; i >=0 ; i-- ){
			newData.add(data.get(i)) ;
		}
		page.setData(newData) ;
		model.addAttribute("page", page) ;
		
		return "product/newsDetail";
	}
	/**
	 * 新闻点评
	 * @param newsId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({ "{newsId}/comment" })
	public String comment(@PathVariable Long newsId,String comment ,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession() ;
		Member member = (Member)session.getAttribute("member") ;
		if(member == null){
			session.setAttribute("newsId", newsId) ;
			return "product/memberLogin";
		}
		NewsComments obj = new NewsComments() ;
		obj.setNewsId(newsId) ;
		obj.setContext(comment) ;
		obj.setCommentTm(new Date()) ;
		obj.setMemberId((member == null)?null:member.getId());
		newsCommentsBiz.save(obj) ;
		
		return "redirect:/news/"+newsId+"/newsDetail";
	}
	
	/**
	 * 后台管理-主界面
	 * @param model
	 * @return
	 */
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
