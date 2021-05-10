package com.macower.basedata.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class Config {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getAssetsDir(HttpServletRequest request){
		String ctxPath = request.getSession().getServletContext()
		.getRealPath(File.separator)
		+ "assets"
		+ File.separator;
		
		return ctxPath ;
	}
	public static String getImagesDir(HttpServletRequest request){
		String ctxPath = request.getSession().getServletContext()
		.getRealPath(File.separator)
		+ "assets"
		+ File.separator
		+ "images"
		+ File.separator ;
		
		return ctxPath ;
	}
	public static String getProductDir(HttpServletRequest request){
		String ctxPath = request.getSession().getServletContext()
		.getRealPath(File.separator)
		+ "assets"
		+ File.separator
		+ "images"
		+ File.separator + "product" + File.separator;
		
		return ctxPath ;
	}

}
