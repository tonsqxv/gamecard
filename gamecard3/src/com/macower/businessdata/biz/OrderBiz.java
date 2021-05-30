package com.macower.businessdata.biz;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.Order;
import com.macower.core.entity.Page;

public interface OrderBiz {


	public Page<Order> findPageBy(OrderDto dto, Integer pageNo,
			Integer pageSize);
	
	public List<Order> findMyOrder(Order obj);

	public void save(Order obj);

	public void deletes(String ids);
	
	public Order get(Long id) ;

	public void createOrder(HttpServletRequest request);

	public void update(Order order);

	public void discount(Order obj);

	public void dispatch(Order obj);

	public void refunded(Order obj);

	public void declined(Order obj);

	public void completed(Order obj);

	public void audit(Order obj);

	public void discount(String orderId, String discountOrderTotalPrice,
			String discountPrice, String orderDetailList);

	public void dispatchSendMail(String ids);

	public void updateShppingInfo(Order obj);

	public Order findShippingNoByOrderNo(String orderNo);

	public void cancelOrder(Long id);


}
