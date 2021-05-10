package com.macower.basedata.biz;


import java.util.List;

import com.macower.basedata.entity.KeyValue;

public interface KeyValueBiz {

	public List<KeyValue> findBy(KeyValue obj);

	public void save(KeyValue obj);

	public void deletes(String ids);

}
