package com.macower.sys.entity;

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
@Table(name = "ts_user")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity{
	
	private static final long serialVersionUID = 7819339689040159276L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="user_name")
	private String userName ;
	
	@Column(name="password")
	private String password ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
