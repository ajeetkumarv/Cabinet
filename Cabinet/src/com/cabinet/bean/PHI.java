package com.cabinet.bean;

public class PHI {

	private String[] header;
	private String[][] dataMatrix;
	
	public PHI(){
		header = null;
		dataMatrix = null;
	}
	
	public PHI(String [] header, String[][] dataMatrix){
		this.header = header;
		this.dataMatrix = dataMatrix;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[][] getDataMatrix() {
		return dataMatrix;
	}

	public void setDataMatrix(String[][] dataMatrix) {
		this.dataMatrix = dataMatrix;
	}	
}
