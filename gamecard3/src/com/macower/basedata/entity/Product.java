package com.macower.basedata.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import com.macower.core.dao.BaseEntity;

@Entity
@Table(name = "tm_product")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends BaseEntity{
	
	private static final long serialVersionUID = 2266508180459384385L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="product_no")
	private String productNo ;
	
	@Column(name="category_id")
	private Long categoryId ;
	
	@Column(name="brand")
	private String brand ;
	  
	@Formula(value="(select t.name from tm_category t where t.id = category_id)")
	private String categoryName ;
	
	@Column(name="short_name")
	private String shortName ;
	
	@Column(name="product_name")
	private String productName ;
	
	@Column(name="main_img_path")
	private String mainImgPath ;
	
	@Column(name="main_img_desc")
	private String mainImgDesc ;
	
	@Column(name="base_price")
	private Double basePrice ;
	
	@Column(name="pre_sell_price")
	private Double preSellPrice ;
	
	@Column(name="actual_sell_price")
	private Double actualSellPrice ;
	
	@Column(name="weight")
	private Double weight ;
	
	@Column(name="available")
	private Integer available ;
	
	@Column(name="sales")
	private Integer sales ;
	
	@Column(name="base_sales")
	private Integer baseSales ;
	
	@Column(name="provider")
	private String provider ;
	
	//@Pattern(regexp="yyyy-MM-dd")
	//@Temporal(TemporalType.DATE)
	@Column(name="purchase_tm")
	private Date purchaseTm ;
	
	@Column(name="discount")
	private Double discount ;
	
	@Column(name="advice_product")
	private Integer isAdvice ;
	
	@Column(name="hot_product")
	private Integer isHot ;
	
	@Column(name="customer_like")
	private Integer isCustomerLike ;
	
	@Column(name="new_product")
	private Integer isNew ;
	
	@Column(name="discount_product")
	private Integer isDiscount ;
	
	@Column(name="down_flag")
	private Integer downFlag ;
	
	@Column(name="star")
	private Integer star ;
	
	@Column(name="hot_img")
	private String hotImgPath ;
	
	@Column(name="hot_desc")
	private String hotDesc ;
	
	@Column(name="product_desc")
	private String desc ;
	
	@Column(name="zoom_img_1")
	private String zoomImg1 ;
	
	@Column(name="zoom_img_2")
	private String zoomImg2 ;
	
	@Column(name="zoom_img_3")
	private String zoomImg3 ;
	
	@Column(name="zoom_img_4")
	private String zoomImg4 ;
	
	@Column(name="zoom_img_5")
	private String zoomImg5 ;
	
	@Column(name="zoom_img_6")
	private String zoomImg6 ;
	
	@Column(name="zoom_img_7")
	private String zoomImg7 ;
	
	@Column(name="zoom_img_8")
	private String zoomImg8 ;
	
	@Column(name="zoom_img_9")
	private String zoomImg9 ;
	
	@Column(name="zoom_img_10")
	private String zoomImg10 ;
	
	@Column(name="sort_no")
	private Integer sortNo ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getMainImgPath() {
		return mainImgPath;
	}

	public void setMainImgPath(String mainImgPath) {
		this.mainImgPath = mainImgPath;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getPreSellPrice() {
		return preSellPrice;
	}

	public void setPreSellPrice(Double preSellPrice) {
		this.preSellPrice = preSellPrice;
	}

	public Double getActualSellPrice() {
		return actualSellPrice;
	}

	public void setActualSellPrice(Double actualSellPrice) {
		this.actualSellPrice = actualSellPrice;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Date getPurchaseTm() {
		return purchaseTm;
	}

	public void setPurchaseTm(Date purchaseTm) {
		this.purchaseTm = purchaseTm;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getHotImgPath() {
		return hotImgPath;
	}

	public void setHotImgPath(String hotImgPath) {
		this.hotImgPath = hotImgPath;
	}

	public String getMainImgDesc() {
		return mainImgDesc;
	}

	public void setMainImgDesc(String mainImgDesc) {
		this.mainImgDesc = mainImgDesc;
	}



	public Integer getIsCustomerLike() {
		return isCustomerLike;
	}

	public void setIsCustomerLike(Integer isCustomerLike) {
		this.isCustomerLike = isCustomerLike;
	}


	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getBaseSales() {
		return baseSales;
	}

	public void setBaseSales(Integer baseSales) {
		this.baseSales = baseSales;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public String getZoomImg1() {
		return zoomImg1;
	}

	public void setZoomImg1(String zoomImg1) {
		this.zoomImg1 = zoomImg1;
	}

	public String getZoomImg2() {
		return zoomImg2;
	}

	public void setZoomImg2(String zoomImg2) {
		this.zoomImg2 = zoomImg2;
	}

	public String getZoomImg3() {
		return zoomImg3;
	}

	public void setZoomImg3(String zoomImg3) {
		this.zoomImg3 = zoomImg3;
	}

	public String getZoomImg4() {
		return zoomImg4;
	}

	public void setZoomImg4(String zoomImg4) {
		this.zoomImg4 = zoomImg4;
	}

	public String getZoomImg5() {
		return zoomImg5;
	}

	public void setZoomImg5(String zoomImg5) {
		this.zoomImg5 = zoomImg5;
	}

	public String getZoomImg6() {
		return zoomImg6;
	}

	public void setZoomImg6(String zoomImg6) {
		this.zoomImg6 = zoomImg6;
	}

	public String getZoomImg7() {
		return zoomImg7;
	}

	public void setZoomImg7(String zoomImg7) {
		this.zoomImg7 = zoomImg7;
	}

	public String getZoomImg8() {
		return zoomImg8;
	}

	public void setZoomImg8(String zoomImg8) {
		this.zoomImg8 = zoomImg8;
	}

	public String getZoomImg9() {
		return zoomImg9;
	}

	public void setZoomImg9(String zoomImg9) {
		this.zoomImg9 = zoomImg9;
	}

	public String getZoomImg10() {
		return zoomImg10;
	}

	public void setZoomImg10(String zoomImg10) {
		this.zoomImg10 = zoomImg10;
	}

	public String getHotDesc() {
		return hotDesc;
	}

	public void setHotDesc(String hotDesc) {
		this.hotDesc = hotDesc;
	}

	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Integer getDownFlag() {
		return downFlag;
	}

	public void setDownFlag(Integer downFlag) {
		this.downFlag = downFlag;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getIsAdvice() {
		return isAdvice;
	}

	public void setIsAdvice(Integer isAdvice) {
		this.isAdvice = isAdvice;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}

}
