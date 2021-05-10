package com.macower.basedata.biz;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.dao.KeyCategoryDaoImpl;
import com.macower.basedata.dao.KeyValueDaoImpl;
import com.macower.basedata.dao.ProductDaoImpl;
import com.macower.basedata.entity.KeyCategory;
import com.macower.basedata.entity.KeyValue;
import com.macower.basedata.entity.Product;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.FileUtils;
import com.macower.core.util.JsonUtils;
import com.macower.core.util.StringUtils;

@Service
public class ProductBizImpl extends BaseBiz implements ProductBiz {

	@Autowired
	private ProductDaoImpl productDao ;
	
	@Autowired
	private KeyCategoryDaoImpl keyCategoryDao ;
	
	@Autowired
	private KeyValueDaoImpl keyValueDao ;
	
	
	@Override
	public Page<Product> findPageBy(Product obj, Integer pageNo,
			Integer pageSize) {
		return productDao.findPageBy(obj, pageNo, pageSize) ;
	}

	/**
	 * 根据分类查找
	 */
	@Override
	public List<Product> findByCategory(Long cardTypeId) {
		Product param  = new Product() ;
		param.setCategoryId(cardTypeId) ;
		param.setDownFlag(0) ;
		return productDao.findBy(param) ;
	}


	@Override
	public void save(Product obj,HttpServletRequest request) {
		//生成产品编号
		obj.setProductNo("NO"+new Date().getTime()) ;
		obj.setDownFlag(0) ;
		productDao.save(obj) ;
		try {
			String colorParam = request.getParameter("colorParams") ;
			List<KeyCategory> list = null ;
			if(StringUtils.isNotEmpty(colorParam)){
				list = JsonUtils.jsonToList(colorParam, KeyCategory.class) ;
			}
			if(list !=null && list.size() > 0){
				for(KeyCategory k : list){
					k.setType(1) ; //颜色
					k.setProductId(obj.getId()) ;
					keyCategoryDao.save(k) ;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace() ;
			throw new BizException("保存颜色列表出现异常") ;
		}
		/***********************/
		try {
			String sizeParams = request.getParameter("sizeParams") ;
			List<KeyCategory> list = null ;
			if(StringUtils.isNotEmpty(sizeParams)){
				list = JsonUtils.jsonToList(sizeParams, KeyCategory.class) ;
			}
			if(list !=null && list.size() > 0){
				for(KeyCategory k : list){
					k.setType(2) ; //尺寸
					k.setProductId(obj.getId()) ;
					keyCategoryDao.save(k) ;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace() ;
			throw new BizException("保存颜色列表出现异常") ;
		}
		/*********************/
		try {
			String kvParams = request.getParameter("kvParams") ;
			List<KeyValue> list = null ;
			if(StringUtils.isNotEmpty(kvParams)){
				list = JsonUtils.jsonToList(kvParams, KeyValue.class) ;
			}
			if(list !=null && list.size() > 0){
				for(KeyValue k : list){
					k.setProductId(obj.getId()) ;
					keyValueDao.save(k) ;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace() ;
			throw new BizException("保存特性值列表出现异常") ;
		}

	}

	@Override
	public void update(Product obj,HttpServletRequest request) {
		Product old = productDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		old.setShortName(obj.getShortName()) ;
		old.setProductName(obj.getProductName()) ;
		old.setCategoryId(obj.getCategoryId()) ;
		old.setBasePrice(obj.getBasePrice()) ;
		old.setPreSellPrice(obj.getPreSellPrice()) ;
		old.setActualSellPrice(obj.getActualSellPrice()) ;
		old.setWeight(obj.getWeight()) ;
		old.setAvailable(obj.getAvailable()) ;
		old.setSales(obj.getSales()) ;
		old.setBaseSales(obj.getBaseSales()) ;
		old.setProvider(obj.getProvider()) ;
		old.setPurchaseTm(obj.getPurchaseTm()) ;
		old.setDiscount(obj.getDiscount()) ;
		old.setIsAdvice(obj.getIsAdvice()) ;
		old.setIsCustomerLike(obj.getIsCustomerLike()) ;
		old.setIsHot(obj.getIsHot()) ;
		old.setStar(obj.getStar()) ;
		old.setIsNew(obj.getIsNew()) ;
		old.setBrand(obj.getBrand()) ;
		old.setDownFlag(obj.getDownFlag()) ;
		old.setIsDiscount(obj.getIsDiscount()) ;

		productDao.update(old) ;
		
		try {
			String colorParam = request.getParameter("colorParams") ;
			System.out.println(colorParam);
			List<KeyCategory> list = null ;
			if(StringUtils.isNotEmpty(colorParam)){
				list = JsonUtils.jsonToList(colorParam, KeyCategory.class) ;
			}
			//删除原有的颜色  新增新的颜色
			keyCategoryDao.deleteByProductIdAndType(obj.getId(),1) ; //type 1:颜色
			if(list !=null && list.size() > 0){
				for(KeyCategory k : list){
					k.setType(1) ; //颜色
					k.setProductId(obj.getId()) ;
					keyCategoryDao.save(k) ;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace() ;
			throw new BizException("保存颜色列表出现异常") ;
		}
		
		try {
			String sizeParams = request.getParameter("sizeParams") ;
			System.out.println(sizeParams);
			List<KeyCategory> list = null ;
			if(StringUtils.isNotEmpty(sizeParams)){
				list = JsonUtils.jsonToList(sizeParams, KeyCategory.class) ;
			}
			//删除原有的尺寸  新增新的尺寸
			keyCategoryDao.deleteByProductIdAndType(obj.getId(),2) ;  //type 2:尺寸
			if(list !=null && list.size() > 0){
				for(KeyCategory k : list){
					k.setType(2) ; //尺寸
					k.setProductId(obj.getId()) ;
					keyCategoryDao.save(k) ;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace() ;
			throw new BizException("保存尺寸列表出现异常") ;
		}
		
		
		try {
			String kvParams = request.getParameter("kvParams") ;
			System.out.println(kvParams);
			List<KeyValue> list = null ;
			if(StringUtils.isNotEmpty(kvParams)){
				list = JsonUtils.jsonToList(kvParams, KeyValue.class) ;
			}
			//删除原有的特性值  新增新的特性值
			keyValueDao.deleteByProductId(obj.getId()) ;
			if(list !=null && list.size() > 0){
				for(KeyValue k : list){
					k.setProductId(obj.getId()) ;
					keyValueDao.save(k) ;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace() ;
			throw new BizException("保存特性值列表出现异常") ;
		}
		

	}
	@Override
	public void update(Product obj) {
		Product old = productDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		old.setShortName(obj.getShortName()) ;
		old.setProductName(obj.getProductName()) ;
		old.setCategoryId(obj.getCategoryId()) ;
		old.setBasePrice(obj.getBasePrice()) ;
		old.setPreSellPrice(obj.getPreSellPrice()) ;
		old.setActualSellPrice(obj.getActualSellPrice()) ;
		old.setWeight(obj.getWeight()) ;
		old.setAvailable(obj.getAvailable()) ;
		old.setSales(obj.getSales()) ;
		old.setBaseSales(obj.getBaseSales()) ;
		old.setProvider(obj.getProvider()) ;
		old.setPurchaseTm(obj.getPurchaseTm()) ;
		old.setDiscount(obj.getDiscount()) ;
		old.setIsAdvice(obj.getIsAdvice()) ;
		old.setIsCustomerLike(obj.getIsCustomerLike()) ;
		old.setIsHot(obj.getIsHot()) ;
		old.setStar(obj.getStar()) ;
		old.setIsNew(obj.getIsNew()) ;
		old.setBrand(obj.getBrand()) ;
		old.setDownFlag(obj.getDownFlag()) ;
		old.setIsDiscount(obj.getIsDiscount()) ;
		
		productDao.update(old) ;
		
	}
	
	@Override
	public void updateDesc(Product obj) {
		Product old = productDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		old.setDesc(obj.getDesc()) ;
		productDao.update(old) ;
	}

	@Override
	public void deletes(String imgRootPath,String ids) {
		
		//删除对应的图片
		String[] arr = ids.split(",") ;
		Product card = null ;
		for(String s : arr){
			card = this.productDao.get(Long.parseLong(s)) ;
			if(StringUtils.isNotEmpty(card.getMainImgPath())){
				//主图片
				FileUtils.deleteFile(imgRootPath+card.getMainImgPath()) ; 
			}
			if(StringUtils.isNotEmpty(card.getHotImgPath())){
				//首页推介图片 
				FileUtils.deleteFile(imgRootPath+card.getHotImgPath()) ; 
			}
			//缩略图
			if(StringUtils.isNotEmpty(card.getZoomImg1())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg1()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg2())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg2()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg3())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg3()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg4())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg4()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg5())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg5()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg6())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg6()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg7())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg7()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg8())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg8()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg9())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg9()) ; 
			}
			if(StringUtils.isNotEmpty(card.getZoomImg10())){
				FileUtils.deleteFile(imgRootPath+card.getZoomImg10()) ; 
			}
			//删除对应的颜色属性
			keyCategoryDao.deleteByProductIdAndType(card.getId(), 1) ;
			//删除对应的尺寸数据
			keyCategoryDao.deleteByProductIdAndType(card.getId(), 2) ;
			//删除特性值
			keyValueDao.deleteByProductId(card.getId()) ;
		}
		this.productDao.deleteByIds(ids) ;
	}
	

	@Override
	public Product get(Long id) {
		return this.productDao.get(id) ;
	}
	

	@Override
	public Product load(Long id) {
		return this.productDao.load(id) ;
	}


	@Override
	public void updateImg(Product obj) {
		Product old = this.productDao.get(obj.getId()) ;
		//图片命名规则：分类id_游戏卡id_XX
		if(StringUtils.isNotEmpty(obj.getMainImgPath())){
			old.setMainImgPath(obj.getMainImgPath()) ;
		}
		if(StringUtils.isNotEmpty(obj.getHotImgPath())){
			old.setHotImgPath(obj.getHotImgPath()) ;
		}
		old.setMainImgDesc(obj.getMainImgDesc()) ;
		
		old.setHotDesc(obj.getHotDesc()) ;
		
		this.productDao.update(old) ;
		
	}
	
	/**
	 * 更新缩略图信息
	 */
	@Override
	public void updateZoomImg(Product obj) {
		Product old = this.productDao.get(obj.getId()) ;
		if(StringUtils.isNotEmpty(obj.getZoomImg1())){
			old.setZoomImg1(obj.getZoomImg1()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg2())){
			old.setZoomImg2(obj.getZoomImg2()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg3())){
			old.setZoomImg3(obj.getZoomImg3()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg4())){
			old.setZoomImg4(obj.getZoomImg4()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg5())){
			old.setZoomImg5(obj.getZoomImg5()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg6())){
			old.setZoomImg6(obj.getZoomImg6()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg7())){
			old.setZoomImg7(obj.getZoomImg7()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg8())){
			old.setZoomImg8(obj.getZoomImg8()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg9())){
			old.setZoomImg9(obj.getZoomImg9()) ;
		}
		if(StringUtils.isNotEmpty(obj.getZoomImg10())){
			old.setZoomImg10(obj.getZoomImg10()) ;
		}
		this.productDao.update(old) ;
	}
	

	@Override
	public void updateSortNo(Product obj) {
		Product old = productDao.get(obj.getId()) ;
		if(old == null){
			throw new BizException("记录已被删除");
		}
		old.setSortNo(obj.getSortNo()) ;
		productDao.update(old) ;
		
	}

	
	/**
	 * 前台主产品显示列表
	 */
	@Override
	public List<Product> featuredProducts(Integer size) {
		return this.productDao.featuredProducts(size);
	}
	
	
	/**
	 * 获取首页推介的产品列表
	 */
	@Override
	public List<Product> getAdvicedProductList(Integer size) {
		return this.productDao.getAdvicedProductList(size) ;
	}

	

	
}
