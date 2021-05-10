package com.macower.sys.entity;

import java.util.Collection;


@SuppressWarnings("rawtypes")
public class SyncTree extends AsyncTree{
	
	private Collection children ;

	
	public Collection getChildren() {
		return children;
	}

	public void setChildren(Collection children) {
		this.children = children;
	}
}
