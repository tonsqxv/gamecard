package com.macower.businessdata.dto;

import java.util.Date;

import com.macower.businessdata.entity.Order;

public class OrderDto extends Order{

	private static final long serialVersionUID = -6152679277088927608L;
	
	/*private String orderNo ;
	
	private Integer orderStatus ;
	
	private String memberName ;
	
	private String email ;*/

	private Date createTmBegin;

	private Date createTmEnd;
	
	private Date payTmBegin ;
	
	private Date payTmEnd ;
	
	private Date dispatchTmBegin ;
	
	private Date dispatchTmEnd ;
	
	private Date completedTmBegin ;
	
	private Date completedTmEnd ;
	
	private Date cancelledTmBegin ;
	
	private Date cancelledTmEnd ;
	
	private Date refundApplyTmBegin ;
	
	private Date refundApplyTmEnd ;
	
	private Date declinedTmBegin ;
	
	private Date declinedTmEnd ;
	
	private Date refundedTmBegin ;
	
	private Date refundedTmEnd ;
	
	private Date auditTmBegin ;
	
	private Date auditTmEnd ;

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

	public Date getPayTmBegin() {
		return payTmBegin;
	}

	public void setPayTmBegin(Date payTmBegin) {
		this.payTmBegin = payTmBegin;
	}

	public Date getPayTmEnd() {
		return payTmEnd;
	}

	public void setPayTmEnd(Date payTmEnd) {
		this.payTmEnd = payTmEnd;
	}

	public Date getDispatchTmBegin() {
		return dispatchTmBegin;
	}

	public void setDispatchTmBegin(Date dispatchTmBegin) {
		this.dispatchTmBegin = dispatchTmBegin;
	}

	public Date getDispatchTmEnd() {
		return dispatchTmEnd;
	}

	public void setDispatchTmEnd(Date dispatchTmEnd) {
		this.dispatchTmEnd = dispatchTmEnd;
	}

	public Date getCompletedTmBegin() {
		return completedTmBegin;
	}

	public void setCompletedTmBegin(Date completedTmBegin) {
		this.completedTmBegin = completedTmBegin;
	}

	public Date getCompletedTmEnd() {
		return completedTmEnd;
	}

	public void setCompletedTmEnd(Date completedTmEnd) {
		this.completedTmEnd = completedTmEnd;
	}

	public Date getCancelledTmBegin() {
		return cancelledTmBegin;
	}

	public void setCancelledTmBegin(Date cancelledTmBegin) {
		this.cancelledTmBegin = cancelledTmBegin;
	}

	public Date getCancelledTmEnd() {
		return cancelledTmEnd;
	}

	public void setCancelledTmEnd(Date cancelledTmEnd) {
		this.cancelledTmEnd = cancelledTmEnd;
	}

	public Date getRefundApplyTmBegin() {
		return refundApplyTmBegin;
	}

	public void setRefundApplyTmBegin(Date refundApplyTmBegin) {
		this.refundApplyTmBegin = refundApplyTmBegin;
	}

	public Date getRefundApplyTmEnd() {
		return refundApplyTmEnd;
	}

	public void setRefundApplyTmEnd(Date refundApplyTmEnd) {
		this.refundApplyTmEnd = refundApplyTmEnd;
	}

	public Date getDeclinedTmBegin() {
		return declinedTmBegin;
	}

	public void setDeclinedTmBegin(Date declinedTmBegin) {
		this.declinedTmBegin = declinedTmBegin;
	}

	public Date getDeclinedTmEnd() {
		return declinedTmEnd;
	}

	public void setDeclinedTmEnd(Date declinedTmEnd) {
		this.declinedTmEnd = declinedTmEnd;
	}

	public Date getRefundedTmBegin() {
		return refundedTmBegin;
	}

	public void setRefundedTmBegin(Date refundedTmBegin) {
		this.refundedTmBegin = refundedTmBegin;
	}

	public Date getRefundedTmEnd() {
		return refundedTmEnd;
	}

	public void setRefundedTmEnd(Date refundedTmEnd) {
		this.refundedTmEnd = refundedTmEnd;
	}

	public Date getAuditTmBegin() {
		return auditTmBegin;
	}

	public void setAuditTmBegin(Date auditTmBegin) {
		this.auditTmBegin = auditTmBegin;
	}

	public Date getAuditTmEnd() {
		return auditTmEnd;
	}

	public void setAuditTmEnd(Date auditTmEnd) {
		this.auditTmEnd = auditTmEnd;
	}

}
