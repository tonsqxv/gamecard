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

import com.macower.core.dao.BaseEntity;

@Entity
@Table(name = "tb_discount_code")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Discode extends BaseEntity{

	private static final long serialVersionUID = 2598770031705590399L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;

	@Column(name="discode")
	private String discode ;
	
	@Column(name="status")
	private Integer status ;
	
	@Column(name="create_tm")
	private Date createTm ;
	
	@Column(name="effect_tm")
	private Date effectTm ;
	
	@Column(name="use_tm")
	private Date useTm ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getEffectTm() {
		return effectTm;
	}

	public void setEffectTm(Date effectTm) {
		this.effectTm = effectTm;
	}

	public Date getUseTm() {
		return useTm;
	}

	public void setUseTm(Date useTm) {
		this.useTm = useTm;
	}

	
}
