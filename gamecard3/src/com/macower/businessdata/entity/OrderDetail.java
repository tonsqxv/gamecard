package com.macower.businessdata.entity;

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
@Table(name = "tb_order_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderDetail extends BaseEntity {

	private static final long serialVersionUID = 257011881570025425L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "order_id")
	private Long orderId;

	@Formula("(select t.order_no from tb_order t where t.id = order_id)")
	private String orderNo;

	@Column(name = "product_id")
	private Long productId;

	@Formula("(select t.product_no from tm_product t where t.id = product_id)")
	private String productNo;

	@Formula("(select t.main_img_path from tm_product t where t.id = product_id)")
	private String mainImgPath;

	@Formula("(select t.product_name from tm_product t where t.id = product_id)")
	private String productName;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "unit_price")
	private Double unitPrice;

	@Column(name = "base_price")
	private Double basePrice;

	@Formula("(unit_price*amount)")
	private Double itemTotal;

	// 颜色
	@Column(name = "color")
	private String color;

	// 尺码
	@Column(name = "size")
	private String size;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getMainImgPath() {
		return mainImgPath;
	}

	public void setMainImgPath(String mainImgPath) {
		this.mainImgPath = mainImgPath;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
