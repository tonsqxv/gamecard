package com.macower.businessdata.biz;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macower.basedata.util.DoubleUtil;
import com.macower.businessdata.dao.DiscodeDaoImpl;
import com.macower.businessdata.dao.OrderDaoImpl;
import com.macower.businessdata.dao.OrderDetailDaoImpl;
import com.macower.businessdata.dao.ShopItemDaoImpl;
import com.macower.businessdata.dto.OrderDto;
import com.macower.businessdata.entity.Discode;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.Order;
import com.macower.businessdata.entity.OrderDetail;
import com.macower.businessdata.entity.ShopItem;
import com.macower.businessdata.util.MailTpl;
import com.macower.core.biz.BaseBiz;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.EmailUtil;
import com.macower.core.util.JsonUtils;
import com.macower.core.util.StringUtils;
import com.macower.sys.dao.ConfigDaoImpl;
import com.macower.sys.entity.Config;

@Service
public class OrderBizImpl extends BaseBiz implements OrderBiz {

	@Autowired
	private OrderDaoImpl orderDao;

	@Autowired
	private OrderDetailDaoImpl orderDetailDao;

	@Autowired
	private ConfigDaoImpl configDao;

	@Autowired
	private ShopItemDaoImpl shopItemDao;
	
	@Autowired
	private DiscodeDaoImpl discodeDao;

	@Override
	public Page<Order> findPageBy(OrderDto obj, Integer pageNo, Integer pageSize) {
		return orderDao.findPageBy(obj, pageNo, pageSize);
	}

	@Override
	public List<Order> findMyOrder(Order obj) {
		DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
		if (obj.getMemberId() != null) {
			dc.add(Restrictions.eq("memberId", obj.getMemberId()));
		} else if (obj.getSessionId() != null) {
			dc.add(Restrictions.eq("sessionId", obj.getSessionId()));
		}
		dc.addOrder(org.hibernate.criterion.Order.desc("createTm"));
		return orderDao.findBy(dc);
	}

	@Override
	public void save(Order obj) {
		orderDao.save(obj);

	}

	@Override
	public void deletes(String id) {
		Order old = this.orderDao.get(Long.parseLong(id));
		if (old != null) {
			if (old.getOrderStatus() != 1) { // 只有未付款状态的可以删除
				throw new BizException("只有未付款状态的可以删除");
			}
			/*
			 * if(old.getMemberId() !=null ){ throw new
			 * BizException("未付款关联会员的订单不能删除") ; }
			 */
		} else {
			throw new BizException("该订单已被删除");
		}

		this.orderDao.deleteByIds(id);
		OrderDetail param = new OrderDetail();
		param.setOrderId(old.getId());
		List<OrderDetail> details = this.orderDetailDao.findBy(param);
		if (details != null && details.size() > 0) {
			this.orderDetailDao.deleteBatch(details);
		}

	}

	@Override
	public Order get(Long id) {
		return this.orderDao.get(id);
	}
	public static void main(String[] args) {
		double d = 0 ;
		System.out.println(d/1);
	}
	
