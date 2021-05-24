package com.macower.basedata.biz;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dao.AddressLogDaoImpl;
import com.macower.basedata.entity.AddressLog;
import com.macower.core.biz.BaseBiz;

@Service
public class AddressLogBizImpl extends BaseBiz implements AddressLogBiz {

	@Autowired
	private AddressLogDaoImpl addressLogDao ;
	
	@Override
	public void save(AddressLog obj) {
		addressLogDao.save(obj) ;

	}

	@Override
	public List<AddressLog> findBy(AddressLog obj) {
		return this.addressLogDao.findBy(obj);
	}

	@Override
	public void deleteBatch(Collection<AddressLog> entities) {
		this.addressLogDao.deleteBatch(entities) ;
	}


}
