package com.cabinet.util;

public class Transpose {

	public static String convertToHorizontal(String data, String delimiter){
		return data.replaceAll("\n", delimiter);
	}
	
	public static String convertToVertical(String data, String delimiter){
		return data.replaceAll(delimiter, "\n");
	}
	
	public static String transpose(String data, String delimiter){
		if(data.contains("\n")){
			return convertToHorizontal(data, delimiter.equals("|")? "\\|":delimiter).trim();
		} else {
			return convertToVertical(data, delimiter.equals("|")? "\\|":delimiter);
		}
	}
	
}


