package com.macower.basedata.dao ;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.KeyCategory;
import com.macower.core.dao.BaseEntityDao;

@Repository
public class KeyCategoryDaoImpl extends BaseEntityDao<KeyCategory> {


	public List<KeyCategory> findBy(KeyCategory obj) {
		DetachedCriteria dc = DetachedCriteria.forClass(KeyCategory.class);
		
		if(obj.getType() != null){
			dc.add(Restrictions.eq("type", obj.getType())) ;
		}
		if(obj.getProductId() != null){
			dc.add(Restrictions.eq("productId", obj.getProductId())) ;
		}
		return this.findBy(dc) ;
	}

	public void deleteByProductIdAndType(Long id, Integer type) {
		KeyCategory param = new KeyCategory() ;
		param.setProductId(id) ;
		param.setType(type) ;
		List<KeyCategory> list = this.findBy(param) ;
		if(list !=null && list.size() >0){
			this.deleteBatch(list) ;
		}
		
	}

}
