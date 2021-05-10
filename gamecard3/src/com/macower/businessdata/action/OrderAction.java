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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.OrderBiz;
import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.Order;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;
import com.macower.core.util.StringUtils;

@Controller
@RequestMapping(value = "/order")
public class OrderAction {

	@Autowired
	private OrderBiz orderBiz;
	

	/**
	 * 后台管理-订单管理页面
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main() {
		return "businessdata/order";
		
	}
	/**
	 * 后台管理-待付款订单
	 * @return
	 */
	@RequestMapping({ "/awaitingPayOrder" })
	public String awaitingPayOrder() {
		return "businessdata/awaitingPayOrder";
		
	}
	
	/**
	 * 后台管理-待发货订单
	 * @return
	 */
	@RequestMapping({ "/prepareSendOrder" })
	public String prepareSendOrder() {
		return "businessdata/prepareSendOrder";
		
	}
	/**
	 * 后台管理-已发货订单
	 * @return
	 */
	@RequestMapping({ "/sendOrder" })
	public String sendOrder() {
		return "businessdata/sendOrder";
		
	}
	/**
	 * 后台管理-已完成订单
	 * @return
	 */
	@RequestMapping({ "/completedOrder" })
	public String completedOrder() {
		return "businessdata/completedOrder";
		
	}
	/**
	 * 后台管理-已取消订单
	 * @return
	 */
	@RequestMapping({ "/cancelledOrder" })
	public String cancelledOrder() {
		return "businessdata/cancelledOrder";
		
	}
	/**
	 * 后台管理-拒绝退款订单
	 * @return
	 */
	@RequestMapping({ "/declinedOrder" })
	public String declinedOrder() {
		return "businessdata/declinedOrder";
		
	}
	/**
	 * 后台管理-申请退款订单
	 * @return
	 */
	@RequestMapping({ "/refundApplyOrder" })
	public String refundApplyOrder() {
		return "businessdata/refundApplyOrder";
		
	}
	/**
	 * 后台管理-申请退款审核通过订单
	 * @return
	 */
	@RequestMapping({ "/auditedOrder" })
	public String auditedOrder() {
		return "businessdata/auditedOrder";
		
	}
	
	
	/**
	 * 后台管理-已退款订单
	 * @return
	 */
	@RequestMapping({ "/refundedOrder" })
	public String refundedOrder() {
		return "businessdata/refundedOrder";
		
	}
	
	/**
	 * 后台管理 - 分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(OrderDto obj, HttpServletResponse response) {
		List<Order> root = new ArrayList<Order>();
		Page<Order> page = orderBiz.findPageBy(obj,
				obj.getStart() / obj.getLimit(), obj.getLimit());
		root = ((List<Order>) page.getData());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response, map);
	}
	/**
	 * 后台管理-删除记录
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String id, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.deletes(id);
		} catch (Exception e) {
			// 删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}
	
	
	/**
	 * 后台管理-打折
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/discount" })
	public void discount(HttpServletRequest request ,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			String orderId = request.getParameter("id") ;//订单id
			String discountOrderTotalPrice = request.getParameter("discountOrderTotalPrice") ; //折扣订单总金额
			String discountPrice = request.getParameter("discountPrice") ;//折扣金额
			String orderDetailList = request.getParameter("orderDetailList") ; //订单详情list
			
			this.orderBiz.discount(orderId,discountOrderTotalPrice,discountPrice,orderDetailList) ;
			
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}
	
	/**
	 * 前台-我的订单
	 */
	@RequestMapping({ "/toMyOrder" })
	public String toMyOrder(Model model ,HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member") ;
		Order param = new Order() ;
		if(member != null){
			param.setMemberId(member.getId()) ;
		}else{
			param.setSessionId(request.getSession().getId()) ;
		}
		List<Order> orders = this.orderBiz.findMyOrder(param) ;
		
		model.addAttribute("orders", orders) ;
		
		return "product/myOrder";
		
	}
	
