package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.ShippingOption;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class ShippingOptionDaoImpl extends BaseEntityDao<ShippingOption>{

	public Page<ShippingOption> findPageBy(ShippingOption obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(ShippingOption.class);
		if(StringUtils.isNotEmpty(obj.getShippingOptionName())){
			dc.add(Restrictions.like("shippingOptionName", obj.getShippingOptionName(),MatchMode.ANYWHERE)) ;
		}
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
