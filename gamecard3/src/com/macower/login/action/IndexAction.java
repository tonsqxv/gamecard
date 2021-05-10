package com.macower.login.action;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexAction {
	
	@RequestMapping("index")
    public String index(Model model,HttpServletRequest request) {
		
        return "product/main";
    }
	

	@RequestMapping("home")
    public String home() {
        return "login/home";
    }
	
}
