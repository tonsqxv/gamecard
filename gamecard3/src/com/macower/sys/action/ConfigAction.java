package com.macower.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.basedata.biz.CategoryBiz;
import com.macower.basedata.biz.ProductBiz;
import com.macower.basedata.entity.Category;
import com.macower.basedata.entity.Product;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;
import com.macower.sys.biz.ConfigBiz;
import com.macower.sys.entity.Config;

@Controller
@RequestMapping(value = "/config")
public class ConfigAction {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConfigBiz configBiz;
	
	@Autowired
	private CategoryBiz cardTypeBiz ;

	@Autowired
	private ProductBiz productBiz ;


	/**
	 * 后台管理-系统参数配置管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "sys/config";
	}

	/**
	 * 后台管理-分页查询页面
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Config obj, HttpServletResponse response) {
		List<Config> root = new ArrayList<Config>();
		Page<Config> page = configBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Config>)page.getData() ;
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
	public void add(Config obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			configBiz.save(obj);
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
	public void update(Config obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			configBiz.update(obj);
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
			configBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	/**
	 * 后台管理-重启服务
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/startService" })
	public void startService(HttpServletRequest request,HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			log.info("start service begin....");
			// 加载系统配置信息
			ServletContext context= request.getSession().getServletContext() ;
			context.setAttribute("configList",this.configBiz.findBy(new Config()));
			// 分类树
			context.setAttribute("categoryTree",cardTypeBiz.findCategoryDtoTree());
			//分类
			context.setAttribute("categories",cardTypeBiz.findBy(new Category()));
			// 首页推介
			context.setAttribute("dynimicProducts",productBiz.getAdvicedProductList(5));
			//左边热门产品显示个数
			Config c =  configBiz.findByConfigCode("hotSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("hotSize",size);
			// 热门产品
			Product param = new Product() ;
			param.setIsHot(1) ;
			Page<Product> page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("hotProducts",page.getData());
			
			// 主产品显示 精选 建议 返回 4*n个
			c =  configBiz.findByConfigCode("mainPageSize") ;
			size = (c==null)?new Integer(32):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("page",this.productBiz.findPageBy(new Product(), 0, size));
			// 客户可能喜欢的
			c =  configBiz.findByConfigCode("likeSize") ;
			size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			param = new Product() ;
			param.setIsCustomerLike(1) ;
			page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("customersLikes",page.getData());
				//左边最新产品显示个数
			c =  configBiz.findByConfigCode("newSize") ;
			size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("newSize",size);
			// 最新产品
			param = new Product() ;
			param.setIsNew(1) ;
			page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("newProducts",page.getData());
		
			//特价商品显示个数
			c =  configBiz.findByConfigCode("discountSize") ;
			size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("discountSize",size);
			
			// 特价商品
			param = new Product() ;
			param.setIsDiscount(1) ;
			page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("discountProducts",page.getData());
			
			log.info("start service successfull.");
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	
	
	

}
