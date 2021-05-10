package com.macower.common.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.macower.basedata.biz.CategoryBiz;
import com.macower.basedata.biz.ProductBiz;
import com.macower.basedata.entity.Category;
import com.macower.basedata.entity.Product;
import com.macower.core.entity.Page;
import com.macower.sys.biz.ConfigBiz;
import com.macower.sys.entity.Config;

public class InitInteceptor extends HandlerInterceptorAdapter {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CategoryBiz categoriyBiz ;

	@Autowired
	private ProductBiz productBiz ;

	@Autowired
	private ConfigBiz configBiz;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("InitInteceptor begin...") ;
		// 加载系统配置信息
		ServletContext context= request.getSession().getServletContext() ;
		if(context.getAttribute("configList") == null){
			log.info("load configList...") ;
			context.setAttribute("configList",this.configBiz.findBy(new Config()));
		}
		// 分类树
		if(context.getAttribute("categoryTree") == null){
			log.info("load categoryTree...") ;
			context.setAttribute("categoryTree",categoriyBiz.findCategoryDtoTree());
		}
		// 分类（为查看产品详情而用）
		if(context.getAttribute("categories") == null){
			log.info("load category...") ;
			context.setAttribute("categories",categoriyBiz.findBy(new Category()));
		}
		
		// 首页推介
		if(context.getAttribute("dynimicProducts") == null){
			log.info("load dynimicProducts...") ;
			context.setAttribute("dynimicProducts",productBiz.getAdvicedProductList(5));
		}
		// 热门产品显示数目
		if(context.getAttribute("hotSize") == null){
				log.info("load hotSize...") ;
				Config c =  configBiz.findFromCacheByConfigCode("hotSize") ;
				Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
				context.setAttribute("hotSize",size);
		}
		// 热门产品
		if(context.getAttribute("hotProducts") == null){
			log.info("load hotProducts...") ;
			Config c =  configBiz.findFromCacheByConfigCode("hotSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			Product param = new Product() ;
			param.setIsHot(1) ;
			Page<Product> page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("hotProducts",page.getData());
		}
		// 主产品显示 精选 建议 返回 4*n个
		if(context.getAttribute("page") == null){
			log.info("load main page 1 data...") ;
			Config c =  configBiz.findFromCacheByConfigCode("mainPageSize") ;
			Integer size = (c==null)?new Integer(32):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("page",this.productBiz.findPageBy(new Product(), 0, size));
		}
		// 客户可能喜欢的
		if(context.getAttribute("customersLikes") == null){
			log.info("load customersLikes...") ;
			Config c =  configBiz.findFromCacheByConfigCode("likeSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			Product param = new Product() ;
			param.setIsCustomerLike(1) ;
			Page<Product> page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("customersLikes",page.getData());
		}
		
		// 新产品显示数目大小
		if(context.getAttribute("newSize") == null){
			log.info("load newSize...") ;
			Config c =  configBiz.findFromCacheByConfigCode("newSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("newSize",size);
		}
		// 新产品
		if(context.getAttribute("newProducts") == null){
			log.info("load newProducts...") ;
			Config c =  configBiz.findFromCacheByConfigCode("newSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			Product param = new Product() ;
			param.setIsNew(1) ;
			Page<Product> page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("newProducts",page.getData());
		}
		// 特价商品产品显示数目大小
		if(context.getAttribute("discountSize") == null){
			log.info("load discountSize...") ;
			Config c =  configBiz.findFromCacheByConfigCode("discountSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			context.setAttribute("discountSize",size);
		}

		// 特价商品
		if(context.getAttribute("discountProducts") == null){
			log.info("load discountProducts...") ;
			Config c =  configBiz.findFromCacheByConfigCode("discountSize") ;
			Integer size = (c==null)?new Integer(4):Integer.parseInt(c.getConfigValue()) ;
			Product param = new Product() ;
			param.setIsDiscount(1) ;
			Page<Product> page = productBiz.findPageBy(param, 0, size) ;
			context.setAttribute("discountProducts",page.getData());
		}		
		log.info("InitInteceptor end.") ;
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}

}
