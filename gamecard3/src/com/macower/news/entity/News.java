package com.macower.news.entity;

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
@Table(name = "tb_news")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class News extends BaseEntity{
	
	private static final long serialVersionUID = -1643047041202896272L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="news_category_id")
	private Long newsCategoryId ;
	
	@Formula("(select t.name from tb_news_category t where t.id = news_category_id)")
	private String newsCategoryName ;
	
	@Column(name="title")
	private String title ;
	
	@Column(name="sub_title")
	private String subTitle ;
	
	@Column(name="context")
	private String context ;
	
	@Column(name="create_tm")
	private Date createTm ;
	
	@Column(name="status")
	private Integer status ;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(Long newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public String getNewsCategoryName() {
		return newsCategoryName;
	}

	public void setNewsCategoryName(String newsCategoryName) {
		this.newsCategoryName = newsCategoryName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
