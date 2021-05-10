package com.macower.basedata.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.basedata.biz.CategoryBiz;
import com.macower.basedata.biz.KeyCategoryBiz;
import com.macower.basedata.biz.KeyValueBiz;
import com.macower.basedata.biz.ProductBiz;
import com.macower.basedata.entity.Category;
import com.macower.basedata.entity.KeyCategory;
import com.macower.basedata.entity.KeyValue;
import com.macower.basedata.entity.Product;
import com.macower.basedata.util.DoubleUtil;
import com.macower.businessdata.biz.ShopItemBiz;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.ShopItem;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.sys.biz.ConfigBiz;
import com.macower.sys.entity.Config;

@Controller
@RequestMapping(value = "/product")
public class ProductAction {

	@Autowired
	private CategoryBiz categoryBiz;

	@Autowired
	private ProductBiz productBiz;

	@Autowired
	private ShopItemBiz shopItemBiz;

	@Autowired
	private KeyCategoryBiz keyCategoryBiz;

	@Autowired
	private KeyValueBiz keyValueBiz;
	
	@Autowired
	private ConfigBiz configBiz;

	/**
	 * 产品详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "{id}/productDetail" })
	public String productDetail(@PathVariable Long id,
			HttpServletRequest request, Model model) {
		ServletContext context = request.getSession().getServletContext();
		List<Category> categories = (List<Category>) context
				.getAttribute("categories");
		if (categories == null) {
			categories = this.categoryBiz.findBy(new Category());
		}
		// 详情对象
		Product product = this.productBiz.get(id);
		model.addAttribute("product", product);
		// 加载颜色
		KeyCategory kcParam = new KeyCategory();
		kcParam.setProductId(id);
		kcParam.setType(1); // 类型：颜色
		List<KeyCategory> kcList = keyCategoryBiz.findBy(kcParam);
		model.addAttribute("colorList", kcList);
		// 加载尺寸
		KeyCategory sizeParam = new KeyCategory();
		sizeParam.setProductId(id);
		sizeParam.setType(2); // 类型：尺寸
		List<KeyCategory> sizeList = keyCategoryBiz.findBy(sizeParam);
		model.addAttribute("sizeList", sizeList);
		// 加载特性值
		KeyValue kvParam = new KeyValue();
		kvParam.setProductId(id);
		List<KeyValue> kvList = keyValueBiz.findBy(kvParam);
		model.addAttribute("kvList", kvList);

		String returnUrl = "product/productDetail";
		// 根据产品类型跳转到不同详情页面
		Long categoryId = product.getCategoryId();
		String productDetailTpl = getProductDetailTplBy(categoryId, categories);
		if (productDetailTpl != null) {
			returnUrl = "product/" + productDetailTpl;
		}
		return returnUrl;
	}

	private String getProductDetailTplBy(Long categoryId,
			List<Category> categories) {
		String s = null;
		for (Category obj : categories) {
			if (obj.getId().equals(categoryId)) {
				if (StringUtils.isNotEmpty(obj.getProductDetailTpl())) {
					s = obj.getProductDetailTpl().trim();
					break;
				} else {
					s = getProductDetailTplBy(obj.getParentId(), categories);
				}
				break;
			}
		}
		return s;
	}

	/**
	 * 我的购物车
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/toMyCart" })
	public String toMyCart(Model model, HttpServletRequest request) {

		// 查询会员的购物车
		// 判断是否登录
		Member member = (Member) request.getSession().getAttribute("member");
		List<ShopItem> shopItemList = null;
		ShopItem param = new ShopItem();
		if (member == null) {// 未登录
			param.setSessionId(request.getSession().getId());
		} else {
			param.setMemberId(member.getId());
		}

		shopItemList = this.shopItemBiz.findBy(param);
		model.addAttribute("shopItemList", shopItemList);
		// 计算总金额
		double totalPrice = 0;
		for (ShopItem item : shopItemList) {
			BigDecimal b1 = new BigDecimal(Double.toString(item.getAmount()
					* item.getUnitPrice()));
			BigDecimal b2 = new BigDecimal(Double.toString(totalPrice));
			totalPrice = b1.add(b2).doubleValue();

		}
		// 金额格式化 只保留两位小数
		totalPrice = new DoubleUtil().format2Point(totalPrice);

		model.addAttribute("totalPrice", totalPrice);

		return "product/cart";
	}

	/**
	 * 我的订单里-->执行checkout操作
	 * 
	 * @return
	 */
	@RequestMapping({ "{orderId}/checkOut" })
	public String checkOut(@PathVariable Long orderId,
			HttpServletRequest request) {
		String returnUrl = "pay/prePayForm";
		HttpSession session = request.getSession();
		// 每次点击check out都把最新的订单号记录在session
		session.setAttribute("prePayOrderId", orderId);

		// 判断是否登录 决定页面去向
		Member member = (Member) session.getAttribute("member");
		if (member != null) {
			returnUrl = "redirect:/pay/prePayForm";
		} else {
			returnUrl = "product/selectCheckOutType";
		}

		return returnUrl;
	}

