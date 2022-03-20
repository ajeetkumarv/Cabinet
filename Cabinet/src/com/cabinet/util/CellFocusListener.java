package com.cabinet.util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTable;
import javax.swing.JTextField;

public class CellFocusListener implements FocusListener{

	JTable table;
	JTextField rowNumber;
	
	public CellFocusListener(JTable table, JTextField rowNumber){
		this.table=table;
		this.rowNumber=rowNumber;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		rowNumber.setText(Integer.valueOf(table.getSelectedRow()).toString());
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		rowNumber.setText(Integer.valueOf(table.getSelectedRow()).toString());
	}

}
