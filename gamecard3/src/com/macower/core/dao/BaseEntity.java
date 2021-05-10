package com.macower.core.dao;

public abstract class BaseEntity implements IEntity {
	
	private static final long serialVersionUID = 1815646184138810394L;
	private Long id;
	private String code;

	private int start;

	private int limit;

	public int compareTo(Object o) {
		if (o == null)
			return 1;
		if (!IEntity.class.isAssignableFrom(o.getClass())) {
			return 1;
		}
		IEntity target = (IEntity) o;
		if (getId() == null)
			return 1;
		if (target.getId() == null)
			return -1;
		return getId().compareTo(target.getId());
	}

	public BaseEntity() {
	}

	public BaseEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int hashCode() {
		int result = 1;
		result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if ((this.id != null) && (other.id != null))
			return this.id.equals(other.id);
		return false;
	}

	public String toString() {
		return getClass().getName() + ":" + this.id;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
