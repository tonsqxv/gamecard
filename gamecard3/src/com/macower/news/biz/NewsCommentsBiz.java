package com.macower.news.biz;



import com.macower.core.entity.Page;
import com.macower.news.entity.NewsComments;

public interface NewsCommentsBiz {

	public Page<NewsComments> findPageBy(NewsComments obj, Integer pageNo,
			Integer pageSize);

	public void save(NewsComments obj);
	
	public NewsComments get(Long id);

	public void deletes(String ids);

	
}
