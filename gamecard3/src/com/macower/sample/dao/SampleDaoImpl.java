package com.macower.sample.dao ;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.macower.core.dao.BaseEntityDao;
import com.macower.sample.entity.Sample;

@Repository
public class SampleDaoImpl extends BaseEntityDao<Sample> {

	public List<Sample> listPageBy(Sample obj) {
		DetachedCriteria dc = DetachedCriteria.forClass(Sample.class);
		return this.findBy(dc) ;
	}

	
}
