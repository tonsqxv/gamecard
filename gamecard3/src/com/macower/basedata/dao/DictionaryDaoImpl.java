package com.macower.basedata.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.Dictionary;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class DictionaryDaoImpl extends BaseEntityDao<Dictionary> {

	public Page<Dictionary> findPageBy(Dictionary obj, Integer pageNo,
			Integer pageSize) {
		DetachedCriteria dc = gerateDetachedCriteria(obj) ;

		return this.findPageBy(dc, pageNo, pageSize);
	}

	private DetachedCriteria gerateDetachedCriteria(Dictionary obj) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dictionary.class);

		if (StringUtils.isNotEmpty(obj.getDicType())) {
			dc.add(Restrictions.eq("dicType", obj.getDicType()));
		}

		if (StringUtils.isNotEmpty(obj.getDicCode())) {
			dc.add(Restrictions.eq("dicCode", obj.getDicCode()));
		}
		dc.addOrder(Order.asc("sort")) ;
		return dc ;
	}

	public List<Dictionary> findBy(Dictionary obj) {
		DetachedCriteria dc = gerateDetachedCriteria(obj) ;
		return this.findBy(dc);
	}

}
