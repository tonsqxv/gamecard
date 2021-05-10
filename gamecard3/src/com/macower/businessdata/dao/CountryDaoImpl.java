package com.macower.businessdata.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.entity.Country;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class CountryDaoImpl extends BaseEntityDao<Country> {

	public Page<Country> findPageBy(Country obj, Integer pageNo,
			Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Country.class);

		if (StringUtils.isNotEmpty(obj.getCode())) {
			dc.add(Restrictions.like("code", obj.getCode(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotEmpty(obj.getCountry())) {
			dc.add(Restrictions.like("country", obj.getCountry(),
					MatchMode.ANYWHERE));
		}

		return this.findPageBy(dc, pageNo, pageSize);
	}
}
