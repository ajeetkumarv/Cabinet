package com.cabinet.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.cabinet.bean.PHI;
import com.cabinet.bean.RuleEngine;
import com.cabinet.dao.ReadFile;
import com.cabinet.dao.WriteFile;

public class FileChooserListener implements ActionListener{

	private JPanel panelForTable;
	private String absoluteFilePath;
	private TableModel dataModel;
	private String delimiter;
	private JTextField rowNumber;
	private JTextField columnName;
	private JLabel totalRows;
	private JLabel dataFilenamePath;
	private JButton goToCell;
	private JTextArea ruleArea;
	
	public FileChooserListener(JPanel panelForTable, JLabel dataFilenamePath, String delimiter, JTextField rowNumber, JLabel totalRows, JTextField columnName, JButton goToCell, JTextArea ruleArea){
		this.panelForTable=panelForTable;
		this.delimiter=delimiter;
		this.rowNumber=rowNumber;
		this.totalRows=totalRows;
		this.columnName=columnName;
		this.goToCell=goToCell;
		this.dataFilenamePath=dataFilenamePath;
		this.ruleArea=ruleArea;
	}
	
	public String getAbsoluteFilePath(){
		return absoluteFilePath;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		String buttonName = ae.getActionCommand();
		
		if(buttonName.equals("Open")){

			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Pipe separated file", "dat","csv","txt");
			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {

				absoluteFilePath = chooser.getSelectedFile().getAbsolutePath();
				openFileAndSetPanel(chooser);
			}
		} else if(buttonName.equals("Save")) {			
			saveData(getAbsoluteFilePath());
		} else if(buttonName.equals("Save As")) {
			
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Pipe separated file", "dat","csv","txt");
			chooser.setFileFilter(filter);
			
			int returnVal = chooser.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				absoluteFilePath = chooser.getSelectedFile().getAbsolutePath();
				dataFilenamePath.setText(absoluteFilePath);
				
			}			
			saveData(getAbsoluteFilePath());
		} else if(buttonName.equals("Run rule")){
			String rawRules = ruleArea.getText();
			String[] rulesLines = rawRules.split("\n");
			
			List<String> rules = new ArrayList<>();
			
			for(String rule: rulesLines){
				if(rule.startsWith("#") || rule.equals(""))
					continue;
				rules.add(rule);
			}
						
			RuleEngine ruleEngine = new RuleEngine(rules, dataModel);
			ruleEngine.processRules();
			ruleEngine.runRules();
			
			panelForTable.revalidate();
			panelForTable.repaint();
		}
	}
	
	public void openFileAndSetPanel(JFileChooser chooser){
		ReadFile r = new ReadFile(getAbsoluteFilePath(), delimiter);
		 try {
				PHI p = r.readFileAsMatrix();
				dataModel = new DataModel(p,delimiter);
				JTable table = new JTable(dataModel);
				table.setColumnSelectionAllowed(true);
				goToCell.addActionListener(new GotoCellActionListener(p.getHeader(), table, rowNumber, columnName));
				totalRows.setText("Total Rows: " + Integer.valueOf(table.getRowCount()).toString()+"    ");
				
				table.addMouseListener(new CellMouseListener(table,p.getHeader(),rowNumber,columnName));
				table.addKeyListener(new CellKeyListener(table, p.getHeader(), rowNumber, columnName));
				
				/*MyCellRenderer mcr = new MyCellRenderer();
				for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex ++) {
					table.getColumnModel().getColumn(columnIndex).setCellRenderer(mcr);
				}*/
								
			    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			    
			    int totalColumns = p.getHeader().length;
			    
			    for(int i=0;i<totalColumns;i++) {
			    	TableColumn tc = table.getColumnModel().getColumn(i);
			    	int len = p.getHeader()[i].length();
			    	tc.setPreferredWidth(len*10);
				    tc.setMinWidth(len*10-100);
				    tc.setMaxWidth(len*10+150);
			    }
			    JScrollPane scrollpane = new JScrollPane(table);
			    
			    dataFilenamePath.setText("Current File: " + absoluteFilePath);
			    
			    panelForTable.removeAll();
			    panelForTable.add(BorderLayout.NORTH, dataFilenamePath);
			    panelForTable.add(BorderLayout.CENTER, scrollpane );
			    panelForTable.add(BorderLayout.SOUTH, new JScrollPane(ruleArea));
			    panelForTable.revalidate();
			    panelForTable.repaint();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void saveData(String filePath){

		try {
			WriteFile.writeDataMatrix(filePath,dataModel.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
