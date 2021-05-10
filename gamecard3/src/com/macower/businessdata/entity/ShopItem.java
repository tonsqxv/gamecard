package com.macower.businessdata.entity;

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
@Table(name = "tb_shop_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShopItem extends BaseEntity {

	private static final long serialVersionUID = -3385583189916636272L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	// 产品id
	@Column(name = "product_id")
	private Long productId;
	// 产品主圖片
	@Formula("(select t.main_img_path from tm_product t where t.id = product_id)")
	private String cardMainImg;

	// 产品名称
	@Formula("(select t.product_name from tm_product t where t.id = product_id)")
	private String productName;

	// 会员id
	@Column(name = "member_id")
	private Long memberId;

	// 件数
	@Column(name = "amount")
	private Integer amount;

	// 单价
	@Column(name = "unit_price")
	private Double unitPrice;

	// 商品原单价
	@Formula("(select t.pre_sell_price from tm_product t where t.id = product_id)")
	private Double preSellPrice;

	// 商品总价=（销售价格*件数）
	@Formula("(unit_price*amount)")
	private Double itemTotal;

	// sessionId
	@Column(name = "session_id")
	private String sessionId;

	// 创建时间
	@Column(name = "create_tm")
	private Date createTm;

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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCardMainImg() {
		return cardMainImg;
	}

	public void setCardMainImg(String cardMainImg) {
		this.cardMainImg = cardMainImg;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Double getPreSellPrice() {
		return preSellPrice;
	}

	public void setPreSellPrice(Double preSellPrice) {
		this.preSellPrice = preSellPrice;
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
