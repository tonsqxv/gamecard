package com.macower.basedata.entity;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class CategoryTree {
	
	private Long id ;
	
	private String text ;
	
	private String cls ;
	
	private Boolean leaf ;

	private Collection children ;

	
	public Collection getChildren() {
		return children;
	}

	public void setChildren(Collection children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
}
