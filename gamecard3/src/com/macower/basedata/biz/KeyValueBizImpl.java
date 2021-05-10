package com.macower.basedata.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dao.KeyValueDaoImpl;
import com.macower.basedata.entity.KeyValue;
import com.macower.core.biz.BaseBiz;

@Service
public class KeyValueBizImpl extends BaseBiz implements KeyValueBiz {

	@Autowired
	private KeyValueDaoImpl keyValueDao ;
	
	
	public List<KeyValue> findBy(KeyValue obj){
		return this.keyValueDao.findBy(obj) ;
	}

	@Override
	public void save(KeyValue obj) {
		keyValueDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.keyValueDao.deleteByIds(ids) ;
	}

	
}
