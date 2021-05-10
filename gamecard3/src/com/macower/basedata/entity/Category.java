package com.macower.basedata.entity;


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
@Table(name = "tm_category")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends BaseEntity{
	
	private static final long serialVersionUID = -2857953069831166320L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="name")
	private String name ;
	
	@Column(name="sort_num")
	private String sort ;

	@Column(name="parent_id")
	private Long parentId ;
	
	@Column(name="product_detail_tpl")
	private String productDetailTpl ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getProductDetailTpl() {
		return productDetailTpl;
	}

	public void setProductDetailTpl(String productDetailTpl) {
		this.productDetailTpl = productDetailTpl;
	}

}
