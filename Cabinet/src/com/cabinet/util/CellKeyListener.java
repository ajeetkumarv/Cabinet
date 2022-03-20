package com.cabinet.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.JTextField;

public class CellKeyListener implements KeyListener{

	private JTable table;
	private JTextField rowNumber;
	private JTextField columnName;
	private String[] columnHeader;
	
	public CellKeyListener(JTable table, String[] columnHeader, JTextField rowNumber, JTextField columnName){
		this.table=table;
		this.rowNumber=rowNumber;
		this.columnName=columnName;
		this.columnHeader=columnHeader;
	}
	
	public String getRowNumber(){
		return Integer.valueOf(table.getSelectedRow()+1).toString();
	}
	
	public String getColumnName(){
		return columnHeader[table.getSelectedColumn()];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		rowNumber.setText(getRowNumber());
		columnName.setText(getColumnName());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		rowNumber.setText(getRowNumber());
		columnName.setText(getColumnName());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
