package com.macower.businessdata.util;

import java.util.Random;

public class UniqueStringGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = new UniqueStringGenerator().generateCode() ;
		System.out.println(s);

	}

	public String generateCode() {
		String[] arr = {
				"a","b","c","d","e","f","g","h","i","j","k","m","n","l","o","p","q","r","s","t","u","v","w","x","y","z",
				"A","B","C","D","E","F","G","H","I","J","K","M","N","L","O","P","Q","R","S","T","U","V","W","X","Y","Z",
				"1","2","3","4","5","6","7","8","9","@","#","$","%","&"
				} ;
		System.out.println(arr.length);
		String value = "" ;
		for(int i=0 ; i<7 ; i++){
			int random = new Random().nextInt(arr.length) ;
			value += arr[random] ;
		}
		return value ;
	}
	

}
