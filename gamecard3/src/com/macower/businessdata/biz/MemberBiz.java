package com.macower.businessdata.biz;


import java.util.List;

import com.macower.businessdata.entity.Member;
import com.macower.core.entity.Page;

public interface MemberBiz {


	public Page<Member> findPageBy(Member obj, Integer pageNo,
			Integer pageSize);
	
	public List<Member> findBy(Member obj);

	public void save(Member obj);

	public void updateBy(Member obj);
	
	public void update(Member obj);

	public void deletes(String ids);
	
	public Member get(Long id) ;

	public void init();


}
