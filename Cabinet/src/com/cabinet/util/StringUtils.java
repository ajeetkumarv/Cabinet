package com.cabinet.util;

public class StringUtils {

	public static String join(String data[], String delimiter){
		StringBuilder result = new StringBuilder();
		for(String dataPart: data){
			result.append(dataPart).append(delimiter);
		}
		
		return result.deleteCharAt(result.length()-1).toString();
	}
}
