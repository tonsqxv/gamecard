package com.macower.basedata.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/help")
public class HelpAction {

	/**
	 * 前台-帮助
	 */
	@RequestMapping({ "/" })
	public String help() {
		return "product/help";
	}
	/**
	 * 前台-联系我们
	 */
	@RequestMapping({ "/contact" })
	public String contact() {
		return "product/contact";
	}
	/**
	 * 前台-关于我们
	 */
	@RequestMapping({ "/about" })
	public String ourPromise() {
		return "product/about";
	}
	
	/**
	 * 前台-物流信息说明
	 */
	@RequestMapping({ "/shippingAndReturn" })
	public String shippingAndReturn() {
		return "product/shippingAndReturn";
	}
	
	
	
}
