package com.macower.sample.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macower.core.exception.BizException;
import com.macower.core.util.JsonUtils;
import com.macower.sample.biz.SampleBiz;
import com.macower.sample.entity.Sample;



@Controller
@RequestMapping(value="/sample")
public class SampleAction {
	
	@Resource
	private SampleBiz sampleBiz ;


	/**
	 * ��ѯ�б�
	 * @param model
	 * @return
	 */
	@RequestMapping({"/list"})
	public String list(Model model) {
		System.out.println("list...");
		
		List<Sample> samples = sampleBiz.listBy(new Sample()) ;
		System.out.println("samples.size = "+samples.size());
		model.addAttribute("samples",samples);
		return "sample/list";
	}
	
	/**
	 * ��������ҳ��   ����ҳ������ʽget
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		System.out.println("to add.....");
		return "sample/add";
	}
	
	
	/**
	 * ִ����������    �޸Ĳ�������ʽpost
	 * @param sample
	 * @param binding
	 * @return  
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Sample sample) {
		System.out.println("add.....");
		System.out.println(sample.getEmail()+"------->"+sample.getUsername());
		sample.setCreateTm(new Date()) ;
		sampleBiz.save(sample) ;
		return "redirect:/sample/list";
	}
	

	/**
	 * �����޸�ҳ��   ����ҳ������ʽget
	 * @param model
	 * @return
	 */
	@RequestMapping(value="{username}/update",method=RequestMethod.GET)
	public String update(@PathVariable String username,Model model) {
		System.out.println("to update.....");
		System.out.println(username);
		model.addAttribute("sample",new Sample());
		return "sample/update";
	}
	
	/**
	 * ִ���޸Ĳ���    �޸Ĳ�������ʽpost
	 * @param sample
	 * @param binding
	 * @return
	 */
	@RequestMapping(value="{username}/update",method=RequestMethod.POST)
	public String update(@Valid Sample sample,BindingResult binding) {
		System.out.println("update.....");
		if(binding.hasErrors()) {
			return "sample/update";
		}
		
		this.sampleBiz.update(sample) ;
		return "redirect:/sample/list";
	}

	/**
	 * ɾ��
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/{username}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String id) {
		System.out.println("delete...");
		this.sampleBiz.delete(id) ;
		return "redirect:/sample/";
	}
	
	/**
	 * ɾ��
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/{id}/show",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model) {
		System.out.println("show...");
		Sample sample = this.sampleBiz.show(id) ;
		model.addAttribute("sample", sample) ;
		return "sample/show";
	}
	
	/**
	 * ������ض��������ò���  ����ͨ������ķ�ʽ����
	 * @param model
	 * @param ra
	 * @return
	 */
	@RequestMapping("/redir")
	public String redir(Model model,RedirectAttributes ra) {
		ra.addFlashAttribute("redirect_attr", "redirect_attr value 111");
		return "redirect:/sample/";
	}
	

	/**
	 * �쳣����  ���ҵ���쳣�Ĵ���
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(value={BizException.class})
	public String handlerException(Exception ex,HttpServletRequest req) {
		System.out.println("**************************bizException....");
		req.setAttribute("ex",ex);
		return "common/error";
	}
	
	@RequestMapping(value="/ajax")
	public void ajax(Sample sample ,HttpServletResponse response){
		   System.out.println("ajax...");
		response.setContentType("text/html; charset=utf-8");

		List<Sample> list = new ArrayList<Sample>();
		Sample s1 = new Sample(111L,"sample1","123456","wwww@qq.com");
		Sample s2 = new Sample(112L,"sample2","654321","www2w@qq.com");
		list.add(s1);
		list.add(s2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", "true");
		map.put("msg", "success");
		map.put("list", list) ;
		//JsonUtils.ReturnJson(response, map);
		JsonUtils.returnJson(response, "kk");
			
	}
	
}
