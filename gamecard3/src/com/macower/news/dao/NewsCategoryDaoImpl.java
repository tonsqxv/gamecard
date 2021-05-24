package com.macower.news.dao ;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.news.entity.NewsCategory;

@Repository
public class NewsCategoryDaoImpl extends BaseEntityDao<NewsCategory> {


	public Page<NewsCategory> findPageBy(NewsCategory obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsCategory.class);
		
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
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

}
