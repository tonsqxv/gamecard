package com.macower.core.util;

import java.io.File;

public class FileUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		deleteFile("E:\\tmp\\201307171004010046.bak");

	}
	
	public static void deleteFile(String path){
		File file = new File(path);
		if(file.exists()){
			file.delete() ;
		}
	}

}
