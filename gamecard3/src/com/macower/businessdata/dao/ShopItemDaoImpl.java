package com.macower.businessdata.dao;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.dto.ShopItemDto;
import com.macower.businessdata.entity.ShopItem;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;

@Repository
public class ShopItemDaoImpl extends BaseEntityDao<ShopItem>{

	public Page<ShopItem> findPageBy(ShopItemDto obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(ShopItem.class);
		
		if(obj.getCreateTmBegin() != null){
			dc.add(Restrictions.ge("createTm", obj.getCreateTmBegin())) ;
		}
		if(obj.getCreateTmEnd() != null){
			dc.add(Restrictions.le("createTm", obj.getCreateTmEnd())) ;
		}
		if(obj.getIsMember() != null){
			if(obj.getIsMember() == 1){ //是会员
				dc.add(Restrictions.sqlRestriction("member_id is not null")) ;
			}else if(obj.getIsMember() == 2){ //不是会员
				dc.add(Restrictions.sqlRestriction("member_id is null")) ;
			}
		}
		dc.addOrder(Order.desc("createTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	


}
