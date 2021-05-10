package com.macower.businessdata.biz;


import java.util.List;

import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.OrderDetail;
import com.macower.core.entity.Page;

public interface OrderDetailBiz {

	public List<OrderDetail> findBy(OrderDetail obj);

	public void deletes(String ids);
	
	public void save(OrderDetail obj) ;

	public OrderDetail get(Long id) ;

	public Page<OrderDetail> findPageBy(OrderDto obj, int i, int limit);

}
