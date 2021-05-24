package com.macower.businessdata.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import com.macower.core.dao.BaseEntity;

@Entity
@Table(name = "tb_order")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends BaseEntity{
	
	private static final long serialVersionUID = -438612270410689344L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id ;
	
	@Column(name="order_no")
	private String orderNo ;
	
	@Column(name="order_total_price")
	private Double orderTotalPrice ;
	
	@Column(name="discount_total_price")
	private Double discountTotalPrice ;
	
	@Column(name="discount_price")
	private Double discountPrice ;
	
	@Column(name="zip_code")
	private String zipCode ;
	
	@Column(name="country")
	private String country ;
	
	@Column(name="country_name")
	private String countryName ;
	
	@Column(name="state")
	private String state ;
	
	@Column(name="city")
	private String city ;
	
	@Column(name="street1")
	private String street1 ;
	
	@Column(name="street2")
	private String street2 ;
	
	@Column(name="phone_num")
	private String phoneNum ;
	
	@Column(name="first_name")
	private String firstName ;
	
	@Column(name="last_name")
	private String lastName ;
	
	@Column(name="email")
	private String email ;
	
	@Column(name="shipping_calculation_mode")
	private String shippingCalculationMode ;
	
	@Column(name="shipping_option_Id")
	private Long shippingOptionId ;
	
	@Column(name="shipping_option_amount")
	private Double shippingOptionAmount ;
	
	@Column(name="shipping_option_name")
	private String shippingOptionName ;
	
	@Column(name="order_status")
	private Integer orderStatus ;
	
	@Column(name="session_id")
	private String sessionId ;
	
	@Column(name="member_id")
	private Long memberId ;
	
	@Formula("(select t.email from tb_member t where t.id = member_id)")
	private String memberName ;
	
	@Column(name="transaction_id")
	private String transactionId ;
	
	@Column(name="gross_amount")
	private Double grossAmount ;
	
	@Column(name="currency_id")
	private String currencyId ;
	
	@Column(name="create_tm")
	private Date createTm ;
	
	@Column(name="pay_tm")
	private Date payTm ;
	
	@Column(name="member_message")
	private String memberMessage ;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderId")
	private List<OrderDetail> orderDetails ;
	
	@Column(name="shipping_no")
	private String shippingNo ;
	
	@Column(name="dispatch_tm")
	private Date dispatchTm ;
	
	@Column(name="dispatch_bee")
	private Double dispatchBee ;
	
	@Column(name="dispatch_kind")
	private String dispatchKind ;
	
	@Column(name="dispatch_mail_flg")
	private Integer dispatchMailFlg ;
	
	@Column(name="dispatch_mail_tm")
	private Date dispatchMailTm ;
	
	@Column(name="completed_tm")
	private Date completedTm ;
	
	@Column(name="cancelled_tm")
	private Date cancelledTm ;
	
	@Column(name="refund_apply_tm")
	private Date refundApplyTm ;
	
	@Column(name="refund_apply_reason")
	private Integer refundApplyReason ;
	
	@Column(name="refund_apply_desc")
	private String refundApplyDesc ;
	
	@Column(name="declined_tm")
	private Date declinedTm ;
	
	@Column(name="declined_desc")
	private String declinedDesc ;
	
	@Column(name="refunded_tm")
	private Date refundedTm ;
	
	@Column(name="audit_tm")
	private Date auditTm ;
	
	@Formula("(select concat(t.street1,',',t.street2,' ',t.city_name,',',t.state,' ',t.zipcode,' ',t.country_name) from tb_address_log t where t.order_id = id)")
	//@Formula("(select t.country_name from tb_address_log t where t.order_id = id)")
	private String addressLog ;
	
	@Column(name="sycn_flag")
	private Integer sycnFlag ;
	
	@Column(name="discode")
	private String discode ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getShippingCalculationMode() {
		return shippingCalculationMode;
	}

	public void setShippingCalculationMode(String shippingCalculationMode) {
		this.shippingCalculationMode = shippingCalculationMode;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getPayTm() {
		return payTm;
	}

	public void setPayTm(Date payTm) {
		this.payTm = payTm;
	}

	public Double getShippingOptionAmount() {
		return shippingOptionAmount;
	}

	public void setShippingOptionAmount(Double shippingOptionAmount) {
		this.shippingOptionAmount = shippingOptionAmount;
	}


	public String getMemberMessage() {
		return memberMessage;
	}

	public void setMemberMessage(String memberMessage) {
		this.memberMessage = memberMessage;
	}

	public String getShippingOptionName() {
		return shippingOptionName;
	}

	public void setShippingOptionName(String shippingOptionName) {
		this.shippingOptionName = shippingOptionName;
	}

	public Long getShippingOptionId() {
		return shippingOptionId;
	}

	public void setShippingOptionId(Long shippingOptionId) {
		this.shippingOptionId = shippingOptionId;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public Date getDispatchTm() {
		return dispatchTm;
	}

	public void setDispatchTm(Date dispatchTm) {
		this.dispatchTm = dispatchTm;
	}

	public Double getDispatchBee() {
		return dispatchBee;
	}

	public void setDispatchBee(Double dispatchBee) {
		this.dispatchBee = dispatchBee;
	}

	public String getDispatchKind() {
		return dispatchKind;
	}

	public void setDispatchKind(String dispatchKind) {
		this.dispatchKind = dispatchKind;
	}

	public Date getCompletedTm() {
		return completedTm;
	}

	public void setCompletedTm(Date completedTm) {
		this.completedTm = completedTm;
	}

	public Date getCancelledTm() {
		return cancelledTm;
	}

	public void setCancelledTm(Date cancelledTm) {
		this.cancelledTm = cancelledTm;
	}

	public Date getRefundApplyTm() {
		return refundApplyTm;
	}

	public void setRefundApplyTm(Date refundApplyTm) {
		this.refundApplyTm = refundApplyTm;
	}

	public String getRefundApplyDesc() {
		return refundApplyDesc;
	}

	public void setRefundApplyDesc(String refundApplyDesc) {
		this.refundApplyDesc = refundApplyDesc;
	}

	public Date getDeclinedTm() {
		return declinedTm;
	}

	public void setDeclinedTm(Date declinedTm) {
		this.declinedTm = declinedTm;
	}

	public String getDeclinedDesc() {
		return declinedDesc;
	}

	public void setDeclinedDesc(String declinedDesc) {
		this.declinedDesc = declinedDesc;
	}

	public Date getRefundedTm() {
		return refundedTm;
	}

	public void setRefundedTm(Date refundedTm) {
		this.refundedTm = refundedTm;
	}

	public Date getAuditTm() {
		return auditTm;
	}

	public void setAuditTm(Date auditTm) {
		this.auditTm = auditTm;
	}

	public Integer getRefundApplyReason() {
		return refundApplyReason;
	}

	public void setRefundApplyReason(Integer refundApplyReason) {
		this.refundApplyReason = refundApplyReason;
	}

	public Double getDiscountTotalPrice() {
		return discountTotalPrice;
	}

	public void setDiscountTotalPrice(Double discountTotalPrice) {
		this.discountTotalPrice = discountTotalPrice;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getDispatchMailFlg() {
		return dispatchMailFlg;
	}

	public void setDispatchMailFlg(Integer dispatchMailFlg) {
		this.dispatchMailFlg = dispatchMailFlg;
	}

	public Date getDispatchMailTm() {
		return dispatchMailTm;
	}

	public void setDispatchMailTm(Date dispatchMailTm) {
		this.dispatchMailTm = dispatchMailTm;
	}

	public String getAddressLog() {
		return addressLog;
	}

	public void setAddressLog(String addressLog) {
		this.addressLog = addressLog;
	}

	public Integer getSycnFlag() {
		return sycnFlag;
	}

	public void setSycnFlag(Integer sycnFlag) {
		this.sycnFlag = sycnFlag;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}


}