	/**
	 * 生成订单
	 */
	@Override
	public void createOrder(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String[] shopItemIds = request.getParameterValues("shopItemId");// 购物车id
		String[] item_productIds = request.getParameterValues("item_productId");
		String[] item_amounts = request.getParameterValues("item_amount");
		String discountCode = request.getParameter("discountCode");
		// 生成订单详情数据
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		double totalPrice = 0.0d;
		if (item_productIds == null) {
			return;
		}
		double discount = 0 ;
		if(StringUtils.isNotEmpty(discountCode)){
			discountCode = discountCode.trim() ;
			//查询数据库
			Discode param = new Discode() ;
			param.setDiscode(discountCode) ;
			param.setStatus(1) ;//未使用
			int sum = discodeDao.countBy(param) ;
			if(sum > 0){ //折扣码有效
				//计算总金额(不精确)
				double tmp_totalPrice = 0.0d ;
				for (int i = 0; i < item_productIds.length; i++) {
					ShopItem shopItem = shopItemDao.get(Long.parseLong(shopItemIds[i]));
					tmp_totalPrice = tmp_totalPrice + (shopItem.getUnitPrice()*Double.parseDouble(item_amounts[i])) ;
				}
				Config discodeConfig = this.configDao.findFromCacheByConfigCode("discode") ;
				double dc = (discodeConfig==null)?0.0:Double.parseDouble(discodeConfig.getConfigValue());
				if(dc >= 1){
					log.error("error:config(discode) value is error,please check config value.") ;
					dc = 0 ;
				}else{
					discount = tmp_totalPrice*(1-dc) ;
				}
				
			}
		}
		
		//计算购买件数
		int amount = 0 ;
		for (int i = 0; i < item_amounts.length; i++) {
			amount = amount +Integer.parseInt(item_amounts[i]) ;
		}
		//计算每件产品平均优惠金额
		double avgDiscountPrince = discount/amount ;
		//格式化保留两位小数
		avgDiscountPrince = new DoubleUtil().format2Point(avgDiscountPrince) ;
			
		for (int i = 0; i < item_productIds.length; i++) {
			ShopItem shopItem = shopItemDao.get(Long.parseLong(shopItemIds[i]));
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProductId(Long.parseLong(item_productIds[i]));
			orderDetail.setAmount(Integer.parseInt(item_amounts[i]));
			double unitPrice = shopItem.getUnitPrice() - avgDiscountPrince ;
			//格式化
			unitPrice = new DoubleUtil().format2Point(unitPrice) ;
			orderDetail.setUnitPrice(unitPrice);//折扣价
			orderDetail.setBasePrice(shopItem.getUnitPrice());//原价
			orderDetail.setColor(shopItem.getColor()) ;
			orderDetail.setSize(shopItem.getSize()) ;

			BigDecimal b1 = new BigDecimal(Double.parseDouble(item_amounts[i])
					* shopItem.getUnitPrice());
			BigDecimal b2 = new BigDecimal(totalPrice);
			totalPrice = b1.add(b2).doubleValue();
			list.add(orderDetail);
		}
		/*****************************************/
		//生成订单数据
		Order order = new Order();
		String orderNo = "NO"
				+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		order.setOrderNo(orderNo);
		order.setCreateTm(new Date());
		order.setOrderStatus(1); // 未付款
		
		totalPrice = new DoubleUtil().format2Point(totalPrice) ;
		
		order.setOrderTotalPrice(new Double(totalPrice));
		//根据折扣码计算折扣总金额
		double discountTotalPrice = totalPrice -discount ; 
		//格式化
		discountTotalPrice = new DoubleUtil().format2Point(discountTotalPrice) ;
		order.setDiscountTotalPrice(new Double(discountTotalPrice));// 生成订单时  折扣后的订单金额初始化为原始订单金额
		order.setDiscountPrice(discount) ;//设置优惠金额
		if(StringUtils.isNotEmpty(discountCode)){//设置订单折扣码
			order.setDiscode(discountCode) ;
		}
		Member member = (Member) session.getAttribute("member");
		if (member != null) {
			order.setMemberId(member.getId());
		} else {
			order.setSessionId(session.getId());
		}

		this.orderDao.save(order);
		for (OrderDetail detail : list) {
			detail.setOrderId(order.getId());
			this.orderDetailDao.save(detail);
		}
		//更新折扣码的状态为已使用2
		if(StringUtils.isNotEmpty(discountCode)){
			Discode param = new Discode() ;
			param.setDiscode(discountCode) ;
			param.setStatus(1) ; //未使用
			List<Discode> discodeList = this.discodeDao.findBy(param) ;
			if(discodeList != null && discodeList.size() > 0){
				for(Discode d : discodeList){
					d.setStatus(2) ;
					d.setUseTm(new Date()) ;
					this.discodeDao.update(d) ;
				}
			}
		}
		/****************************************/
		// 清空购物车
		List<ShopItem> shopItemList = null;
		ShopItem param = new ShopItem();
		if (member != null) {
			param.setMemberId(member.getId());
		} else {
			param.setSessionId(session.getId());
		}

		shopItemList = this.shopItemDao.findBy(param);
		if (shopItemList != null && shopItemList.size() > 0) {
			String ids = "";
			for (int i = 0; i < shopItemList.size(); i++) {
				ShopItem item = shopItemList.get(i);
				if (i == 0) {
					ids = ids + item.getId();
				} else {
					ids = ids + "," + item.getId();
				}
			}
			if (!"".equals(ids)) {
				this.shopItemDao.deleteByIds(ids);
			}
		}
	}

	@Override
	public void update(Order obj) {
		this.orderDao.update(obj);

	}

	@Override
	public void discount(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		old.setDiscountPrice(obj.getDiscountPrice());
		this.orderDao.update(old);

	}

	@Override
	public void dispatch(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		if (old.getOrderStatus() == 2) { // 已付款的订单才可以发货
			old.setShippingNo(obj.getShippingNo());
			old.setDispatchBee(obj.getDispatchBee());
			old.setDispatchTm(obj.getDispatchTm());
			old.setOrderStatus(3); // 已发货
			old.setDispatchKind(obj.getDispatchKind());
			old.setDispatchMailFlg(1); // 未邮件通知
			this.orderDao.update(old);
		} else {
			throw new BizException("数据已变更，请刷新数据再进行操作");
		}

	}

	@Override
	public void refunded(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		if (old.getOrderStatus() == 9) { // 审核通过状态才可以执行退款操作
			old.setRefundedTm(new Date());
			old.setOrderStatus(7); // 已退款
			this.orderDao.update(old);
		} else {
			throw new BizException("数据已变更，请刷新数据再进行操作");
		}

	}

	@Override
	public void declined(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		if (old.getOrderStatus() == 6) { // 只有申请退款状态才能进行拒绝退款操作
			old.setDeclinedTm(new Date());
			old.setDeclinedDesc(obj.getDeclinedDesc());
			old.setOrderStatus(8); // 拒绝退款
			this.orderDao.update(old);
		} else {
			throw new BizException("数据已变更，请刷新数据再进行操作");
		}

	}

