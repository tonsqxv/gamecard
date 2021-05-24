package com.macower.news.biz;



import com.macower.core.entity.Page;
import com.macower.news.entity.News;

public interface NewsBiz {

	public Page<News> findPageBy(News obj, Integer pageNo,
			Integer pageSize);

	public void save(News obj);
	
	public News get(Long id);

	public void update(News obj);

	public void deletes(String ids);

	public void updateStatusByType(Long id, Integer type);
	
}
