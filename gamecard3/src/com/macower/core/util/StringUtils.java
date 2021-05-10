package com.macower.core.util;

public final class StringUtils {

	
	public static boolean isNotEmpty(String s) {
		return (s != null) && (!"".equals(s.trim())) && (!"null".equals(s.trim()));
	}
	
	public static boolean isEmpty(String s) {
		return (s == null) || ("".equals(s.trim())) || ("null".equals(s.trim()));
	}

}
