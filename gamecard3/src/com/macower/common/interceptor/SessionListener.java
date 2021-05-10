package com.macower.common.interceptor;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener{


	protected Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.info(new Date()+":------sessionCreated---------"+event.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.info(new Date()+":-----sessionDestroyed-------"+event.getSession().getId());
		
	}

}
