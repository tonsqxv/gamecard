package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macower.businessdata.biz.MemberBiz;
import com.macower.businessdata.biz.OrderBiz;
import com.macower.businessdata.biz.ShopItemBiz;
import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.Order;
import com.macower.businessdata.entity.ShopItem;
import com.macower.businessdata.util.MailTpl;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.EmailUtil;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/member")
public class MemberAction {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberBiz memberBiz;
	
	@Autowired
	private ShopItemBiz shopItemBiz;
	
	@Autowired
	private OrderBiz orderBiz;

	/**
	 * 后台管理-会员管理主页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/member";
	}
	/**
	 * 前台-我的账户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/toMyAccount" },method=RequestMethod.GET)
	public String toMyAccount(HttpServletRequest request,Model model,RedirectAttributes ra) {
		Member member = (Member)request.getSession().getAttribute("member") ;
		if(member == null){
			ra.addFlashAttribute("msg", "You are not logged in") ;
			return "redirect:/member/login" ;
		}
		return "product/myAccount";
	}
	/**
	 * 前台修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/changePassword" },method=RequestMethod.GET)
	public String changePassword(Model model) {
		return "product/changePassword";
	}
	/**
	 * 前台修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/changMyPassword" })
	public String changMyPassword(String oldpassword,String newpassword,HttpServletRequest request,Model model,RedirectAttributes ra) {
		Member member = (Member)request.getSession().getAttribute("member") ;
		if(member == null){
			ra.addFlashAttribute("msg", "Sorry ,You are not logged in") ;
			return "redirect:/member/login" ;
		}
		if(!member.getPassword().equals(oldpassword)){
			model.addAttribute("error", "Sorry,your password error") ;
		}
		if(newpassword ==null || "".equals(newpassword.trim())){
			model.addAttribute("error", "Sorry,Please input your new password") ;
		}
		Member old = this.memberBiz.get(member.getId()) ;
		old.setPassword(newpassword) ;
		this.memberBiz.update(old) ;
		request.getSession().setAttribute("member", old) ;
		/**********密码修改成功 发送邮件***********************/
		//发送邮件
		String subject = "Password change request confirmed for n3ds-card.com " ;
		String emailto = old.getEmail() ;
		
