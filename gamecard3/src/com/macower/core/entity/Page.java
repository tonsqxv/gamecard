package com.macower.core.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Page<T> implements IPage<T> {

	// 总大小
	private int totalSize;
	// 分页大小
	private int pageSize;
	// 总页数
	private long totalPage;
	// 当前页
	private long currentPage;

	private Collection<T> data;

	public Page(int totalSize, int pageSize, int currentPage, Collection<T> data) {
		super();
		this.data = (data == null ? new ArrayList<T>(0) : data);
		this.totalSize = totalSize;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = calcTotalPage();
	}

	private long calcTotalPage() {
		long t = getTotalSize();
		long p = getPageSize();
		if ((t == 0L) || (p == 0L)) {
			return 0L;
		}
		long r = t % p;
		long pages = (t - r) / p;
		if (r > 0L) {
			pages += 1L;
		}
		return pages;
	}

	@Override
	public long getTotalSize() {
		return this.totalSize;
	}

	@Override
	public Collection<T> getData() {
		return this.data;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public long getTotalPage() {
		return totalPage;
	}

	@Override
	public long getCurrentPage() {
		return currentPage;
	}

	@Override
	public long getPageBegin() {
		return this.pageSize * this.currentPage;
	}

	@Override
	public long getPageEnd() {
		return getPageBegin() + getData().size();
	}

	@Override
	public boolean isFirstPage() {
		return this.currentPage == 0L;
	}

	@Override
	public boolean isLastPage() {
		return this.currentPage == this.totalPage;
	}

	public boolean add(T o) {
		return this.data.add(o);
	}

	public boolean addAll(Collection<? extends T> c) {
		return this.data.addAll(c);
	}

	public void clear() {
		this.data.clear();
	}

	public boolean contains(Object o) {
		return this.data.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.data.containsAll(c);
	}

	public boolean equals(Object o) {
		return this.data.equals(o);
	}

	public int hashCode() {
		return this.data.hashCode();
	}

	public boolean isEmpty() {
		return this.data.isEmpty();
	}

	public Iterator<T> iterator() {
		return this.data.iterator();
	}

	public boolean remove(Object o) {
		return this.data.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return this.data.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return this.data.retainAll(c);
	}

	public int size() {
		return this.data.size();
	}

	public Object[] toArray() {
		return this.data.toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return this.data.toArray(a);
	}

}
