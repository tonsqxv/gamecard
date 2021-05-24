package com.macower.businessdata.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.businessdata.biz.DiscodeBiz;
import com.macower.businessdata.entity.Discode;
import com.macower.core.entity.Page;
import com.macower.core.util.JsonUtils;
import com.macower.core.util.StringUtils;

@Controller
@RequestMapping(value = "/discode")
public class DiscodeAction {

	@Autowired
	private DiscodeBiz discodeBiz;

	/**
	 * 后台管理-联系我们主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/main" })
	public String main(Model model) {
		return "businessdata/discode";
	}

	/**
	 * 后台管理-分页查询页面
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/list" })
	public void list(Discode obj, HttpServletResponse response) {
		List<Discode> root = new ArrayList<Discode>();
		Page<Discode> page = discodeBiz.findPageBy(obj,
				obj.getStart() / obj.getLimit(), obj.getLimit());
		root = (List<Discode>) page.getData();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		map.put("root", root);
		map.put("totalSize", page.getTotalSize());

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-批量生成折扣码
	 * 
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/create" })
	public void create(Integer count, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			discodeBiz.createBatch(count);
		} catch (Exception e) {
			// 删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-AJAX请求删除
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/delete" })
	public void delete(String ids, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		try {
			discodeBiz.deletes(ids);
		} catch (Exception e) {
			// 删除捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 前台-验证折扣码是否有效
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/validDiscode" })
	public void validDiscode(String discountCode, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String value = null;
		if (StringUtils.isEmpty(discountCode)) {
			value = "ok";
		} else {
			value = "no";
			Discode param = new Discode();
			param.setDiscode(discountCode.trim());
			List<Discode> list = discodeBiz.findBy(param);
			// 如果list为空 折扣码错误 如果存在取第一个元素 判断折扣码状态
			if (list != null && list.size() > 0) {
				Discode code = list.get(0);
				if (code.getStatus() == 1) {// 未使用
					value = "ok";
				} else if (code.getStatus() == 2) { // 已使用
					value = "2";
				} else if (code.getStatus() == 3) { // 已过期
					value = "3";
				}
			}
			map.put("success", value);
		}

		JsonUtils.returnJson(response, map);
	}

	/**
	 * 后台管理-验证折扣码是否有效
	 * 
	 * @param ids
	 * @param response
	 */
	@RequestMapping({ "/emailSendDiscode" })
	public void emailSendDiscode(String email, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			discodeBiz.emailSendDiscode(email);
		} catch (Exception e) {
			// 捕获所有的异常
			map.put("success", false);
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}

		JsonUtils.returnJson(response, map);
	}

	
}
