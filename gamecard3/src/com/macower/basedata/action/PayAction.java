package com.macower.basedata.action;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.macower.basedata.biz.AddressLogBiz;
import com.macower.basedata.entity.AddressLog;
import com.macower.basedata.util.DoubleUtil;
import com.macower.businessdata.biz.CountryBiz;
import com.macower.businessdata.biz.OrderBiz;
import com.macower.businessdata.biz.OrderDetailBiz;
import com.macower.businessdata.biz.ShippingOptionBiz;
import com.macower.businessdata.entity.Country;
import com.macower.businessdata.entity.Order;
import com.macower.businessdata.entity.OrderDetail;
import com.macower.businessdata.entity.ShippingOption;
import com.macower.businessdata.util.MailTpl;
import com.macower.core.exception.BizException;
import com.macower.core.util.EmailUtil;
import com.macower.sys.biz.ConfigBiz;
import com.macower.sys.entity.Config;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;
import com.paypal.sdk.util.Util;
import com.paypal.soap.api.AckCodeType;
import com.paypal.soap.api.AddressType;
import com.paypal.soap.api.BasicAmountType;
import com.paypal.soap.api.CountryCodeType;
import com.paypal.soap.api.CurrencyCodeType;
import com.paypal.soap.api.DoExpressCheckoutPaymentRequestDetailsType;
import com.paypal.soap.api.DoExpressCheckoutPaymentRequestType;
import com.paypal.soap.api.DoExpressCheckoutPaymentResponseDetailsType;
import com.paypal.soap.api.DoExpressCheckoutPaymentResponseType;
import com.paypal.soap.api.GetExpressCheckoutDetailsRequestType;
import com.paypal.soap.api.GetExpressCheckoutDetailsResponseDetailsType;
import com.paypal.soap.api.GetExpressCheckoutDetailsResponseType;
import com.paypal.soap.api.PaymentActionCodeType;
import com.paypal.soap.api.PaymentDetailsItemType;
import com.paypal.soap.api.PaymentDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestType;
import com.paypal.soap.api.SetExpressCheckoutResponseType;
import com.paypal.soap.api.ShippingOptionType;

@Controller
@RequestMapping(value = "/pay")
public class PayAction {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderBiz orderBiz;
	
	@Autowired
	private OrderDetailBiz orderDetailBiz;
	
	@Autowired
	private ConfigBiz configBiz;
	
	@Autowired
	private CountryBiz countryBiz;
	
	@Autowired
	private ShippingOptionBiz shippingOptionBiz;
	
	@Autowired
	private AddressLogBiz addressLogBiz;
	
	

