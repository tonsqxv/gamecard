package com.macower.news.dao ;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;
import com.macower.news.entity.News;

@Repository
public class NewsDaoImpl extends BaseEntityDao<News> {


	public Page<News> findPageBy(News obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(News.class);
		
		if(StringUtils.isNotEmpty(obj.getTitle())){  
			dc.add(Restrictions.like("title", obj.getTitle(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getNewsCategoryId() != null && obj.getNewsCategoryId() != 0){
			dc.add(Restrictions.eq("newsCategoryId", obj.getNewsCategoryId())) ;
		}
		if(obj.getStatus() != null){
			dc.add(Restrictions.eq("status", obj.getStatus())) ;
		}
		dc.addOrder(Order.desc("createTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

}
