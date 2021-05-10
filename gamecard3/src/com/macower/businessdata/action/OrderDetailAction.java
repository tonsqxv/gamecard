package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.OrderDetailBiz;
import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.OrderDetail;
import com.macower.core.entity.Page;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/orderDetail")
public class OrderDetailAction {

	@Autowired
	private OrderDetailBiz orderDetailBiz;

	/**
	 * 前台-查看订单详情
	 */
	@RequestMapping({ "{id}/orderDetail" })
	public String toMyOrder(@PathVariable Long id , Model model) {
		OrderDetail param = new OrderDetail() ;
		param.setOrderId(id) ;
		List<OrderDetail> list = this.orderDetailBiz.findBy(param) ;
		model.addAttribute("orderDetailList", list) ;
		return "product/orderDetail";
		
	}
	/**
	 * 后台-查看订单详情列表
	 */
	@RequestMapping({ "/listOrderDetailsByOrderId" })
	public void listOrderDetailsByOrderId(Long orderId , HttpServletResponse response) {
		List<OrderDetail> root = new ArrayList<OrderDetail>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		OrderDetail param = new OrderDetail() ;
		param.setOrderId(orderId) ;
		root = this.orderDetailBiz.findBy(param) ;

		map.put("root", root);

		JsonUtils.returnJson(response, map);
		
	}
	
	/**
	 * 后台管理-订单详情查询页面
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main() {
		return "businessdata/orderDetail";
		
	}
	/**
	 * 后台-订单详情列表
	 */
	@RequestMapping({ "/list" })
	public void list(OrderDto obj , HttpServletResponse response) {
		List<OrderDetail> root = new ArrayList<OrderDetail>();
		Page<OrderDetail> page = orderDetailBiz.findPageBy(obj,
				obj.getStart() / obj.getLimit(), obj.getLimit());
		root = ((List<OrderDetail>) page.getData());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response, map);
		
	}
	
}
