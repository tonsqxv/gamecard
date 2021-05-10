package com.macower.businessdata.biz;


import java.util.List;


import com.macower.businessdata.entity.ShippingOption;
import com.macower.core.entity.Page;

public interface ShippingOptionBiz {

	public Page<ShippingOption> findPageBy(ShippingOption obj, Integer pageNo,
			Integer pageSize);
	
	public List<ShippingOption> findBy(ShippingOption obj);

	public void save(ShippingOption obj);

	public void deletes(String ids);
	
	public ShippingOption get(Long id) ;

	public void update(ShippingOption obj);


}
