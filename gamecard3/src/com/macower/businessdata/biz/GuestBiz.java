package com.macower.businessdata.biz;


import java.util.List;

import com.macower.businessdata.entity.Guest;
import com.macower.core.entity.Page;

public interface GuestBiz {


	public Page<Guest> findPageBy(Guest obj, Integer pageNo,
			Integer pageSize);
	
	public List<Guest> findBy(Guest obj);

	public void save(Guest obj);

	public void update(Guest obj);

	public void deletes(String ids);
	
	public Guest get(Long id) ;

	public int sycnData();

	public int sycnMemberData();

	public void init();

	public void resetOrderData();


}
