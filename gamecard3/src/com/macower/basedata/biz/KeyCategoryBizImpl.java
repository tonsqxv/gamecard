package com.macower.basedata.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dao.KeyCategoryDaoImpl;
import com.macower.basedata.entity.KeyCategory;
import com.macower.core.biz.BaseBiz;

@Service
public class KeyCategoryBizImpl extends BaseBiz implements KeyCategoryBiz {

	@Autowired
	private KeyCategoryDaoImpl keyCategoryDao ;
	
	
	public List<KeyCategory> findBy(KeyCategory obj){
		return this.keyCategoryDao.findBy(obj) ;
	}

	@Override
	public void save(KeyCategory obj) {
		keyCategoryDao.save(obj) ;

	}

	@Override
	public void deletes(String ids) {
		this.keyCategoryDao.deleteByIds(ids) ;
	}

	
}
