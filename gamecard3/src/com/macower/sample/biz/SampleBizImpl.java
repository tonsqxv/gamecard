package com.macower.sample.biz ;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.macower.sample.dao.SampleDaoImpl;
import com.macower.sample.entity.Sample;

@Service
public class SampleBizImpl implements SampleBiz {

	@Resource
	private SampleDaoImpl sampleDao ;

	@Override
	public List<Sample> listBy(Sample obj) {
		return sampleDao.listPageBy(obj) ;
	}

	@Override
	public void delete(String ids) {
		 sampleDao.deleteByIds(ids) ;
		
	}

	@Override
	public void update(Sample obj) {
		sampleDao.update(obj) ;
		
	}

	@Override
	public void save(Sample obj) {
		sampleDao.save(obj) ;
		
	}

	@Override
	public Sample show(Long id) {
		return this.sampleDao.get(id) ;
		
	}
	

}
