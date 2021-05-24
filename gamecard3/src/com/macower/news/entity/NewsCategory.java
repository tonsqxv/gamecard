package com.macower.news.entity;

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
@Table(name = "tb_news_category")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsCategory extends BaseEntity{
	
	private static final long serialVersionUID = -1643047041202896272L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="parent_id")
	private Long parentId ;
	
	@Column(name="name")
	private String name ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
