package com.macower.basedata.dao ;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.Brand;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class BrandDaoImpl extends BaseEntityDao<Brand> {


	public Page<Brand> findPageBy(Brand obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Brand.class);
		
		if(StringUtils.isNotEmpty(obj.getBrand())){
			dc.add(Restrictions.like("brand", obj.getBrand(),MatchMode.ANYWHERE)) ;
		}
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

}
