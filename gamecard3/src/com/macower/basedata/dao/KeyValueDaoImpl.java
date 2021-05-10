package com.macower.basedata.dao ;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.KeyValue;
import com.macower.core.dao.BaseEntityDao;

@Repository
public class KeyValueDaoImpl extends BaseEntityDao<KeyValue> {


	public List<KeyValue> findBy(KeyValue obj) {
		DetachedCriteria dc = DetachedCriteria.forClass(KeyValue.class);
		
		if(obj.getProductId() != null){
			dc.add(Restrictions.eq("productId", obj.getProductId())) ;
		}
		dc.addOrder(Order.asc("sort")) ;
		return this.findBy(dc) ;
	}

	public void deleteByProductId(Long id) {
		KeyValue param = new KeyValue() ;
		param.setProductId(id) ;
		List<KeyValue> list = this.findBy(param) ;
		if(list !=null && list.size() >0){
			this.deleteBatch(list) ;
		}
		
	}

}
