package com.macower.common.interceptor;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macower.businessdata.util.DBManager;
import com.macower.common.core.ApplicationContext;


public class InitListener implements ServletContextListener {

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log.info("InitListener contextDestroyed...") ;

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//初始化环境
		ApplicationContext.init(event.getServletContext()) ;
		//初始化所有任务状态
		DBManager dbManager = new DBManager() ;
		dbManager.executeUpdate("update tb_task set status = 3 where status = 1") ;
		dbManager.close() ;
		log.info("InitListener contextInitialized...") ;
	}

}
