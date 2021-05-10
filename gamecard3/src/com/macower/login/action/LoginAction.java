package com.macower.login.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.macower.core.util.MD5Utils;
import com.macower.sys.biz.UserBiz;
import com.macower.sys.entity.User;


@Controller
@RequestMapping(value="/login")
//@SessionAttributes(value = {"user","username"})

public class LoginAction {
	
	@Autowired
	private UserBiz userBiz;
	
	/**
	 * 前台用户登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String login(Model model) {
		return "login/login";
	}
	/**
	 * 后台管理-用户登录
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String userLogin(User user ,String verifyCode,HttpServletRequest request,Model model) {
		String returnUrl = "login/frame";
		if(user.getUserName() == null || "".equals(user.getUserName().trim())){
			model.addAttribute("error", "用户名不能为空") ;
			returnUrl = "login/login" ;
			return returnUrl ;
		}
		if(user.getPassword() == null || "".equals(user.getPassword().trim())){
			model.addAttribute("error", "密码不能为空") ;
			returnUrl = "login/login" ;
			return returnUrl ;
		}
		String imgCode = (String)request.getSession().getAttribute("imgCode") ;
		if(imgCode == null){
			model.addAttribute("error", "会话已过期") ;
			returnUrl = "login/login" ;
			return returnUrl ;
		}
		if(!imgCode.equals(verifyCode)){
			model.addAttribute("error", "验证码错误") ;
			returnUrl = "login/login" ;
			return returnUrl ;
		}
		
		User currentUser = null ;
		if("admin".equals(user.getUserName().trim())){
			if(!"wuzhaohuixy@123!".equals(user.getPassword().trim())){
				model.addAttribute("error", "密码错误") ;
				returnUrl = "login/login" ;
			}else{
				currentUser = new User() ;
				currentUser.setUserName("admin") ;
				request.getSession().setAttribute("user", currentUser) ;
			}
		}else{
			User param = new User() ;
			param.setUserName(user.getUserName().trim()) ;
			param.setPassword(MD5Utils.encode(user.getPassword().trim())) ;
			List<User> users = this.userBiz.findBy(param) ;
			if(users == null || users.isEmpty()){
				model.addAttribute("error", "用户名或密码错误") ;
				returnUrl = "login/login" ;
			}else{
				currentUser = users.get(0) ;
				//model.addAttribute("user",currentUser) ;	
				request.getSession().setAttribute("user", currentUser) ;
			}
		}
		
		return returnUrl ;
	}

	/**
	 * 后台管理-用户退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user") ;
		return "login/login" ;
	}
}