	@Override
	public void completed(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		if (old.getOrderStatus() == 3) { // 只有已发货状态才能进行交易关闭
			old.setCompletedTm(new Date());
			old.setOrderStatus(4); // 已完成
			this.orderDao.update(old);
		} else {
			throw new BizException("数据已变更，请刷新数据再进行操作");
		}

	}

	@Override
	public void audit(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		if (old.getOrderStatus() == 6) { // 申请退款状态才可以审核通过
			old.setOrderStatus(9); // 审核通过
			old.setAuditTm(new Date());
			this.orderDao.update(old);
		} else {
			throw new BizException("数据已变更，请刷新数据再进行操作");
		}

	}

	@Override
	public void discount(String orderId, String discountOrderTotalPrice,
			String discountPrice, String orderDetailList) {
		if (orderId == null || discountOrderTotalPrice == null
				|| discountPrice == null || orderDetailList == null) {
			throw new BizException("参数错误，参数为空");
		}
		Order old = this.orderDao.get(Long.parseLong(orderId));
		if (old.getOrderStatus() != 1) { // 只有未付款状态的订单可以进行打折操作
			throw new BizException("订单状态已修改，请刷新数据再操作");
		}
		List<OrderDetail> detailList = null;
		List<OrderDetail> updateDetailList = new ArrayList<OrderDetail>();
		try {
			detailList = JsonUtils.jsonToList(orderDetailList,
					OrderDetail.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (detailList == null || detailList.size() == 0) {
			throw new BizException("打折错误，订单性情json convert to list error。");
		}
		// 更新详细item
		for (OrderDetail detail : detailList) {
			OrderDetail oldDetail = this.orderDetailDao.get(detail.getId());
			if (oldDetail == null) {
				throw new BizException("not find orderDetail by id="
						+ detail.getId());
			}
			oldDetail.setUnitPrice(detail.getUnitPrice());
			updateDetailList.add(oldDetail);
		}
		this.orderDetailDao.updateBatch(updateDetailList);
		// 更新订单折扣总金额、折扣金额

		old.setDiscountPrice(Double.parseDouble(discountPrice));
		old.setDiscountTotalPrice(Double.parseDouble(discountOrderTotalPrice));
		this.orderDao.update(old);
	}

	/**
	 * 发货邮件通知
	 */
	@Override
	public void dispatchSendMail(String ids) {
		if (!StringUtils.isNotEmpty(ids)) {
			throw new BizException("ids is null");
		}

		String[] arr = ids.split(",");
		for (String id : arr) {
			if (!StringUtils.isNotEmpty(id)) {
				continue;
			}
			Long orderId = Long.parseLong(id);
			Order old = this.orderDao.get(orderId);
			if (old == null || StringUtils.isEmpty(old.getShippingNo())) {
				continue;
			}
			// 发送邮件
			String subject = "Your Order Has Been Updated";
			String emailto = old.getEmail();

			EmailUtil email = new EmailUtil(null, null, null, null);
			boolean sendResult = email.sendGmailEmail(subject,
					new MailTpl().getDispatchMailTpl(old), emailto);
			// 更新邮件状态
			if (sendResult) {
				old.setDispatchMailFlg(2);// 已邮件通知
				old.setDispatchMailTm(new Date());
				this.orderDao.update(old);
			}
		}

	}

	@Override
	public void updateShppingInfo(Order obj) {
		Order old = this.orderDao.get(obj.getId());
		if (old == null) {
			throw new BizException("订单已被删除");
		}
		if (old.getOrderStatus() == 3) { // 已发货的订单才可以修改发货物流信息
			old.setShippingNo(obj.getShippingNo());
			old.setDispatchBee(obj.getDispatchBee());
			old.setDispatchKind(obj.getDispatchKind());
			old.setDispatchMailFlg(1); // 未邮件通知
			this.orderDao.update(old);
		} else {
			throw new BizException("数据已变更，请刷新数据再进行操作");
		}

	}

	@Override
	public Order findShippingNoByOrderNo(String orderNo) {
		Order param = new Order();
		param.setOrderNo(orderNo);
		List<Order> list = this.orderDao.findBy(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void cancelOrder(Long id) {
		// 更改订单状态
		Order old = this.orderDao.get(id) ;
		if(old.getOrderStatus() == 1){ //未付款才可以取消
			old.setOrderStatus(5) ; //取消订单
			old.setCancelledTm(new Date()) ;
			this.orderDao.update(old) ;
		}
		//更改折扣码状态
		String discode = old.getDiscode() ;
		if(StringUtils.isNotEmpty(discode)){
			Discode param = new Discode() ;
			param.setDiscode(discode) ;
			List<Discode> list = this.discodeDao.findBy(param) ;
			if(list != null && list.size() > 0){
				for(Discode code : list){
					code.setStatus(1) ;//未使用
					this.discodeDao.update(code) ;
				}
			}
		}
	}

}
