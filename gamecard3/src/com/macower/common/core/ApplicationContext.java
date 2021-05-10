package com.macower.common.core;

import javax.servlet.ServletContext;

import com.macower.core.exception.BizException;


public class ApplicationContext {
	
	private static ServletContext context ;
	
	private ApplicationContext(){
	}
	
	public synchronized static  ServletContext getInstance(){
		if(context == null){
			throw new BizException("error:context not init,contact with admin.") ;
		}
		return context ;
	}

	public static void init(ServletContext sc){
		context = sc ;
	}
}
