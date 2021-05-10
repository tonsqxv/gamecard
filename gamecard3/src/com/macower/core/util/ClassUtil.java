package com.macower.core.util;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ClassUtil {

	/**
	 * 复制对象属性
	 * @param dest 目标Bean
	 * @param src  实体Bean
	 */
	public static void copyProperties(Object dest,Object src){
		if (!dest.getClass().equals(src.getClass())) {
			throw new ClassCastException();
		}
		BeanWrapper srcWrapper = new BeanWrapperImpl(src);
		BeanWrapper destWrapper = new BeanWrapperImpl(dest);
		PropertyDescriptor pds[] = srcWrapper.getPropertyDescriptors();
		if (null != pds && 0 < pds.length) {
			for (PropertyDescriptor pd : pds) {
				String propName = pd.getName();
				// 读或写方法为空时不处理
				if (null == propName || null == pd.getWriteMethod()
						|| null == pd.getReadMethod()) {
					continue;
				}
				Object propValue = srcWrapper.getPropertyValue(propName);
				if(null == propValue){
					continue;
				}
				destWrapper.setPropertyValue(propName, propValue);
			}
		}
	}
}
