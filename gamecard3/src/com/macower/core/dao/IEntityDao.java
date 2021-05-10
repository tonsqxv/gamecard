package com.macower.core.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;

import com.macower.core.entity.Page;

public abstract interface IEntityDao<E extends IEntity> extends IDao{
	
	 public SQLQuery createSQLQuery(String SQLQuery) ;
	
	 public Query createQuery(String HqlQuery) ;
	
	 public abstract void save(E paramE) ;
	 
	 public abstract E load(Long id) ;
	 
	 public E load(E entity) ;
	 
	 public E loadBy(String propertyName, String propertyValue)  ;
	 
	 public abstract E get(Long id) ;
	 
	 public E get(E entity)  ;
	 
	 public Page<E> findPageBy(DetachedCriteria dc, Integer pageNo, Integer pageSize)  ;
	 
	 public Page<E> findPageBy(DetachedCriteria dc, Integer pageNo, Integer pageSize, String sortField, boolean isAsc) ;
	 
	 public Page<E> findPageBy(E entity, Integer pageNo, Integer pageSize)  ;
	 
	 public Page<E> findPageBy(E entity, Integer pageNo, Integer pageSize, String sortField, boolean isAsc) ;
	 
	 public List<E> findAll() ;
	 
	 public List<E> findBy(DetachedCriteria detachedCriteria) ;
	 
	 public List<E> findBy(E entity)  ;
	 
	 public List<E> find(String hql) ;
	 
	 public List<E> find(String hql, Map<String, Object> args) ;
	 
	 public List<E> find(String hql, List<Object> args) ;
	 
	 public void delete(E entity) ;
	 
	 public int deleteByIds(Collection<Long> ids) ;
	 
	 public int deleteBatch(Collection<E> entities) ;
	 
	 public int deleteByIds(String ids) ;
	 
	 public void flush() ;
	 
	 public void update(E entity) ;
	 
	 public void updateBatch(Collection<E> entities) ;
	 
	 public void saveOrUpdate(E entity) ;
	 
	 public int executeUpdate(String sql) ;
	 
	 public int executeUpdate(String sql,List<Object> args) ;
	 
	 public int executeUpdate(String sql,Map<String ,Object> args) ;
	 
	 public List<java.util.Map<Object,Object>> executeSQLQuery(String sqlQuery)  ;
	 
	 public List<java.util.Map<Object,Object>> executeSQLQuery(String sqlQuery,Map<String, Object> args) ;
	 
	 public int countBy(DetachedCriteria detachedCriteria) ;
	 
	 public int countBy(E entity) ;
	 
	 public int count(String hql, Map<String, Object> args) ;
	 
	 public int count(String hql, List<Object> args) ;
	 
	 public int count(String sql) ;
	 

}
