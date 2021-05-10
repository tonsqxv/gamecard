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
@Table(name = "tb_task")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends BaseEntity{
	
	private static final long serialVersionUID = 2266508180459384385L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="task_name")
	private String taskName ;
	
	@Column(name="email")
	private String email ;
	
	@Column(name="password")
	private String password ;
	
	@Column(name="status")
	private Integer status ;
	
	@Column(name="task_type")
	private Integer taskType ;
	
	@Column(name="subject")
	private String subject ;
	
	@Column(name="email_tpl")
	private String emailTpl ;
	  
	@Column(name="read_data_rows")
	private Integer readDataRows ;
	
	@Column(name="run_cycle")
	private Integer runCycle ;
	
	@Column(name="task_desc")
	private String desc ;
	
	@Column(name="start_tm")
	private Date startTm ;
	
	@Column(name="stop_tm")
	private Date stopTm ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailTpl() {
		return emailTpl;
	}

	public void setEmailTpl(String emailTpl) {
		this.emailTpl = emailTpl;
	}

	public Integer getReadDataRows() {
		return readDataRows;
	}

	public void setReadDataRows(Integer readDataRows) {
		this.readDataRows = readDataRows;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTm() {
		return startTm;
	}

	public void setStartTm(Date startTm) {
		this.startTm = startTm;
	}

	public Date getStopTm() {
		return stopTm;
	}

	public void setStopTm(Date stopTm) {
		this.stopTm = stopTm;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getRunCycle() {
		return runCycle;
	}

	public void setRunCycle(Integer runCycle) {
		this.runCycle = runCycle;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}


}
