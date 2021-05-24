package com.macower.news.dao ;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.news.entity.NewsComments;

@Repository
public class NewsCommentsDaoImpl extends BaseEntityDao<NewsComments> {


	public Page<NewsComments> findPageBy(NewsComments obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsComments.class);
		
		if(obj.getNewsId() != null){
			dc.add(Restrictions.eq("newsId", obj.getNewsId())) ;
		}
		dc.addOrder(Order.desc("commentTm")) ;
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

}
