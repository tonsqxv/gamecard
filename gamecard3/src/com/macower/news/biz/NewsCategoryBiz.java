package com.macower.news.biz;


import java.util.List;

import com.macower.core.entity.Page;
import com.macower.news.entity.NewsCategory;
import com.macower.news.entity.NewsCategoryTree;

public interface NewsCategoryBiz {

	public Page<NewsCategory> findPageBy(NewsCategory obj, Integer pageNo,
			Integer pageSize);

	public List<NewsCategory> findBy(NewsCategory obj);

	public void save(NewsCategory obj);
	
	public NewsCategory get(Long id);

	public void update(NewsCategory obj);

	public void deletes(String ids);
	
	public List<NewsCategoryTree> findNewsCategoryTree();

	public List<NewsCategory> getNewsCategoryList(Integer size);


}
