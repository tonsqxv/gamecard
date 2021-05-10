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
@Table(name = "tb_member")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Member extends BaseEntity{
	
	private static final long serialVersionUID = -3385583189916636272L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;

	@Column(name="email")
	private String email ;
	
	@Column(name="password")
	private String password ;
	
	@Column(name="first_name")
	private String firstName ;

	@Column(name="last_name")
	private String lastName ;

	@Column(name="phone_number")
	private String phoneNumber ;
	
	@Column(name="locked")
	private Integer locked ;
	
	@Column(name="login_tm")
	private Date loginTm ;
	
	@Column(name="login_count")
	private Integer loginCount ;
	
	@Column(name="register_tm")
	private Date registerTm ;
	

	@Column(name="task_status")
	private Integer taskStatus ;
	
	@Column(name="send_tm")
	private Date sendTm ;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLoginTm() {
		return loginTm;
	}

	public void setLoginTm(Date loginTm) {
		this.loginTm = loginTm;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getRegisterTm() {
		return registerTm;
	}

	public void setRegisterTm(Date registerTm) {
		this.registerTm = registerTm;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getSendTm() {
		return sendTm;
	}

	public void setSendTm(Date sendTm) {
		this.sendTm = sendTm;
	}


	
}
