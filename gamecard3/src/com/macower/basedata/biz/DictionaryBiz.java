package com.macower.basedata.biz;


import java.util.List;

import com.macower.basedata.entity.Dictionary;
import com.macower.core.entity.Page;

public interface DictionaryBiz {

	public Page<Dictionary> findPageBy(Dictionary obj, Integer pageNo,
			Integer pageSize);

	public List<Dictionary> findBy(Dictionary obj);

	public void save(Dictionary obj);

	public void update(Dictionary obj);

	public void deletes(String ids);
	
}
