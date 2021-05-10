package com.macower.sample.biz ;


import java.util.List;

import com.macower.sample.entity.Sample;


public interface SampleBiz {
	
	public List<Sample> listBy(Sample obj);

	public void delete(String ids);
	public void update(Sample obj);
	public void save(Sample obj);

	public Sample show(Long id);

}
