package com.macower.basedata.biz;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dao.BrandDaoImpl;
import com.macower.basedata.entity.Brand;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;

@Service
public class BrandBizImpl extends BaseBiz implements BrandBiz {

	@Autowired
	private BrandDaoImpl brandDao ;
	
	@Override
	public Page<Brand> findPageBy(Brand obj, Integer pageNo,
			Integer pageSize) {
		return brandDao.findPageBy(obj, pageNo, pageSize) ;
	}
	
	public List<Brand> findBy(Brand obj){
		return this.brandDao.findBy(obj) ;
	}

	@Override
	public void save(Brand obj) {
		Brand param = new Brand() ;
		param.setBrand(obj.getBrand()) ;
		int count  = this.brandDao.countBy(param) ;
		if(count > 0){
			throw new BizException(obj.getBrand()+"已经存在") ;
		}
		
		brandDao.save(obj) ;

	}

	@Override
	public void update(Brand obj) {
		Brand old = this.brandDao.get(obj.getId()) ;
		if(!old.getBrand().equals(obj.getBrand())){
			Brand param = new Brand() ;
			param.setBrand(obj.getBrand()) ;
			int count  = this.brandDao.countBy(param) ;
			if(count > 0){
				throw new BizException(obj.getBrand()+"已经存在") ;
			}
		}
		old.setBrand(obj.getBrand()) ;
		
		brandDao.update(old) ;

	}

	@Override
	public void deletes(String ids) {
		this.brandDao.deleteByIds(ids) ;
	}

	/**
	 * 获取前台展示品牌列表
	 */
	@Override
	public List<Brand> getBrandList(Integer size) {
		//游戏卡种类
		DetachedCriteria dc = DetachedCriteria.forClass(Brand.class) ;
		Page<Brand> page =  this.brandDao.findPageBy(dc, 0, size) ;
		return (List<Brand>)page.getData() ;
	}

}
