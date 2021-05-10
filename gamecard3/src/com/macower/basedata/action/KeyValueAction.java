package com.macower.basedata.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.macower.basedata.biz.KeyValueBiz;
import com.macower.basedata.entity.KeyValue;
import com.macower.core.util.JsonUtils;

@Controller
@RequestMapping(value = "/keyValue")
public class KeyValueAction {

	@Autowired
	private KeyValueBiz keyValueBiz;

	/**
	 * 后台管理-按条件查询所有
	 * @param obj
	 * @param response
	 */
	@RequestMapping({ "/listBy" })
	public void listBy(KeyValue obj, HttpServletResponse response) {
		List<KeyValue> root = this.keyValueBiz.findBy(obj) ;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("success", true);
		map.put("root", root);

		JsonUtils.returnJson(response,map) ;
	}

}
