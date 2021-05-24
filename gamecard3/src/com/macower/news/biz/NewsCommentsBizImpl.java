package com.macower.news.biz;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.news.dao.NewsCommentsDaoImpl;
import com.macower.news.entity.NewsComments;

@Service
public class NewsCommentsBizImpl extends BaseBiz implements NewsCommentsBiz {

	@Autowired
	private NewsCommentsDaoImpl newsCommentsDao ;
	
	@Override
	public Page<NewsComments> findPageBy(NewsComments obj, Integer pageNo,
			Integer pageSize) {
		return newsCommentsDao.findPageBy(obj, pageNo, pageSize) ;
	}
	
	@Override
	public void save(NewsComments obj) {
		obj.setCommentTm(new Date()) ;
		newsCommentsDao.save(obj) ;
	}

	@Override
	public void deletes(String ids) {
		this.newsCommentsDao.deleteByIds(ids) ;
	}


	@Override
	public NewsComments get(Long id) {
		return this.newsCommentsDao.get(id) ;
	}

}
