package com.cabinet.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.JTextField;

public class CellMouseListener implements MouseListener{

	private JTable table;
	private JTextField rowNumber;
	private JTextField columnName;
	private String[] columnHeader;
	
	public CellMouseListener(JTable table, String[] columnHeader,JTextField rowNumber, JTextField columnName){
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
	public void mouseClicked(MouseEvent e) {
		rowNumber.setText(getRowNumber());
		columnName.setText(getColumnName());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		rowNumber.setText(getRowNumber());
		columnName.setText(getColumnName());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
