package com.macower.core.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao implements IDao{
	
	@Resource
	protected SessionFactory sessionFactory ;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession() ;
	}

}
