package com.macower.businessdata.biz;


import java.util.List;


import com.macower.businessdata.entity.Contact;
import com.macower.core.entity.Page;

public interface ContactBiz {

	public Page<Contact> findPageBy(Contact obj, Integer pageNo,
			Integer pageSize);
	
	public List<Contact> findBy(Contact obj);

	public void save(Contact obj);

	public void deletes(String ids);
	
	public Contact get(Long id) ;

	public void replay(Contact obj);


}
