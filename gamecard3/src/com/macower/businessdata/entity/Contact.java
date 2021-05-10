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
@Table(name = "tb_contact")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contact extends BaseEntity{
	
	private static final long serialVersionUID = 8520195116065854047L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;

	@Column(name="full_name")
	private String fullName ;
	
	@Column(name="email")
	private String email ;
	
	@Column(name="company")
	private String company ;

	@Column(name="phone_number")
	private String phoneNumber ;

	@Column(name="message")
	private String message ;
	
	@Column(name="create_tm")
	private Date createTm ;
	
	@Column(name="reply_tm")
	private Date replyTm ;
	
	@Column(name="status")
	private Integer status ;
	
	@Column(name="reply_msg")
	private String replyMsg ;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getReplyTm() {
		return replyTm;
	}

	public void setReplyTm(Date replyTm) {
		this.replyTm = replyTm;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}
	
	
}