	/**
	 * 分类链接
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping({ "{categoryId}/listProductByCategory" })
	public String listProductByCategory(@PathVariable Long categoryId,HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String pageIndex_Str = (String) request.getParameter("pageIndex");
		Product param = null ;
		Integer pageIndex = 1 ;
		if(pageIndex_Str == null){ //默认查询第一页
			session.setAttribute("categoryId", categoryId) ;
			pageIndex = 1;
			param = new Product() ;
			param.setCategoryId(categoryId) ;
		}else{//分页查询
			categoryId = (Long)session.getAttribute("categoryId") ;
			pageIndex = Integer.parseInt(pageIndex_Str);
			param = new Product() ;
			param.setCategoryId(categoryId) ;
		}
		Config c = this.configBiz.findFromCacheByConfigCode("pageSize") ;
		Integer pageSize = 20 ;
		try {
			pageSize = (c == null)?20:Integer.parseInt(c.getConfigValue()) ;
		} catch (NumberFormatException e) {
			pageSize = 20 ;
		}
		Page<Product> page = this.productBiz.findPageBy(param, pageIndex-1, pageSize) ;
		Category category = this.categoryBiz.get(categoryId);
		model.addAttribute("category", category);
		model.addAttribute("page",page);

		return "product/listProductByCategory";
	}

	/**
	 * 热门推介 more链接 查询所有热门商品 type 1:热门产品 2：最新产品 3:特价商品
	 * 
	 * @return
	 */
	@RequestMapping({ "/viewMoreProduct" })
	public String viewMoreProduct(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String pageIndex_Str = (String) request.getParameter("pageIndex");
		String type = request.getParameter("type");
		Integer pageIndex = 1;
		Product param = null;
		Page<Product> page = null;
		if (pageIndex_Str == null) { // 来自view more 默认查询第一页
			pageIndex = 1;
			session.setAttribute("type", type);
			switch (Integer.parseInt(type)) {
			case 1:
				pageIndex = 1;
				param = new Product();
				param.setIsHot(1);
				model.addAttribute("title", "Hot Products");
				break;
			case 2:
				pageIndex = 1;
				param = new Product();
				param.setIsNew(1);
				model.addAttribute("title", "New Products");
				break;
			case 3:
				pageIndex = 1;
				param = new Product();
				param.setIsDiscount(1);
				model.addAttribute("title", "Discount Products");
				break;
			}
		} else {// 来自分页
			pageIndex_Str = (String) request.getParameter("pageIndex");
			pageIndex = Integer.parseInt(pageIndex_Str);
			type = (String) session.getAttribute("type");
			switch (Integer.parseInt(type)) {
			case 1:
				param = new Product();
				param.setIsHot(1);
				model.addAttribute("title", "Hot Products");
				break;
			case 2:
				param = new Product();
				param.setIsNew(1);
				model.addAttribute("title", "New Products");
				break;
			case 3:
				param = new Product();
				param.setIsDiscount(1);
				model.addAttribute("title", "Discount Products");
				break;
			}
		}
		Config c = this.configBiz.findFromCacheByConfigCode("pageSize") ;
		Integer pageSize = 20 ;
		try {
			pageSize = (c == null)?20:Integer.parseInt(c.getConfigValue()) ;
		} catch (NumberFormatException e) {
			pageSize = 20 ;
		}
		page = this.productBiz.findPageBy(param, pageIndex - 1, pageSize);
		model.addAttribute("page", page);

		return "product/viewMoreProduct";
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	@RequestMapping({ "/searchBy" })
	public String searchBy(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String pageIndex_Str = (String) request.getParameter("pageIndex");
		// 搜索重置session的参数 page从session中获取参数
		String productName = null;
		Integer pageIndex = 1;
		if (pageIndex_Str == null) {//默认查询第一页
			pageIndex = 1;
			productName = request.getParameter("searchByParam_productName");
			session.setAttribute("searchByParam_productName", productName);
		} else{
			productName = (String) session
					.getAttribute("searchByParam_productName");
			pageIndex_Str = (String) request.getParameter("pageIndex");
			pageIndex = Integer.parseInt(pageIndex_Str);
		}

		Product param = new Product();
		param.setProductName(productName);
		Config c = this.configBiz.findFromCacheByConfigCode("pageSize") ;
		Integer pageSize = 20 ;
		try {
			pageSize = (c == null)?20:Integer.parseInt(c.getConfigValue()) ;
		} catch (NumberFormatException e) {
			pageSize = 20 ;
		}
		Page<Product> page = this.productBiz
				.findPageBy(param, pageIndex - 1, pageSize);
		model.addAttribute("page", page);

		return "product/searchResult";
	}
	
	/**
	 * 主页分页
	 * 
	 * @return
	 */
	@RequestMapping({ "/mainPage" })
	public String mainPage(HttpServletRequest request, Model model) {
		String pageIndex_Str = (String) request.getParameter("pageIndex");
		Integer pageIndex = 1;
		
		Config c = this.configBiz.findFromCacheByConfigCode("mainPageSize") ;
		Integer pageSize = 32 ;
		try {
			pageIndex = Integer.parseInt(pageIndex_Str) ;
			pageSize = (c == null)?32:Integer.parseInt(c.getConfigValue()) ;
		} catch (NumberFormatException e) {
			pageIndex = 1 ; 
			pageSize = 1 ;
		}
		Page<Product> page = this.productBiz
				.findPageBy(new Product(), pageIndex - 1, pageSize);
		model.addAttribute("page", page);

		return "product/main";
	}

}
