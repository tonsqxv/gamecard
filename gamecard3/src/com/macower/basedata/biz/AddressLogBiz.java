package com.macower.basedata.biz;


import java.util.Collection;
import java.util.List;

import com.macower.basedata.entity.AddressLog;

public interface AddressLogBiz {


	public void save(AddressLog obj);

	public List<AddressLog> findBy(AddressLog obj) ;
	
	public void deleteBatch(Collection<AddressLog> entities)  ;

}