	/**
	 * 进入支付页面 我的订单里-->执行checkout操作
	 * 初始化支付环境
	 * @param obj
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/prePayForm" })
	public String initPayEnviroment(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("prepare to check out order ,begin...") ;
		HttpSession session = request.getSession();
		//根据prePayOrderId获取订单信息
		Long prePayOrderId = (Long)session.getAttribute("prePayOrderId") ;
		if(prePayOrderId == null){
			log.warn("session expired,prePayOrderId is null") ;
			//跳转到主页面
			return "redirect:/index";
		}
		Order order = this.orderBiz.get(prePayOrderId) ;
		model.addAttribute("order", order) ;
		OrderDetail param = new OrderDetail() ;
		param.setOrderId(order.getId()) ;
		List<OrderDetail> orderDetailList =  this.orderDetailBiz.findBy(param) ;
		model.addAttribute("orderDetailList", orderDetailList) ;
		//物流信息设置
		if(session.getAttribute("shippingOptionList") == null){
			List<ShippingOption> shippingOptionList = this.shippingOptionBiz.findBy(new ShippingOption()) ;
			session.setAttribute("shippingOptionList", shippingOptionList) ;
		}
		//地址国家信息
		if(session.getAttribute("countryList") == null){
			List<Country> countryList = this.countryBiz.findBy(new Country()) ;
			session.setAttribute("countryList", countryList) ;
		}
		
		
		//paypal账号信息检测
		//API用户名 dashitou_api1.qq.com
		Config APIUsername = this.configBiz.findByConfigCode("APIUsername") ;
		if(APIUsername == null || APIUsername.getConfigValue().isEmpty()){
			throw new BizException("Paypal environment not config APIUsername,Please contact to system administrator by e-mail");
		}
		//API密码 C2CHWFA5HY26UY9D
		Config APIPassword = this.configBiz.findByConfigCode("APIPassword") ;
		if(APIPassword == null || APIPassword.getConfigValue().isEmpty()){
			throw new BizException("Paypal environment not config APIPassword,Please contact to system administrator by e-mail");
		}
		//证书密钥密码 skychen
		Config PrivateKeyPassword = this.configBiz.findByConfigCode("PrivateKeyPassword") ;
		if(PrivateKeyPassword == null || PrivateKeyPassword.getConfigValue().isEmpty()){
			throw new BizException("Paypal environment not config PrivateKeyPassword,Please contact to system administrator by e-mail");
		}
		//设置卖家邮箱  123390255@qq.com
		Config Subject = this.configBiz.findByConfigCode("Subject") ;
		if(Subject == null || Subject.getConfigValue().isEmpty()){
			throw new BizException("Paypal environment not config Subject,Please contact to system administrator by e-mail");
		}

		try {
			if(session.getAttribute("environment") == null){
				Config config = this.configBiz.findByConfigCode("paypalEnvironment") ;
				if(config != null ){
					session.setAttribute("environment",config.getConfigValue()) ;
				}else{
					throw new BizException("Paypal environment not config,Please contact to system administrator by e-mail");
				}
			}
			
			
			APIProfile profile = null;

			profile = ProfileFactory.createSSLAPIProfile();
			//API用户名 dashitou_api1.qq.com
			profile.setAPIUsername(APIUsername.getConfigValue().trim());
			//API密码 C2CHWFA5HY26UY9D
			profile.setAPIPassword(APIPassword.getConfigValue().trim());
			profile.setCertificateFile(session.getServletContext()
					.getRealPath(File.separator)
					+ File.separator + "WEB-INF/cert/paypal_cert.p12");
			//证书密钥密码 skychen
			profile.setPrivateKeyPassword(PrivateKeyPassword.getConfigValue().trim());
			//设置运行环境 sandbox代表测试环境  正式环境：live
			profile.setEnvironment((String)session.getAttribute("environment"));
			//设置卖家邮箱 123390255@qq.com
			profile.setSubject(Subject.getConfigValue().trim());
			
			CallerServices caller = new CallerServices();

			caller.setAPIProfile(profile);
			session.setAttribute("caller", caller);
			log.info("checkout end.") ;
		} catch (Exception e) {
			log.error(e.getMessage()) ;
			session.setAttribute("exception", e);
			return "pay/payError";
		}
		return "pay/prePayForm";
	}
	

	/**
	 * 支付，跳转到支付官网
	 * @param obj
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/payAccountLogin" }, method = RequestMethod.POST)
	public String payAccountLogin(Order obj,Model model , HttpServletRequest request,
			HttpServletResponse response) {
		log.info("prepare to login paypal,begin...") ;
		HttpSession session = request.getSession();
		//更新订单地址信息 设置物流名称和运费
		Long prePayOrderId = (Long)session.getAttribute("prePayOrderId") ;
		if(prePayOrderId == null){
			log.warn("session expired ,prePayOrderId is null") ;
			return "redirect:/index";
		}
		Order old = this.orderBiz.get(prePayOrderId) ;
		old.setShippingOptionId(obj.getShippingOptionId()) ;
		ShippingOption shippingOption= this.shippingOptionBiz.get(obj.getShippingOptionId()) ;
		old.setShippingOptionName(shippingOption.getShippingOptionName()) ;
		old.setShippingOptionAmount(shippingOption.getShippingOptionAmount()) ;
		
		//设置联系方式  邮箱、电话
		//更新地址信息
		old.setFirstName(obj.getFirstName()) ;
		old.setLastName(obj.getLastName()) ;
		old.setStreet1(obj.getStreet1()) ;
		old.setStreet2(obj.getStreet2()) ;
		old.setCity(obj.getCity()) ;
		old.setState(obj.getState()) ;
		old.setCountry(obj.getCountry()) ;
		old.setCountryName(this.countryBiz.findByCode(old.getCountry())) ;
		old.setZipCode(obj.getZipCode()) ;
		old.setEmail(obj.getEmail().trim()) ;
		old.setPhoneNum(obj.getPhoneNum()) ;
		old.setMemberMessage(obj.getMemberMessage()) ;
		
		this.orderBiz.update(old) ;
		
		String currencyCodeType = request.getParameter("currencyCodeType") ;
		String paymentType = request.getParameter("paymentType") ;
		
		try {
			CallerServices caller = (CallerServices) session.getAttribute("caller");
			SetExpressCheckoutRequestType pprequest = new SetExpressCheckoutRequestType();
			SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();


			// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();

			String returnURL = basePath + "/pay/confirmPayDetail";
			//取消页面跳转到我的订单页面
			String cancelURL = basePath + "/order/toMyOrder";

			details.setReturnURL(returnURL + "?currencyCodeType="
					+ currencyCodeType);
			details.setCancelURL(cancelURL);
			details.setBuyerEmail(old.getEmail());

			details.setPaymentAction(PaymentActionCodeType.fromString(paymentType));
			session.setAttribute("paymentType",	paymentType);

			details.setCallbackURL("https://www.ppcallback.com/callback.pl");
			details.setCallbackTimeout("5");

			double totalPrice = 0.0d ;
			OrderDetail param = new OrderDetail() ;
			param.setOrderId(old.getId()) ;
			List<OrderDetail> orderDetailList = this.orderDetailBiz.findBy(param) ;
			PaymentDetailsItemType[] lineItems = new PaymentDetailsItemType[orderDetailList.size()];
			for(int i=0 ; i< lineItems.length ; i++){
				OrderDetail orderDetail = orderDetailList.get(i) ;
				PaymentDetailsItemType item = new PaymentDetailsItemType();
				BasicAmountType amt = new BasicAmountType();
				amt.setCurrencyID(CurrencyCodeType.fromString(currencyCodeType));//设置货币种类
				amt.set_value(Double.toString(orderDetail.getUnitPrice()));//设置单价
				item.setQuantity(new BigInteger(Integer.toString(orderDetail.getAmount())));//设置数量
				item.setName(orderDetail.getOrderNo()); //这里物品名字传的是订单编号
				item.setNumber(Integer.toString(i));//设置编号
				item.setAmount(amt);
				lineItems[i] = item; 
				
				BigDecimal b_unitTotal = new BigDecimal(orderDetail.getAmount()*orderDetail.getUnitPrice());
				BigDecimal b_totalPrice = new BigDecimal(totalPrice);
				totalPrice = b_totalPrice.add(b_unitTotal).doubleValue();
				
			}
			//金额格式化 只保留两位小数
			totalPrice = new DoubleUtil().format2Point(totalPrice) ;
			
			//准备物流信息数据
			BasicAmountType shippingTotal = new BasicAmountType();
			//这里设置的物流费用必须是你选择的物流金额
			shippingTotal.set_value(old.getShippingOptionAmount()+"");
			
			shippingTotal.setCurrencyID(CurrencyCodeType.fromString(currencyCodeType));
			
			//准备订单总金额和货币种类数据
			BasicAmountType itemsTotal = new BasicAmountType();
			itemsTotal.set_value(Double.toString(totalPrice));//订单总金额 
			itemsTotal.setCurrencyID(CurrencyCodeType.fromString(currencyCodeType));

			
			PaymentDetailsType[] payDetails = new PaymentDetailsType[1];
			PaymentDetailsType paydtl = new PaymentDetailsType();
			//设置订单明细条目
			paydtl.setPaymentDetailsItem(lineItems);
			//设置物流信息
			paydtl.setShippingTotal(shippingTotal);
			//订单总金额和货币种类
			paydtl.setItemTotal(itemsTotal);
			payDetails[0] = paydtl;
			details.setPaymentDetails(payDetails);

			// populate shipping address details
			//1:重写地址，使用传入的地址  0：使用注册地址 传入地址无效
			details.setAddressOverride("0");
			//details.setNoShipping("1");//1：不校验地址  0：校验地址

			String[] shippingMethods = { old.getShippingOptionName() };//这里可以设置多种物流方式
			ShippingOptionType[] shippings = new ShippingOptionType[shippingMethods.length];
			for (int i = 0; i < shippings.length; i++) {
				ShippingOptionType ship = new ShippingOptionType();
				ship.setShippingOptionName(shippingMethods[i]);
				BasicAmountType amt = new BasicAmountType();
				amt.setCurrencyID(CurrencyCodeType.fromString(currencyCodeType));
				if (i == 0) {
					ship.setShippingOptionIsDefault("true");
					amt.set_value(old.getShippingOptionAmount()+"");
				} 
				ship.setShippingOptionAmount(amt);
				shippings[i] = ship;
			}
			//设置物流方式
			details.setFlatRateShippingOptions(shippings);

			//设置买家收获地址
			AddressType address = new AddressType();
			address.setName(old.getFirstName()+" "+old.getLastName());
			address.setStreet1(old.getStreet1());
			address.setStreet2(old.getStreet2()) ;
			address.setCityName(old.getCity());
			address.setStateOrProvince(old.getState());
			address.setCountryName(old.getCountryName());
			address.setCountry(CountryCodeType.fromString(old.getCountry()));
			address.setPostalCode(old.getZipCode());	
			details.setAddress(address);
			
			// populate order total
			BasicAmountType orderTotal = new BasicAmountType();
			// orderTotal.set_value(request.getParameter("paymentAmount"));
			orderTotal.setCurrencyID(CurrencyCodeType.fromString(currencyCodeType));
			
			//计算最后的总金额 = 原有总金额+运费
			totalPrice = add(totalPrice , old.getShippingOptionAmount()) ;
			float amt = Util.round(Float.parseFloat(Double.toString(totalPrice)), 2);
			
			
			orderTotal.set_value(Float.toString(amt));
			details.setOrderTotal(orderTotal);
			//这里的最大交易金额设置只是为了安全 在原有的总金额基础上设置了个范围值
			float maxamt = Util.round(amt + 35.00f, 2);
			BasicAmountType maxAmount = new BasicAmountType();
			maxAmount.set_value(Float.toString(maxamt));
			maxAmount.setCurrencyID(CurrencyCodeType.fromString(currencyCodeType));
			details.setMaxAmount(maxAmount);

			pprequest.setSetExpressCheckoutRequestDetails(details);
			String testEnv = (String) session.getAttribute("environment");

			SetExpressCheckoutResponseType ppresponse = (SetExpressCheckoutResponseType) caller
					.call("SetExpressCheckout", pprequest);
			if (!ppresponse.getAck().equals(AckCodeType.Success)
					&& !ppresponse.getAck().equals(
							AckCodeType.SuccessWithWarning)) {
				session.setAttribute("response", ppresponse);
				log.error("Ack:"+ppresponse.getAck()) ;
				log.error("CorrelationID:"+ppresponse.getCorrelationID()) ;
				log.error("Version:"+ppresponse.getVersion()) ;
				for (int i = 0; i < ppresponse.getErrors().length; i++) {
					log.error("Error Number:"+ppresponse.getErrors(i).getErrorCode()) ;
					log.error("Short Message:"+ppresponse.getErrors(i).getShortMessage()) ;
					log.error("Long Message:"+ppresponse.getErrors(i).getLongMessage()) ;
				}
				return "pay/APIError";
			} else {
				String redirectURL = null ;
				if("sandbox".equals(testEnv)){
					redirectURL = "https://www."
							+ testEnv+"."
							+ "paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
							+ ppresponse.getToken();
				}else if("live".equals(testEnv)){
					redirectURL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
							+ ppresponse.getToken();
				}
				response.sendRedirect(redirectURL);
			}
			log.info("login paypal successfull. ") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			log.error(e.getMessage()) ;
			session.setAttribute("exception", e);
			//错误
			return "pay/payError";
		}
		return "product/main";
	}
	/**
	 * 两个double数相加
	 * @param d1
	 * @param d2
	 * @return
	 */
	private Double add(Double d1,Double d2){
		BigDecimal b1 = new BigDecimal(d1);
		
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		Double v = b1.add(b2).doubleValue();
		return v ;
	}

