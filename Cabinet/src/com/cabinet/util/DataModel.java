package com.cabinet.util;

import javax.swing.table.AbstractTableModel;

import com.cabinet.bean.PHI;

public class DataModel extends AbstractTableModel{

	private static final long serialVersionUID = -5740952021885997905L;
	private PHI p;
	private String[][] data;
	private String delimiter;
	public DataModel(PHI p, String delimiter){
		this.p=p;
		data=p.getDataMatrix();
		this.delimiter=delimiter;
	}
	
	@Override
	public int getColumnCount() {
		return p.getHeader().length;
	}

	@Override
	public int getRowCount() {
		return p.getDataMatrix().length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int column){
		return p.getHeader()[column];
	}

	public int getColumnIndex(String columnName){
		int index=-1;
		String[] allColumns =  p.getHeader();
		for(int i=0;i<allColumns.length;i++){
			if(columnName.equalsIgnoreCase(allColumns[i])){
				index=i;
				break;
			}
		}
		return index;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return true;
	}
	
	@Override
	public void setValueAt(Object value, int row, int column){
		data[row][column]=(String)value;
	}
	
	public String[][] getDataMatrix(){
		return data;
	}
	
	public String[] getHeader(){
		return p.getHeader();
	}
	
	@Override
	public String toString(){
		int maxRows = data.length;
		
		StringBuilder dataMatrixResult =  new StringBuilder();
		dataMatrixResult.append(StringUtils.join(p.getHeader(),delimiter)).append("\n");
		
		for(int i=0;i<maxRows;i++){
			dataMatrixResult.append(StringUtils.join(data[i],delimiter)).append("\n");
		}

		/* to remove the last blank line from data*/
		dataMatrixResult.deleteCharAt(dataMatrixResult.length()-1);
		return dataMatrixResult.toString();
	}
}
