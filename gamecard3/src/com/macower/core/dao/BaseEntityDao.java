package com.macower.core.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.macower.core.entity.Page;
import com.macower.core.util.StringUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseEntityDao<E extends IEntity> extends BaseDao implements
		IEntityDao<E> {

	protected final Class<E> entityClass;

	public BaseEntityDao(Class<E> entityClass) {
		this.entityClass = entityClass;
		if (entityClass == null)
			throw new RuntimeException(getClass().getCanonicalName()
					+ "未定义泛型! 继承于:" + BaseEntityDao.class.getCanonicalName()
					+ "的类都必需声明所操作实体的泛型");
	}

	public BaseEntityDao() {
		this.entityClass = getInitEntityClass();
		if (this.entityClass == null) {
			String clsName = getClass().getSimpleName();
			throw new RuntimeException(getClass().getCanonicalName()
					+ "未定义泛型! 继承于:" + BaseEntityDao.class.getCanonicalName()
					+ "的类都必需声明所操作实体的泛型, 如:\npublic class " + clsName
					+ " extends " + BaseEntityDao.class.getSimpleName() + "<"
					+ clsName.substring(0, clsName.length() - 3)
					+ "> implements I" + clsName + "{\n\t...\n}");
		}
	}

	protected Class<E> getInitEntityClass() {
		Class cls = getEntityTypeFromClass(getClass());
		if (cls == null) {
			cls = getFirstGenericType(getClass());
		}
		return cls;
	}

	private Class<E> getFirstGenericType(Class<?> beanClass) {
		Type type = beanClass.getGenericSuperclass();
		if ((type != null) && (ParameterizedType.class.isInstance(type))) {
			ParameterizedType pType = (ParameterizedType) type;
			Type argType = pType.getActualTypeArguments()[0];
			if ((argType instanceof Class)) {
				return (Class) argType;
			}
			return null;
		}

		return getFirstGenericType(beanClass.getSuperclass());
	}

	protected Class<E> getEntityTypeFromClass(Class<?> beanClass) {
		if (BaseEntityDao.class.isAssignableFrom(beanClass)) {
			Type type = beanClass.getGenericSuperclass();
			if ((type != null) && (ParameterizedType.class.isInstance(type))) {
				ParameterizedType pType = (ParameterizedType) type;
				if (pType.getRawType().equals(BaseEntityDao.class)) {
					Type argType = pType.getActualTypeArguments()[0];
					if ((argType instanceof Class)) {
						return (Class) argType;
					}  
					return null;
				}
			}

			return getEntityTypeFromClass(beanClass.getSuperclass());
		}
		return null;
	}
	
	@Override
	public SQLQuery createSQLQuery(String SQLQuery){
		return this.getSession().createSQLQuery(SQLQuery) ;
	}
	@Override
	public Query createQuery(String HqlQuery){
		return this.getSession().createQuery(HqlQuery) ;
	}

	@Override
	public void save(E paramE) {
		this.getSession().save(paramE);
	}

	@Override
	public E load(Long id) {
		return (E) this.getSession().load(this.entityClass, id);
	}

	@Override
	public E load(E entity) {
		return load(entity.getId());
	}

	@Override
	public E loadBy(String propertyName, String propertyValue) {
		List list = this
				.getSession()
				.createQuery(
						"from " + this.entityClass.getName() + " where "
								+ propertyName + " = ?")
				.setParameter(0, propertyValue).list();
		if (list.size() > 0){
			return (E) list.get(0);
		}
			
		return null;
	}

	@Override
	public E get(Long id) {
		return (E) this.getSession().get(entityClass, id);
	}

	@Override
	public E get(E entity) {
		return (E) this.getSession().get(entityClass, entity.getId());
	}

	@Override
	public Page<E> findPageBy(DetachedCriteria dc, Integer pageNo,
			Integer pageSize) {
		Criteria criteria = dc.getExecutableCriteria(this.getSession());
		int totalSize = Integer.valueOf(((Number) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue());
		criteria.setProjection(null);

		criteria.setFirstResult(pageNo * pageSize);
		criteria.setMaxResults(pageSize);

		return new Page(totalSize,pageSize,pageNo, criteria.list());
	}

	@Override
	public Page<E> findPageBy(DetachedCriteria dc, Integer pageNo,
			Integer pageSize, String sortField, boolean isAsc) {
		Criteria criteria = dc.getExecutableCriteria(this.getSession());
		int totalSize = Integer.valueOf(((Number) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue());
		criteria.setProjection(null);

		if (StringUtils.isNotEmpty(sortField)) {
			criteria.addOrder(isAsc ? Order.asc(sortField) : Order
					.desc(sortField));
		}
		criteria.setFirstResult(pageNo * pageSize);
		criteria.setMaxResults(pageSize);

		return new Page(totalSize,pageSize,pageNo, criteria.list());
	}

	@Override
	public Page<E> findPageBy(E entity, Integer pageNo, Integer pageSize) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.add(Example.create(entity));

		int totalSize = Integer.valueOf(((Number) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue());
		criteria.setProjection(null);

		criteria.setFirstResult(pageNo * pageSize);
		criteria.setMaxResults(pageSize);

		return new Page(totalSize,pageSize,pageNo, criteria.list());
	}

	@Override
	public Page<E> findPageBy(E entity, Integer pageNo, Integer pageSize,
			String sortField, boolean isAsc) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.add(Example.create(entity));

		int totalSize = Integer.valueOf(((Number) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue());
		criteria.setProjection(null);

		if (StringUtils.isNotEmpty(sortField)) {
			criteria.addOrder(isAsc ? Order.asc(sortField) : Order
					.desc(sortField));
		}

		criteria.setFirstResult(pageNo * pageSize);
		criteria.setMaxResults(pageSize);

		return new Page(totalSize,pageSize,pageNo, criteria.list());
	}

	@Override
	public List<E> findAll() {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public List<E> findBy(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(this
				.getSession());
		return criteria.list();
	}

	@Override
	public List<E> findBy(E entity) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.add(Example.create(entity));
		return criteria.list();
	}
	
	@Override
	public List<java.util.Map<Object,Object>> executeSQLQuery(String sqlQuery) {
		SQLQuery query = this.getSession().createSQLQuery(sqlQuery) ;
		return query.list();
	}
	
	@Override
	public List<java.util.Map<Object,Object>> executeSQLQuery(String sqlQuery,Map<String, Object> args) {
		SQLQuery query = this.getSession().createSQLQuery(sqlQuery) ;
		query.setProperties(args) ;
		return query.list();
	}
	
	@Override
	public List<E> find(String hql) {
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<E> find(String hql, Map<String, Object> args) {
		Query query = this.getSession().createQuery(hql);
		query.setProperties(args);
		return query.list();
	}

	@Override
	public List<E> find(String hql, List args) {
		Query query = this.getSession().createQuery(hql);
		Object[] values = args.toArray();
		
		if ((args != null) && (args.size() > 0)) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	@Override
	public void delete(E entity) {
		 this.getSession().delete(entity) ;
		 this.flush() ;
		 
	}

	@Override
	public int deleteByIds(Collection<Long> ids) {
		if ((ids == null) || (ids.size() == 0)) {
			return 0;
		}
		
		String hqlString = "delete from " + this.entityClass.getName()
				+ " where id in (:ids)";
		Query query = this.getSession().createQuery(hqlString);
		query.setParameterList("ids", ids);
		return Integer.valueOf(query.executeUpdate());

	}
	
	@Override
	public int deleteBatch(Collection<E> entities) {
		if ((entities == null) || (entities.size() == 0)) {
			return 0;
		}
		String ids = "" ;
		for(E obj:entities){
			if(StringUtils.isNotEmpty(String.valueOf(obj.getId()))){
				ids = ids+obj.getId() +"," ;
			}
		}
		if(!"".equals(ids.trim())){
			ids = ids.substring(0, ids.length()-1) ;
		}
		String hqlString = "delete from " + this.entityClass.getName()
				+ " where id in (:ids)";
		Query query = this.getSession().createQuery(hqlString);
		query.setParameterList("ids", toList(ids));
		return Integer.valueOf(query.executeUpdate());

	}
	
	public int deleteByIds(String ids) {
		return deleteByIds(toList(ids)) ;
	}
	
	private List<Long> toList(String ids) {
		List<Long> list = new ArrayList<Long>() ;
		String[] arr = ids.split(",") ;
		for(String s: arr){
			Long l = Long.valueOf(s) ;
			list.add(l) ;
		}
		return list ;
	}
	
	
	public void flush(){
		this.getSession().flush() ;
	}
	@Override
	 public void update(E entity) {
		 this.getSession().update(entity) ;
		 this.flush() ;
	 }
	
	@Override
	 public void updateBatch(Collection<E> entities) {
		for (IEntity entity : entities){
			this.getSession().update(entity) ;
		}
		 this.flush() ;
	 }
	
	@Override
	 public void saveOrUpdate(E entity) {
		 this.getSession().saveOrUpdate(entity) ;
		 this.flush() ;
	 }
	
	@Override
	 public int executeUpdate(String sql) {
		 return this.getSession().createSQLQuery(sql).executeUpdate() ;
		 
	 }
	@Override
	 public int executeUpdate(String sql,List<Object> args) {
		Query query =  this.getSession().createSQLQuery(sql) ;
		 Object[] values = args.toArray() ;
		 if ((args != null) && (args.size() > 0)) {
		 for (int i = 0; i < values.length; i++) {
	            query.setParameter(i, values[i]);
	          }
		 }
		 return query.executeUpdate() ;
		 
	 }
	@Override
	 public int executeUpdate(String sql,Map<String ,Object> args) {
		 return this.getSession().createSQLQuery(sql).setProperties(args).executeUpdate() ;
		 
	 }
	@Override
	 public int countBy(DetachedCriteria dc){
		 Criteria criteria = dc.getExecutableCriteria(this.getSession());
		 return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		 
	 }

	@Override
	 public int countBy(E entity) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
        criteria.add(Example.create(entity));
        return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue() ;
        
	 }
	
	public int count(String hql, Map<String, Object> args){
		Query query = this.getSession().createQuery(hql);
        query.setProperties(args);
        return ((Number)query.uniqueResult()).intValue();
	}
	 
	public int count(String hql, List<Object> args) {
		Query query = this.getSession().createQuery(hql);
		
		if ((args != null) && (args.size() > 0)) {
			Object[] values = args.toArray();
	          for (int i = 0; i < values.length; i++) {
	            query.setParameter(i, values[i]);
	          }
		}
		return ((Number)query.uniqueResult()).intValue() ;
		
	}
	
	 public int count(String sql){
		return  ((Number)this.getSession().createSQLQuery(sql).uniqueResult()).intValue() ;
	 }

}
