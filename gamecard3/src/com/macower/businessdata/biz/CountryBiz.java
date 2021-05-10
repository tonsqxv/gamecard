package com.macower.businessdata.biz;


import java.util.List;

import com.macower.businessdata.entity.Country;
import com.macower.core.entity.Page;

public interface CountryBiz {

	public Page<Country> findPageBy(Country obj, Integer pageNo,
			Integer pageSize);
	
	public List<Country> findBy(Country obj);

	public void deletes(String ids);
	
	public void save(Country obj) ;

	public Country get(Long id) ;
	
	public void update(Country obj);

	public String findByCode(String code);

}
