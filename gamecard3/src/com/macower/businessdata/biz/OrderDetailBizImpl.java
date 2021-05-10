package com.macower.businessdata.biz;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.OrderDetailDaoImpl;
import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.OrderDetail;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
@Service
public class OrderDetailBizImpl extends BaseBiz implements OrderDetailBiz {

	@Autowired
	private OrderDetailDaoImpl orderDetailDao ;
	

	@Override
	public List<OrderDetail> findBy(OrderDetail obj) {
		return orderDetailDao.findBy(obj) ;
	}


	@Override
	public void save(OrderDetail obj) {
		orderDetailDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.orderDetailDao.deleteByIds(ids) ;
	}

	@Override
	public OrderDetail get(Long id) {
		return this.orderDetailDao.get(id) ;
	}


	@Override
	public Page<OrderDetail> findPageBy(OrderDto obj, int pageNo, int pageSize) {
		return this.orderDetailDao.findPageBy(obj, pageNo, pageSize);
	}

}