		EmailUtil email = new EmailUtil(null,null,null,null) ;
		email.sendGmailEmail(subject, new MailTpl().getChangePasswordTpl(old), emailto) ;
		/*******************************/
		model.addAttribute("msg", "your password changed successfull") ;
		return "product/changePassword";
	}
	
	/**
	 * 前台-注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/register" },method=RequestMethod.GET)
	public String register(Model model) {
		return "product/memberRegister";
	}
	/**
	 * 前台-注册
	 * @param obj
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/memberRegister" },method=RequestMethod.POST)
	public String memberRegister(Member obj, Model model,RedirectAttributes ra) {
		
		//注册要做后台验证
		Member param = new Member() ;
		param.setEmail(obj.getEmail().trim()) ;
		List<Member> list = this.memberBiz.findBy(param) ;
		if(list != null && list.size() > 0){
			model.addAttribute("error", "email has been registered") ;
			return "redirect:/member/register";
		}
		obj.setRegisterTm(new Date()) ;
		this.memberBiz.save(obj) ;
		//注册成功 邮件提示
		//发送邮件
		String subject = "Thanks for Registering at n3ds-card.com" ;
		String emailto = obj.getEmail() ;
		
		
		EmailUtil email = new EmailUtil(null,null,null,null) ;
		email.sendGmailEmail(subject, new MailTpl().getMemberRegisterTpl(obj), emailto) ;
		//返回登录页面
		ra.addFlashAttribute("msg", "Register Successfully");
		return "redirect:/member/login";
	}
	/**
	 * 前台-登陆页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/login" },method=RequestMethod.GET)
	public String login(Model model) {
		return "product/memberLogin";
	}
	
	/**
	 * 前台-忘记密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/findPassword" })
	public String findPassword(Model model) {
		return "product/findPassword";
	}
	
	/**
	 * 前台-找回密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/findMyPassword" })
	public String findMyPassword(Member obj ,Model model,RedirectAttributes ra) {
		String returnUrl = "product/findPassword";
		Member param = new Member() ;
		param.setEmail(obj.getEmail()) ;
		List<Member> list = this.memberBiz.findBy(param) ;
		if(list != null && list.size() > 0){
			Member m = list.get(0) ;
			//发送邮件
			String subject = "Password change request confirmed for n3ds-card.com " ;
			String emailto = m.getEmail() ;
			
			EmailUtil email = new EmailUtil(null,null,null,null) ;
			email.sendGmailEmail(subject, new MailTpl().getFindPasswordTpl(m), emailto) ;
			
			//返回登录页面
			ra.addFlashAttribute("msg", "your password has been sent to you e-mail");
			returnUrl = "redirect:/member/login";
		}else{
			ra.addFlashAttribute("error", "<i class='icon-remove'></i>Sorry,can not find your data");
			returnUrl = "redirect:/member/findPassword";
		}
		return returnUrl ;
	}
	
	/**
	 * 前台-会员登录
	 * @param obj
	 * @param request
	 * @param model
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = { "/memberLogin" },method=RequestMethod.POST)
	public String memberlogin(Member obj ,HttpServletRequest request , Model model,RedirectAttributes ra) {
		String returnPage = "redirect:/index" ;
		HttpSession session =request.getSession() ;
		
		Member param = new Member() ;
		param.setEmail(obj.getEmail()) ;
		param.setPassword(obj.getPassword()) ;
		List<Member> list = this.memberBiz.findBy(param) ;
		if(list.size() > 0){ //登录成功
			Member member = list.get(0) ;
			request.getSession().setAttribute("member", member) ;
			//查询当前session是否有购物车记录  
			ShopItem itemParam = new ShopItem();
			itemParam.setSessionId(session.getId()) ;
			List<ShopItem> shopItemList = this.shopItemBiz.findBy(itemParam) ;
			if(shopItemList != null && shopItemList.size() > 0){
				for(ShopItem item : shopItemList){
					item.setMemberId(member.getId()) ;
					item.setSessionId(null) ;
					this.shopItemBiz.update(item) ;
				}
			}
			// 查询当前session是否有订单 
			Order orderParam = new Order() ;
			orderParam.setSessionId(session.getId()) ;
			List<Order> orderList = this.orderBiz.findMyOrder(orderParam) ;
			if(orderList != null && orderList.size() > 0){
				for(Order order : orderList){
					order.setMemberId(member.getId()) ;
					order.setSessionId(null) ;
					this.orderBiz.update(order) ;
				}
			}
			
			//记录会员的登录信息
			member.setLoginTm(new Date()) ;
			Integer oldLoginCount = member.getLoginCount() ;
			member.setLoginCount((oldLoginCount == null?0:oldLoginCount)+1) ;
			this.memberBiz.update(member) ;
			
			//判断请求的页面来源转向不同的页面
			//如果session中有prePayOrderId属性值说明登录是为了支付
			Long prePayOrderId = (Long)session.getAttribute("prePayOrderId") ;
			Long newsId = (Long)session.getAttribute("newsId") ; //新闻点评登录
			if(prePayOrderId != null){
				returnPage =  "redirect:/pay/prePayForm";
			}else if(newsId != null){
				session.removeAttribute("newsId") ;
				returnPage =  "redirect:/news/"+newsId+"/newsDetail";
			}
			
		}else{
			ra.addFlashAttribute("error", "username or password error !");
			returnPage =  "redirect:/member/login";
		}
		//成功后跳转到主页面
		return returnPage ;
	}
	
	/**
	 * 前台-会员退出
	 * @param obj
	 * @param request
	 * @param model
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = { "/logout" })
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession() ;
		//退出移除会员
		session.removeAttribute("member") ;
		//会员退出移除session中付款的标志  如果不移除 只要浏览器不关闭 就是同一个session 每次登录都会跳到支付页面去
		session.removeAttribute("prePayOrderId") ;
		//成功后跳转到主页面
		return "redirect:/index";
	}
	
	
	
	/**
	 * 前台-登录\注册时验证邮箱是否存在
	 * @param obj
	 * @param response
	 */
	@RequestMapping(value = { "/checkEmail" })
	public void checkEmail(Member obj, HttpServletResponse response) {
		String value = null ;
		Member param = new Member() ;
		param.setEmail(obj.getEmail().trim()) ;
		List<Member> list = this.memberBiz.findBy(param) ;
		Map<String, Object> map = new HashMap<String, Object>();
		if(list != null && list.size() > 0){
			value = "ok" ;
			map.put("success", value);
		}else{
			value = "no" ;
			map.put("success", value);
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 前台-支付页面登录 验证邮箱密码是否正确   之所以和正常登录页面处理方式不一样，原因分析：正常登录页面后台验证不通过，直接返回前台，而支付登录页面套提示用户邮箱密码错误，只能在前台ajax请求处理
	 * @param obj
	 * @param response
	 */
	@RequestMapping(value = { "/checkEmailAndPassword" })
	public void checkEmailAndPassword(Member obj, HttpServletResponse response) {
		String value = null ;
		Member param = new Member() ;
		param.setEmail(obj.getEmail()) ;
		param.setPassword(obj.getPassword()) ;
		List<Member> list = this.memberBiz.findBy(param) ;
		Map<String, Object> map = new HashMap<String, Object>();
		if(list != null && list.size() > 0){
			value = "ok" ;
			map.put("success", value);
		}else{
			value = "no" ;
			map.put("success", value);
		}
		JsonUtils.returnJson(response,map) ;
	}
	
	

	/**
	 * 后台管理-分页查询
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Member obj, HttpServletResponse response) {
		List<Member> root = new ArrayList<Member>();
		Page<Member> page = memberBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Member>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response,map) ;
	}

	/**
	 * 后台管理-添加
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/add" })
	public void add(Member obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			memberBiz.save(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response,map) ;
	}
	/**
	 * 后台管理-更新
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/update" })
	public void update(Member obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			memberBiz.updateBy(obj);
		} catch (BizException e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("error", e1.getMessage());
			map.put("success", false);
		}

		JsonUtils.returnJson(response,map) ;
	}

	/**
	 * 后台管理-AJAX请求批量删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			memberBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	/**
	 * 后台管理-初始化任务
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/init" })
	public void init(HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			memberBiz.init();
		} catch (Exception e) {
			//捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	

}
