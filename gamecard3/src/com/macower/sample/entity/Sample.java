package com.macower.sample.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.macower.core.dao.BaseEntity;

@Entity
@Table(name = "sample")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sample extends BaseEntity{
	
	private static final long serialVersionUID = -625306897068176761L;

	public Sample() {
		
	}
	public Sample(Long id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="username")
	private String username ;
	
	@Column(name="password")
	private String password ;
	
	@Pattern(regexp="(\\w+\\.*)\\w+@\\w+\\.[a-zA-z]{2,6}",message="邮箱格式不正确!")
	@Column(name="email")
	private String email ;
	
	@Column(name="create_tm")
	private Date createTm ;
	
	/*@ManyToOne(cascade = CascadeType.PERSIST,targetEntity=ArticleCategory.class)
	@JoinColumn(name="ARTICLE_CATEGORY_ID")	
	private ArticleCategory articleCategoryId;*/


	public Date getCreateTm() {
		return createTm;
	}
	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
