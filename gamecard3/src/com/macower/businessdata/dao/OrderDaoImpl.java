package com.macower.businessdata.dao;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.Order;
import com.macower.core.dao.BaseEntityDao;
import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@Repository
public class OrderDaoImpl extends BaseEntityDao<Order>{

	public Page<Order> findPageBy(OrderDto obj, Integer pageNo, Integer pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
		//订单编号
		if(StringUtils.isNotEmpty(obj.getOrderNo())){
			dc.add(Restrictions.like("orderNo", obj.getOrderNo(),MatchMode.ANYWHERE)) ;
		}
		//会员
		if(StringUtils.isNotEmpty(obj.getMemberName())){
			dc.add(Restrictions.like("memberName", obj.getMemberName(),MatchMode.ANYWHERE)) ;
		}
		//邮箱
		if(StringUtils.isNotEmpty(obj.getEmail())){
			dc.add(Restrictions.like("email", obj.getMemberName(),MatchMode.ANYWHERE)) ;
		}
		
		//订单状态
		if(obj.getOrderStatus() != null){
			dc.add(Restrictions.eq("orderStatus", obj.getOrderStatus())) ;
			if(obj.getOrderStatus() == 1){ //待付款按创建时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("createTm")) ;
			}else if(obj.getOrderStatus() == 2){ //已付款按已付款时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("payTm")) ;
			}else if(obj.getOrderStatus() == 3){ //已发货按发货时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("dispatchTm")) ;
			}else if(obj.getOrderStatus() == 4){ //已完成按交易关闭时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("completedTm")) ;
			}else if(obj.getOrderStatus() == 5){ //取消按交易取消时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("cancelledTm")) ;
			}else if(obj.getOrderStatus() == 6){ //申请退款按申请退款时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("refundApplyTm")) ;
			}else if(obj.getOrderStatus() == 7){ //已退款按退款时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("refundedTm")) ;
			}else if(obj.getOrderStatus() == 8){ //拒绝退款按拒绝退款时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("declinedTm")) ;
			}else if(obj.getOrderStatus() == 9){ //审核通过按审核通过时间降序排列
				dc.addOrder(org.hibernate.criterion.Order.desc("auditTm")) ;
			}
		}
		
		//订单创建时间
		if(obj.getCreateTmBegin() != null){
			dc.add(Restrictions.ge("createTm", obj.getCreateTmBegin())) ;
		}
		if(obj.getCreateTmEnd() != null){
			dc.add(Restrictions.le("createTm", obj.getCreateTmEnd())) ;
		}
		//付款时间
		if(obj.getPayTmBegin() != null){
			dc.add(Restrictions.ge("payTm", obj.getPayTmBegin())) ;
		}
		if(obj.getPayTmEnd() != null){
			dc.add(Restrictions.le("payTm", obj.getPayTmEnd())) ;
		}
		//发货时间
		if(obj.getDispatchTmBegin() != null){
			dc.add(Restrictions.ge("dispatchTm", obj.getDispatchTmBegin())) ;
		}
		if(obj.getDispatchTmEnd() != null){
			dc.add(Restrictions.le("dispatchTm", obj.getDispatchTmEnd())) ;
		}
		//完成时间
		if(obj.getCompletedTmBegin() != null){
			dc.add(Restrictions.ge("completedTm", obj.getCompletedTmBegin())) ;
		}
		if(obj.getCompletedTmEnd() != null){
			dc.add(Restrictions.le("completedTm", obj.getCompletedTmEnd())) ;
		}
		//取消时间
		if(obj.getCancelledTmBegin() != null){
			dc.add(Restrictions.ge("cancelledTm", obj.getCancelledTmBegin())) ;
		}
		if(obj.getCancelledTmEnd() != null){
			dc.add(Restrictions.le("cancelledTm", obj.getCancelledTmEnd())) ;
		}
		//申请退款时间
		if(obj.getRefundApplyTmBegin() != null){
			dc.add(Restrictions.ge("refundApplyTm", obj.getRefundApplyTmBegin())) ;
		}
		if(obj.getRefundApplyTmEnd() != null){
			dc.add(Restrictions.le("refundApplyTm", obj.getRefundApplyTmEnd())) ;
		}
		//拒绝退款时间
		if(obj.getDeclinedTmBegin() != null){
			dc.add(Restrictions.ge("declinedTm", obj.getDeclinedTmBegin())) ;
		}
		if(obj.getDeclinedTmEnd() != null){
			dc.add(Restrictions.le("declinedTm", obj.getDeclinedTmEnd())) ;
		}
		//退款时间
		if(obj.getRefundedTmBegin() != null){
			dc.add(Restrictions.ge("refundedTm", obj.getRefundedTmBegin())) ;
		}
		if(obj.getRefundedTmEnd() != null){
			dc.add(Restrictions.le("refundedTm", obj.getRefundedTmEnd())) ;
		}
		//退款审核时间
		if(obj.getAuditTmBegin() != null){
			dc.add(Restrictions.ge("auditTm", obj.getAuditTmBegin())) ;
		}
		if(obj.getAuditTmEnd() != null){
			dc.add(Restrictions.le("auditTm", obj.getAuditTmEnd())) ;
		}
		//是否发货邮件通知
		if(obj.getDispatchMailFlg() != null){
				dc.add(Restrictions.eq("dispatchMailFlg", obj.getDispatchMailFlg())) ;
		}
		
		return this.findPageBy(dc,pageNo,pageSize) ;
	}

	/**
	 * 查找游客需要同步的数据  
	 * @return
	 */
	public List<Order> findGuestSycnData() {
		DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.sqlRestriction("{alias}.member_id is null and ({alias}.sycn_flag is null or {alias}.sycn_flag =0) and {alias}.email is not null  and {alias}.order_status != 1")) ;
		
		return this.findBy(dc); 
	}
	


}
