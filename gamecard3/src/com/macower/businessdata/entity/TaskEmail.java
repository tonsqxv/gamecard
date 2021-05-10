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
@Table(name = "tb_task_email")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TaskEmail extends BaseEntity{
	
	private static final long serialVersionUID = 2266508180459384385L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="email")
	private String email ;
	
	@Column(name="status")
	private Integer status ;
	
	@Column(name="send_tm")
	private Date sendTm ;
	
	@Column(name="is_member")
	private Integer isMember ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSendTm() {
		return sendTm;
	}

	public void setSendTm(Date sendTm) {
		this.sendTm = sendTm;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsMember() {
		return isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

}
