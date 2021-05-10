package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.ContactBiz;
import com.macower.businessdata.entity.Contact;
import com.macower.core.entity.Page;
import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/contact")
public class ContactAction {

	@Autowired
	private ContactBiz contactBiz;

	/**
	 * 后台管理-联系我们主页面
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/contact";
	}
	
	/**
	 * 前台-留言
	 * @param obj
	 * @param verifyCode
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/message" })
	public String message(Contact obj,String verifyCode,HttpServletRequest request,Model model) {
		String imgCode = (String)request.getSession().getAttribute("imgCode") ;
		if(imgCode == null){
			model.addAttribute("msg", "session expired") ;
		}else if(!imgCode.equals(verifyCode)){
			model.addAttribute("msg", "Captcha error") ;
		}else{
			obj.setStatus(1) ; //未回复
			obj.setCreateTm(new Date()) ;
			this.contactBiz.save(obj) ;
			model.addAttribute("msg", "your message submit successfull,we will reply in 24 hours.") ;
		}
		
		return "product/contact";
	}

	/**
	 * 后台管理-分页查询页面
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Contact obj, HttpServletResponse response) {
		List<Contact> root = new ArrayList<Contact>();
		Page<Contact> page = contactBiz.findPageBy(obj, obj.getStart()/obj.getLimit(), obj.getLimit());
		root = (List<Contact>)page.getData() ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		
		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response,map) ;
	}

	
	/**
	 * 后台管理-回复
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/reply" })
	public void reply(Contact obj, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			contactBiz.replay(obj);
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
	 * 后台管理-AJAX请求删除
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			contactBiz.deletes(ids);
		} catch (Exception e) {
			//删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		JsonUtils.returnJson(response,map) ;
	}
	
	
	
	

}
