package com.macower.businessdata.dto;

import java.util.Date;

import com.macower.businessdata.entity.ShopItem;

public class ShopItemDto extends ShopItem{

	private static final long serialVersionUID = 1857641688234400633L;
	
	private Date createTmBegin ;
	
	private Date createTmEnd ;
	//是否是会员  1：是  2：否
	private Integer isMember ;

	public Date getCreateTmBegin() {
		return createTmBegin;
	}

	public void setCreateTmBegin(Date createTmBegin) {
		this.createTmBegin = createTmBegin;
	}

	public Date getCreateTmEnd() {
		return createTmEnd;
	}

	public void setCreateTmEnd(Date createTmEnd) {
		this.createTmEnd = createTmEnd;
	}

	public Integer getIsMember() {
		return isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

}
