package com.macower.news.biz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.news.dao.NewsDaoImpl;
import com.macower.news.entity.News;

@Service
public class NewsBizImpl extends BaseBiz implements NewsBiz {

	@Autowired
	private NewsDaoImpl newsDao ;
	
	@Override
	public Page<News> findPageBy(News obj, Integer pageNo,
			Integer pageSize) {
		return newsDao.findPageBy(obj, pageNo, pageSize) ;
	}
	
	@Override
	public void save(News obj) {
		obj.setCreateTm(new Date()) ;
		obj.setStatus(1) ; //在线状态
		newsDao.save(obj) ;

	}

	@Override
	public void update(News obj) {
		News old = this.newsDao.get(obj.getId()) ;
		old.setTitle(obj.getTitle()) ;
		old.setSubTitle(obj.getSubTitle()) ;
		old.setContext(obj.getContext()) ;
		old.setCreateTm(new Date()) ;
		old.setNewsCategoryId(obj.getNewsCategoryId()) ;
		
		newsDao.update(old) ;

	}

	@Override
	public void deletes(String ids) {
		this.newsDao.deleteByIds(ids) ;
	}


	@Override
	public News get(Long id) {
		return this.newsDao.get(id) ;
	}

	@Override
	public void updateStatusByType(Long id, Integer type) {
		News old = this.newsDao.get(id) ;
		if(type == 1){
			old.setStatus(1) ;
		}else if(type == 2){
			old.setStatus(2) ;
		}
		this.newsDao.update(old) ;
	}


}
