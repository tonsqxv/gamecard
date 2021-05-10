package com.macower.basedata.biz;


import java.util.List;

import com.macower.basedata.entity.Brand;
import com.macower.core.entity.Page;

public interface BrandBiz {

	public Page<Brand> findPageBy(Brand obj, Integer pageNo,
			Integer pageSize);

	public List<Brand> findBy(Brand obj);

	public void save(Brand obj);

	public void update(Brand obj);

	public void deletes(String ids);
	
	public List<Brand> getBrandList(Integer size) ;

}