	/**
	 * 前台-取消订单
	 */
	@RequestMapping({ "/{id}/cancelOrder" })
	public String cancelOrder(@PathVariable Long id ) {
		
		// 更改订单状态
		Order old = this.orderBiz.get(id) ;
		if(old.getOrderStatus() == 1){ //未付款才可以取消
			old.setOrderStatus(5) ; //取消订单
			old.setCancelledTm(new Date()) ;
			this.orderBiz.update(old) ;
		}
		
		return "redirect:/order/toMyOrder";
		
	}
	
	/**
	 * 前台-申请退款
	 */
	@RequestMapping({ "/redundApply" })
	public String redundApply(Order obj) {
		
		// 更改订单状态
		Order old = this.orderBiz.get(obj.getId()) ;
		if(old.getOrderStatus() == 2){ //已付款才可以申请退款
			old.setOrderStatus(6) ; //申请退款
			old.setRefundApplyTm(new Date()) ;
			old.setRefundApplyDesc(obj.getRefundApplyDesc()) ;
			old.setRefundApplyReason(obj.getRefundApplyReason()) ;
			this.orderBiz.update(old) ;
		}
		
		return "redirect:/order/toMyOrder";
		
	}
	/**
	 * 前台-查询物流
	 */
	@RequestMapping({ "/trackShipment" })
	public String trackShipment(Order obj) {
		
		return "product/trackShipment";
		
	}
	/**
	 * 前台-查询物流
	 */
	@RequestMapping({ "/findShippingNoByOrderNo" })
	public void findShippingNoByOrderNo(String orderNo,HttpServletResponse response) {
		String value = null ;
		Order order = this.orderBiz.findShippingNoByOrderNo(orderNo) ;
		Map<String, Object> map = new HashMap<String, Object>();
		if(order != null){
			if(StringUtils.isEmpty(order.getShippingNo())){
				value = "empty" ;
			}else{
				value = order.getShippingNo() ;
			}
			
			map.put("success", value);
		}else{
			value = "no" ;
			map.put("success", value);
		}
		
		JsonUtils.returnJson(response,map) ;
		
	}
	
	
	
	
	
	/*
	 * 前台-生成订单
	 * 判断是否登录，未登录存储sessionid  已登录存储memberid
	 */
	@RequestMapping({ "/gernarateOrder" })
	public String gernarateOrder(HttpServletRequest request) {
		String returlUrl = "redirect:/order/toMyOrder";

		//生成订单 生成订单详情数据  清空购物车
		String[]  item_productIds = request.getParameterValues("item_productId");
		if(item_productIds != null){
			this.orderBiz.createOrder(request);
		}else{
			//跳回我的购物车
			returlUrl = "redirect:/product/toMyCart";
		}
		//跳转到我的订单
		return returlUrl;
		
	}

	/*
	 * 后台：发货
	 */
	@RequestMapping({ "/dispatch" })
	public void dispatch(Order obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.dispatch(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}
	
	
	/*
	 * 后台：修改发货物流信息  在已完成的订单里可以修改发货物流信息
	 */
	@RequestMapping({ "/updateShppingInfo" })
	public void updateShppingInfo(Order obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.updateShppingInfo(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}
	
	/*
	 * 后台：退款 ajax
	 */
	@RequestMapping({ "/refunded" })
	public void refunded(Order obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.refunded(obj);
		}catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}
	
	
	/*
	 * 后台：发货邮件通知
	 */
	@RequestMapping({ "/dispatchSendMail" })
	public void dispatchSendMail(String ids, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.dispatchSendMail(ids);
		}catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}
	/*
	 * 后台：关闭交易 ajax
	 */
	@RequestMapping({ "/completed" })
	public void completed(Order obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.completed(obj);
		}catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}
	
	
	
	/*
	 * 后台：拒绝退款
	 */
	@RequestMapping({ "/declined" })
	public void delined(Order obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.declined(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}
	
	/*
	 * 后台：退款申请-》审核通过
	 */
	@RequestMapping({ "/audit" })
	public void audit(Order obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			orderBiz.audit(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response, map);
	}
	
	
	

}
