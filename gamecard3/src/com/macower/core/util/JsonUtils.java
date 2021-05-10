package com.macower.core.util;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonUtils {
	protected static Log log = LogFactory.getLog(JsonUtils.class);
	
	public static void returnJson(HttpServletResponse response,Map<String, Object> map){
		JSONObject json = JSONObject.fromObject(map);
		try {
			PrintWriter out = response.getWriter();
			out.print(json.toString());// array
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void returnJson(HttpServletResponse response,String str){
		try {
			PrintWriter out = response.getWriter();
			out.print(str);// array
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void returnJson(HttpServletResponse response,Collection collection){
		JSONArray arr = JSONArray.fromObject(collection) ;
		try {
			PrintWriter out = response.getWriter();
			out.print(arr.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public static <T> List<T> jsonToList(String json, Class<T> clazz)
	    throws InstantiationException, IllegalAccessException, InvocationTargetException
	  {
	    List objects = new ArrayList();
	    if (json != null) {
	      JSONArray jsonArray = JSONArray.fromObject(json);
	      for (int i = 0; i < jsonArray.size(); i++) {
	        Object bean = clazz.newInstance();
	        JSONObject jsonObject = jsonArray.getJSONObject(i);
	        for (Iterator iterator = jsonObject.entrySet().iterator(); iterator.hasNext(); ) {
	          Map.Entry entry = (Map.Entry)iterator.next();
	          String property = (String)entry.getKey();
	          Object value = entry.getValue();
	          if ((property != null) && (property.length() > 0) && (value != null) && (JSONNull.getInstance() != value)) {
	            setProperty(bean, property, value);
	          }
	        }
	        objects.add(bean);
	      }
	    }
	    return objects;
	  }

	  private static void setProperty(Object bean, String property, Object value) {
	    try {
	      if ((Date.class == findSetterClass(bean.getClass(), property)) && ((value instanceof String))) {
	        log.debug("pase date: " + value);
	        Date date = DateUtils.convert((String)value);
	        if (date == null)
	          return;
	        value = date;
	      }
	      org.apache.commons.beanutils.BeanUtils.setProperty(bean, property, value);
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	    }
	  }

	  private static Class<?> findSetterClass(Class<?> clazz, String property) throws NoSuchMethodException {
	    String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
	    String setterName = "set" + upper;
	    Method[] methods = clazz.getMethods();
	    for (Method method : methods) {
	      if ((setterName.equals(method.getName())) && (method.getParameterTypes().length == 1)) {
	        return method.getParameterTypes()[0];
	      }
	    }
	    throw new NoSuchMethodException("No such method \"" + setterName + "\" in class \"" + clazz.getCanonicalName() + 
	      "\"");
	  }


}
