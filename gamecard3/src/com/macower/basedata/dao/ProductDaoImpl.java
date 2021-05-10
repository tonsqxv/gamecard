package com.macower.basedata.dao;



import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.basedata.entity.Product;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class ProductDaoImpl extends BaseEntityDao<Product>{

	public Page<Product> findPageBy(Product obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		if(StringUtils.isNotEmpty(obj.getShortName())){
			dc.add(Restrictions.like("shortName", obj.getShortName(),MatchMode.ANYWHERE)) ;
		}
		if(StringUtils.isNotEmpty(obj.getProductNo())){
			dc.add(Restrictions.like("productNo", obj.getProductNo(),MatchMode.ANYWHERE)) ;
		}
		if(StringUtils.isNotEmpty(obj.getProductName())){
			dc.add(Restrictions.like("productName", obj.getProductName(),MatchMode.ANYWHERE)) ;
		}
		if(obj.getCategoryId() != null){
			dc.add(Restrictions.eq("categoryId", obj.getCategoryId())) ;
		}
		if(StringUtils.isNotEmpty(obj.getProvider())){
			dc.add(Restrictions.like("provider", obj.getProvider(),MatchMode.ANYWHERE)) ;
		}
		
		if(obj.getIsAdvice() != null){
			dc.add(Restrictions.eq("isAdvice", obj.getIsAdvice())) ;
		}
		if(obj.getIsHot() != null){
			dc.add(Restrictions.eq("isHot", obj.getIsHot())) ;
		}
		if(obj.getIsCustomerLike() != null){
			dc.add(Restrictions.eq("isCustomerLike", obj.getIsCustomerLike())) ;
		}
		if(obj.getIsNew() != null){
			dc.add(Restrictions.eq("isNew", obj.getIsNew())) ;
		}
		if(obj.getDownFlag() != null){
			dc.add(Restrictions.eq("downFlag", obj.getDownFlag())) ;
		}
		if(obj.getIsDiscount() != null){
			dc.add(Restrictions.eq("isDiscount", obj.getIsDiscount())) ;
		}
		dc.add(Restrictions.eq("downFlag", 0)) ;
		dc.addOrder(Order.desc("id")) ;
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}
	
	
	/**
	 * 前台主产品显示列表
	 */
	public List<Product> featuredProducts(Integer size) {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class) ;
		dc.addOrder(Order.asc("sortNo")) ;
		Page<Product> page =  this.findPageBy(dc, 0, size) ;
		return (List<Product>)page.getData() ;
	}
	
	
	
	/**
	 * 获取首页推介的产品列表
	 */
	public List<Product> getAdvicedProductList(Integer size) {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class) ;
		dc.add(Restrictions.eq("isAdvice", 1)) ;
		Page<Product> page =  this.findPageBy(dc, 0, size) ;
		return (List<Product>)page.getData() ;
	}

}