	/**
	 * 支付官网后回调的商品确认详情页面
	 * 
	 * @param obj
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = { "/confirmPayDetail" })
	public String confirmPayDetail(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("confirmPayDetail begin...") ;
		HttpSession session = request.getSession() ;
		CallerServices caller = (CallerServices) session.getAttribute("caller");		
		  
		GetExpressCheckoutDetailsRequestType pprequest = new GetExpressCheckoutDetailsRequestType();
		pprequest.setToken(request.getParameter("token"));

		try {
			GetExpressCheckoutDetailsResponseType ppresponse = (GetExpressCheckoutDetailsResponseType)caller.call("GetExpressCheckoutDetails", pprequest);
			
			if (!ppresponse.getAck().equals(AckCodeType.Success) && 
				!ppresponse.getAck().equals(AckCodeType.SuccessWithWarning)) {
				session.setAttribute("response", ppresponse);
				log.error("Ack:"+ppresponse.getAck()) ;
				log.error("CorrelationID:"+ppresponse.getCorrelationID()) ;
				log.error("Version:"+ppresponse.getVersion()) ;
				for (int i = 0; i < ppresponse.getErrors().length; i++) {
					log.error("Error Number:"+ppresponse.getErrors(i).getErrorCode()) ;
					log.error("Short Message:"+ppresponse.getErrors(i).getShortMessage()) ;
					log.error("Long Message:"+ppresponse.getErrors(i).getLongMessage()) ;
				}
				return "pay/APIError";
			} 
			
			GetExpressCheckoutDetailsResponseDetailsType details = ppresponse.getGetExpressCheckoutDetailsResponseDetails();
			model.addAttribute("details", details) ;
			Long prePayOrderId = (Long)session.getAttribute("prePayOrderId") ;
			Order order = this.orderBiz.get(prePayOrderId) ;
			model.addAttribute("order", order) ;
			//记录从paypal传过来的地址信息
			AddressType address = details.getPayerInfo().getAddress() ;
			AddressLog addressLog = new AddressLog() ;
			addressLog.setStreet1(address.getStreet1()) ;
			addressLog.setStreet2(address.getStreet2()) ;
			addressLog.setCityName(address.getCityName()) ;
			addressLog.setState(address.getStateOrProvince()) ;
			addressLog.setZipcode(address.getPostalCode()) ;
			addressLog.setCountryName(address.getCountryName()) ;
			addressLog.setOrderId(prePayOrderId) ;
			this.addressLogBiz.save(addressLog) ;
			
		}catch (Exception e) {
			log.error(e.getMessage()) ;
			session.setAttribute("exception", e);
			return "pay/payError";
		}
		log.info("confirmPayDetail end.") ;
		return "pay/confirmPayDetail";
	}


	/**
	 * 确认支付
	 * @param obj
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/confirmPayment" }, method = RequestMethod.POST)
	public String confirmPayment(HttpServletRequest request, HttpServletResponse response) {
		log.info("confirmPayment begin...") ;
		HttpSession session = request.getSession();
		String currencyCodeType = request.getParameter("currencyCodeType") ;
		String paymentAmount = request.getParameter("paymentAmount") ;
		
		CallerServices caller = (CallerServices) session.getAttribute("caller");

		DoExpressCheckoutPaymentRequestType pprequest = new DoExpressCheckoutPaymentRequestType();
		DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
		details.setToken(request.getParameter("token"));
		details.setPayerID(request.getParameter("PayerID"));
		details.setPaymentAction(PaymentActionCodeType
				.fromString((String) session.getAttribute("paymentType")));

		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.set_value(paymentAmount);
		orderTotal.setCurrencyID(CurrencyCodeType.fromString((String)currencyCodeType));
		paymentDetails.setOrderTotal(orderTotal);

		BasicAmountType itemTotal = new BasicAmountType();
		itemTotal.set_value(paymentAmount);
		itemTotal.setCurrencyID(CurrencyCodeType.fromString((String) currencyCodeType));
		paymentDetails.setItemTotal(itemTotal);

		PaymentDetailsItemType[] paymentItems = new PaymentDetailsItemType[1];
		PaymentDetailsItemType paymentItem = new PaymentDetailsItemType();
		//paypal详细信息里的订单物品名称  127个字节
		Long prePayOrderId = (Long)session.getAttribute("prePayOrderId") ;
		Order order = this.orderBiz.get(prePayOrderId) ;
		paymentItem.setName(order.getOrderNo());
		paymentItem.setQuantity(new BigInteger("1"));
		BasicAmountType amount = new BasicAmountType();
		amount.set_value(paymentAmount);
		amount.setCurrencyID(CurrencyCodeType.fromString((String) currencyCodeType));
		paymentItem.setAmount(amount);

		paymentItems[0] = paymentItem;
		paymentDetails.setPaymentDetailsItem(paymentItems);

		PaymentDetailsType[] payDetailType = new PaymentDetailsType[1];
		payDetailType[0] = paymentDetails;
		details.setPaymentDetails(payDetailType);

		pprequest.setDoExpressCheckoutPaymentRequestDetails(details);
		DoExpressCheckoutPaymentResponseType ppresponse = null;
		try {
			ppresponse = (DoExpressCheckoutPaymentResponseType) caller.call(
					"DoExpressCheckoutPayment", pprequest);

			if (!ppresponse.getAck().equals(AckCodeType.Success)
					&& !ppresponse.getAck().equals(
							AckCodeType.SuccessWithWarning)) {
				session.setAttribute("response", ppresponse);
				log.error("Ack:"+ppresponse.getAck()) ;
				log.error("CorrelationID:"+ppresponse.getCorrelationID()) ;
				log.error("Version:"+ppresponse.getVersion()) ;
				for (int i = 0; i < ppresponse.getErrors().length; i++) {
					log.error("Error Number:"+ppresponse.getErrors(i).getErrorCode()) ;
					log.error("Short Message:"+ppresponse.getErrors(i).getShortMessage()) ;
					log.error("Long Message:"+ppresponse.getErrors(i).getLongMessage()) ;
				}
				
				return "pay/APIError";
			}

			DoExpressCheckoutPaymentResponseDetailsType responseDetails = ppresponse
					.getDoExpressCheckoutPaymentResponseDetails();
			request.setAttribute("responseDetails", responseDetails) ;
			//完成付款后更新本地数据库order记录
			order.setPayTm(new Date()) ; //付款时间
			order.setOrderStatus(2); //已付款状态
			//paypal交易金额
			order.setGrossAmount(Double.parseDouble((responseDetails.getPaymentInfo())[0].getGrossAmount().get_value()));
			//本次支付关联的paypal交易号
			order.setTransactionId((responseDetails.getPaymentInfo())[0].getTransactionID()) ;
			CurrencyCodeType currencyCode = (responseDetails.getPaymentInfo())[0].getGrossAmount().getCurrencyID() ;
			currencyCode.getValue() ;
			order.setCurrencyId(currencyCode.getValue().toString());
			this.orderBiz.update(order) ;
			log.info("confirmPayment end ,deal successfull.") ;
			//付款邮件通知
			String subject = "Your Order Has Been Updated " ;
			EmailUtil email = new EmailUtil(null,null,null,null) ;
			email.sendGmailEmail(subject, new MailTpl().getPaiedMailTpl(order), order.getEmail()) ;
		} catch (Exception e) {
			log.error(e.getMessage()) ;
			session.setAttribute("exception", e);
			return "pay/payError";
		}
		return "redirect:/order/toMyOrder";

	}
	
}
