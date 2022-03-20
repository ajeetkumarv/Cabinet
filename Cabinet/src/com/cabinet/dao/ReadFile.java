package com.cabinet.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cabinet.bean.PHI;

public class ReadFile {

	private String delimiter;
	private String absolutePathOfFile;
	
	public ReadFile(String absolutePathOfFile, String delimiter){
		this.absolutePathOfFile = absolutePathOfFile;
		this.delimiter = delimiter.equals("|")? "\\|":delimiter;
	}
	
	public void setDelimiter(String delimiter){
		this.delimiter = delimiter;
	}
	
	public void setFile(String absolutePathOfFile){
		this.absolutePathOfFile = absolutePathOfFile;
	}
	
	public String getDelimiter() {
		return delimiter;
	}

	public String getAbsolutePathOfFile() {
		return absolutePathOfFile;
	}

	public PHI readFileAsMatrix() throws FileNotFoundException, IOException{
		
		PHI phi = new PHI();
		String header;
		
		int cols;
		
		List<String> dataRows = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(absolutePathOfFile))) {
			header = br.readLine();
			cols = header.split(delimiter).length;
			phi.setHeader(header.split(delimiter));
			
		    for(String line ; (line = br.readLine()) != null;) {
		    	dataRows.add(line);
		    }
		}
		
		if(!dataRows.isEmpty()){
			
			String[] dataRowArray = dataRows.toArray(new String[dataRows.size()]);
			int rows = dataRowArray.length;
			
			String[] temp = new String[cols];
			Arrays.fill(temp,"");
			String[][] phiMatrix = new String[rows][cols];
			int row=0;
			for(String dataRow: dataRowArray){
				temp=new String[cols];
				Arrays.fill(temp,"");
				String[] dataElements= dataRow.split(delimiter);
				System.arraycopy(dataElements, 0, temp, 0, dataElements.length);
				phiMatrix[row++]=temp;
			}
			phi.setDataMatrix(phiMatrix);
		}
		return phi;
	}
}
