package com.cabinet.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;

public class GotoCellActionListener implements ActionListener{

	private JTable table;
	private String[] header;
	private JTextField row; 
	private JTextField columnName;
	
	public GotoCellActionListener(String[] header, JTable table, JTextField row, JTextField columnName){
		this.header=header;
		this.table=table;
		this.row=row;
		this.columnName=columnName;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		table.changeSelection(getRowIndex(), getColumnIndex(columnName.getText().trim()), false, false);
	}
	
	private int getColumnIndex(String columnName){
		
		int index=0;
		for(int i=0;i<header.length;i++){
			if(columnName.equalsIgnoreCase(header[i]))
				index = i;
		}
		return index;
	}
	
	private int getRowIndex(){
		int rowIndex=0;
		try {
			rowIndex = Integer.parseInt(row.getText())-1;
			if(rowIndex<0){
				return 0;
			}
		}
		catch(NumberFormatException ex){}
		
		return rowIndex;
	}

}
