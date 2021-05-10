package com.macower.businessdata.dao;



import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.OrderDetail;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class OrderDetailDaoImpl extends BaseEntityDao<OrderDetail>{

	public Page<OrderDetail> findPageBy(OrderDto obj, int pageNo, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetail.class);
		//订单编号
		if(StringUtils.isNotEmpty(obj.getOrderNo())){
			dc.add(Restrictions.like("orderNo", obj.getOrderNo(),MatchMode.ANYWHERE)) ;
		}
		dc.addOrder(Order.desc("id")) ;
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}


}
