package com.macower.businessdata.biz;


import java.util.List;


import com.macower.businessdata.entity.Discode;
import com.macower.core.entity.Page;

public interface DiscodeBiz {

	public Page<Discode> findPageBy(Discode obj, Integer pageNo,
			Integer pageSize);
	
	public List<Discode> findBy(Discode obj);

	public void createBatch(Integer count);

	public void deletes(String ids);
	
	public Discode get(Long id) ;

	public void emailSendDiscode(String email);

}
