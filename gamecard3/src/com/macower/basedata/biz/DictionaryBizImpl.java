package com.macower.basedata.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dao.DictionaryDaoImpl;
import com.macower.basedata.entity.Dictionary;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;

@Service
public class DictionaryBizImpl extends BaseBiz implements DictionaryBiz {

	@Autowired
	private DictionaryDaoImpl dictionaryBizDao ;
	
	@Override
	public Page<Dictionary> findPageBy(Dictionary obj, Integer pageNo,
			Integer pageSize) {
		return dictionaryBizDao.findPageBy(obj, pageNo, pageSize) ;
	}
	
	public List<Dictionary> findBy(Dictionary obj){
		return this.dictionaryBizDao.findBy(obj) ;
	}

	@Override
	public void save(Dictionary obj) {
		dictionaryBizDao.save(obj) ;

	}

	@Override
	public void update(Dictionary obj) {
		Dictionary old = this.dictionaryBizDao.get(obj.getId()) ;
		old.setDesc(obj.getDesc()) ;
		old.setDicCode(obj.getDicCode()) ;
		old.setDicType(obj.getDicType()) ;
		old.setDicValue(obj.getDicValue()) ;
		old.setSort(obj.getSort()) ;
		
		dictionaryBizDao.update(old) ;

	}

	@Override
	public void deletes(String ids) {
		this.dictionaryBizDao.deleteByIds(ids) ;
	}

}
