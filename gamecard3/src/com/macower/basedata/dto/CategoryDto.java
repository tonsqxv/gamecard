package com.macower.basedata.dto;

import java.util.Collection;

import com.macower.basedata.entity.Category;

@SuppressWarnings("rawtypes")
public class CategoryDto extends Category{

	private static final long serialVersionUID = 934429808481629247L;
	private Collection children ;

	public Collection getChildren() {
		return children;
	}

	public void setChildren(Collection children) {
		this.children = children;
	}
}
