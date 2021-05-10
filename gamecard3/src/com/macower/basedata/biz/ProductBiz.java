package com.macower.basedata.biz;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.macower.basedata.entity.Product;
import com.macower.core.entity.Page;

public interface ProductBiz {


	public Page<Product> findPageBy(Product obj, Integer pageNo,
			Integer pageSize);
	
	public List<Product> findByCategory(Long categoryId);
	
	public void save(Product obj,HttpServletRequest request);

	public void update(Product obj,HttpServletRequest request);

	public void deletes(String imageRootPath, String ids);
	
	public Product get(Long id) ;
	
	public Product load(Long id) ;

	public void updateImg(Product obj);
	
	public List<Product> getAdvicedProductList(Integer size) ;

	public void updateZoomImg(Product obj);
	
	public List<Product> featuredProducts(Integer size) ;

	public void updateSortNo(Product obj);

	void updateDesc(Product obj);

	void update(Product obj);


	

}
