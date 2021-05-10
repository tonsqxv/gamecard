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
@Table(name = "ts_module")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Module extends BaseEntity{
	
	private static final long serialVersionUID = -6334519060313918541L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="MODULE_ID")
	private Long moduleId ;
	
	@Column(name="PARENT_ID")
	private Long parentId ;
	
	@Column(name="MODULE_NAME")
	private String moduleName ;
	
	@Column(name="MODULE_CODE")
	private String moduleCode ;
	
	@Column(name="ACTION_URL")
	private String actionUrl ;
	
	@Column(name="is_leaf")
	private Integer isLeaf ;
	
	@Column(name="SORT_NO")
	private Integer sort ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	
}
