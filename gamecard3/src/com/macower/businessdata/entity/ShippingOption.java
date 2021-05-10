package com.macower.businessdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.macower.core.dao.BaseEntity;

@Entity
@Table(name = "tb_shipping_option")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShippingOption extends BaseEntity{
	
	private static final long serialVersionUID = 2714244348166083200L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;

	@Column(name="shipping_option_name")
	private String shippingOptionName ;
	
	@Column(name="shipping_option_amount")
	private Double shippingOptionAmount ;
	
	@Column(name="shipping_option_desc")
	private String shippingOptionDesc ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShippingOptionName() {
		return shippingOptionName;
	}

	public void setShippingOptionName(String shippingOptionName) {
		this.shippingOptionName = shippingOptionName;
	}

	public Double getShippingOptionAmount() {
		return shippingOptionAmount;
	}

	public void setShippingOptionAmount(Double shippingOptionAmount) {
		this.shippingOptionAmount = shippingOptionAmount;
	}

	public String getShippingOptionDesc() {
		return shippingOptionDesc;
	}

	public void setShippingOptionDesc(String shippingOptionDesc) {
		this.shippingOptionDesc = shippingOptionDesc;
	}

	
}
