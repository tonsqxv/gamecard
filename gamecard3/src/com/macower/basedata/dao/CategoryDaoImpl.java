package com.macower.basedata.dao ;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.Category;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.util.StringUtils;
import com.macower.core.entity.Page;

@Repository
public class CategoryDaoImpl extends BaseEntityDao<Category> {


	public Page<Category> findPageBy(Category obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		
		if(StringUtils.isNotEmpty(obj.getName())){  
			dc.add(Restrictions.like("name", obj.getName(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getParentId() != null){  
			if(obj.getParentId() == 0L){
				dc.add(Restrictions.isNull("parentId"));
			}else{
				dc.add(Restrictions.eq("parentId", obj.getParentId())) ;
			}
		}
		dc.addOrder(Order.asc("sort")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

}
