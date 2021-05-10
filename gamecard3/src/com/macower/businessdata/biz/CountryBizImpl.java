package com.macower.businessdata.biz;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.businessdata.dao.CountryDaoImpl;
import com.macower.businessdata.entity.Country;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Service 
public class CountryBizImpl extends BaseBiz implements CountryBiz {

	@Autowired
	private CountryDaoImpl countryDao ;

	@Override
	public Page<Country> findPageBy(Country obj, Integer pageNo,
			Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Country.class) ;
		dc.addOrder(Order.asc("country")) ;
		return this.countryDao.findPageBy(dc, pageNo, pageSize) ;
	}

	@Override
	public List<Country> findBy(Country obj) {
		DetachedCriteria dc = DetachedCriteria.forClass(Country.class) ;
		dc.addOrder(Order.asc("country")) ;
		return this.countryDao.findBy(dc) ;
	}

	@Override
	public void deletes(String ids) {
		this.countryDao.deleteByIds(ids) ;
		
	}

	@Override
	public void save(Country obj) {
		this.countryDao.save(obj) ;
		
	}

	@Override
	public Country get(Long id) {
		return this.countryDao.get(id) ;
	}

	@Override
	public void update(Country obj) {
		this.countryDao.update(obj) ;
		
	}

	@Override
	public String findByCode(String code) {
		if(!StringUtils.isNotEmpty(code)){
			return null;
		}
		Country param = new Country() ;
		param.setCode(code.trim()) ;
		List<Country> list = this.countryDao.findBy(param) ;
		if(list !=null && list.size() > 0){
			return list.get(0).getCountry() ;
		}
		return null ;
	}
	


}
