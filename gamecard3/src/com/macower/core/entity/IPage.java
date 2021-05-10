package com.macower.core.entity;

import java.util.Collection;

public abstract interface IPage<T> extends Collection<T>{
	public abstract long getTotalSize();

	public abstract Collection<T> getData();

	public abstract int getPageSize();

	public abstract long getTotalPage();

	public abstract long getCurrentPage();

	public abstract boolean isFirstPage();

	public abstract boolean isLastPage();

	public abstract long getPageBegin();

	public abstract long getPageEnd();
}
