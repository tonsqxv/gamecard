package com.macower.basedata.biz;


import java.util.List;

import com.macower.basedata.dto.CategoryDto;
import com.macower.basedata.entity.Category;
import com.macower.basedata.entity.CategoryTree;
import com.macower.core.entity.Page;

public interface CategoryBiz {

	public Page<Category> findPageBy(Category obj, Integer pageNo,
			Integer pageSize);

	public List<Category> findBy(Category obj);

	public void save(Category obj);
	
	public Category get(Long id);

	public void update(Category obj);

	public void deletes(String ids);
	
	public List<CategoryTree> findCategoryTree();

	public List<Category> getCategoryList(Integer size);

	List<CategoryDto> findCategoryDtoTree();

}
