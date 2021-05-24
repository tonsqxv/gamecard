package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.ShopItemBiz;
import com.macower.businessdata.dto.ShopItemDto;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.ShopItem;
import com.macower.core.entity.Page;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/shopItem")
public class ShopItemAction {

	@Autowired
	private ShopItemBiz shopItemBiz;
	
	

	/**
	 * 前台-加入购物车
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(ShopItem obj, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String value = null;
		Member member = (Member) request.getSession().getAttribute("member");
		try {
			// 加入购物车 业务逻辑：未登录说明是游客

			if (member == null) {// 游客
				String sessionId = request.getSession().getId() ;
				//判断是否购物车已存在同样的商品，如果存在同样的商品，则更新原有的记录的件数
				ShopItem param = new ShopItem() ;
				param.setSessionId(sessionId);
				param.setProductId(obj.getProductId()) ;
				List<ShopItem> list = this.shopItemBiz.findBy(param) ;
				if(list != null && list.size() > 0){
					ShopItem old = list.get(0) ;
					old.setAmount(old.getAmount() + obj.getAmount()) ;
					old.setCreateTm(new Date()) ;
					this.shopItemBiz.updateShopItem(old) ;
				}else{
					obj.setSessionId(sessionId) ;
					obj.setCreateTm(new Date()) ;
					shopItemBiz.save(obj);
				}
				
			} else {
				ShopItem param = new ShopItem() ;
				param.setMemberId(member.getId());
				param.setProductId(obj.getProductId()) ;
				List<ShopItem> list = this.shopItemBiz.findBy(param) ;
				if(list != null && list.size() > 0){
					ShopItem old = list.get(0) ;
					old.setAmount(old.getAmount() + obj.getAmount()) ;
					old.setCreateTm(new Date()) ;
					this.shopItemBiz.updateShopItem(old) ;
				}else{
					obj.setMemberId(member.getId());
					obj.setCreateTm(new Date()) ;
					shopItemBiz.save(obj);
				}
				
			}
			
			value = "ok";
		} catch (Exception e) {
			e.printStackTrace() ;
			value = "no";
		}
		map.put("success", value);
		JsonUtils.returnJson(response, map);
	}

	
	/**
	 *前台-从购物车删除产品
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/deleteItem" })
	public void deleteItem(ShopItem obj,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String value = null;
		try {
			// 不管是会员还是游客 根据id删除
			this.shopItemBiz.deletes(obj.getId()+"") ;
			value = "ok";
		} catch (Exception e) {
			value = "no";
			
		}
		map.put("success", value);
		JsonUtils.returnJson(response, map);
	}
	
	/**
	 * 后台管理-购物车管理页面 
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main() {
		return "businessdata/shopItem";
		
	}

	/**
	 * 后台-购物车列表
	 */
	@RequestMapping({ "/list" })
	public void list(ShopItemDto obj , HttpServletResponse response) {
		List<ShopItem> root = new ArrayList<ShopItem>();
		Page<ShopItem> page = shopItemBiz.findPageBy(obj,
				obj.getStart() / obj.getLimit(), obj.getLimit());
		root = ((List<ShopItem>) page.getData());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response, map);
		
	}

}
