package com.macower.basedata.biz;


import java.util.List;

import com.macower.basedata.entity.KeyCategory;

public interface KeyCategoryBiz {

	public List<KeyCategory> findBy(KeyCategory obj);

	public void save(KeyCategory obj);

	public void deletes(String ids);

}
