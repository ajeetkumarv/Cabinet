package com.cabinet.home;

import javax.swing.*;

import com.cabinet.util.FileChooserListener;
import com.cabinet.util.TransposeListerner;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private GraphicsEnvironment env;
	private Dimension screenDimension;
	
	JLabel rowDisplay;
	JLabel totalRows;
	JLabel columnNameLbl;
	JLabel dataFileNamePath;
	
	JTextField rowNumber;
	JTextField columnName;
	JTextField delimiter;
	
	JPanel north, south, center, east;
	
	JButton transpose;
	JButton openFile, saveFile, saveAsFile;
	JButton goToCell;
	JButton runRule;
	
	
	JTextArea ruleArea;
	
	public Home() {
		super("Cabinet");
		
		env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		screenDimension = env.getMaximumWindowBounds().getSize();
		
		dataFileNamePath = new JLabel();
		
		openFile = new JButton("Open");
		saveFile = new JButton("Save");
		saveAsFile = new JButton("Save As");
		
		goToCell = new JButton("Go to Cell");
		
		runRule = new JButton("Run rule");
		
		transpose = new JButton("Transpose");
		transpose.addActionListener(new TransposeListerner());
		
		totalRows = new JLabel();
		totalRows.setForeground(Color.white);
		
		columnNameLbl=new JLabel("    Column Name ");
		columnNameLbl.setForeground(Color.white);
		
		delimiter = new JTextField("|",3);
		
		columnName = new JTextField(20);
		columnNameLbl.setLabelFor(columnName);
		columnName.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				columnName.selectAll();
			}
		});
		
		rowNumber = new JTextField(4);
		rowDisplay = new JLabel("Selected Row Number");
		rowDisplay.setLabelFor(rowNumber);
		rowDisplay.setForeground(Color.white);
		
		ruleArea = new JTextArea(10,100);
		setPanels();
		addPanels();

		ActionListener openSaveListener = new FileChooserListener(center, dataFileNamePath,delimiter.getText(),rowNumber, totalRows, columnName, goToCell, ruleArea);
		
		openFile.addActionListener(openSaveListener);
		saveFile.addActionListener(openSaveListener);
		saveAsFile.addActionListener(openSaveListener);
		runRule.addActionListener(openSaveListener);
		
		setFrameProperties();
	}
	
	void setFrameProperties() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenDimension);
		setSize(1000,400);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
	}
	
	void setPanels(){
		
		east = new JPanel(new BorderLayout());
		east.add(BorderLayout.NORTH, new JLabel("Rule Area"));
		east.setBackground(Color.LIGHT_GRAY);
		//east.add(BorderLayout.EAST, runRule);
		
		north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north.add(transpose);
		north.add(openFile);
		north.add(saveFile);
		north.add(saveAsFile);
		north.add(runRule);
		north.add(new JLabel("Delimiter "));
		north.add(delimiter);
		center = new JPanel(new BorderLayout());

		south = new JPanel(new FlowLayout(FlowLayout.LEFT));
		south.setBackground(Color.DARK_GRAY);
		south.add(totalRows);
		south.add(rowDisplay);
		south.add(rowNumber);
		south.add(columnNameLbl);
		south.add(columnName);
		south.add(goToCell);
		JLabel info = new JLabel("\t\t\t\t\tDeveloped by Ajeetkumar Vishwakarma[CT2105]");
		info.setForeground(Color.WHITE);
		south.add(info);
	}
	
	void addPanels() {
		
		Container pane = getContentPane();
		
		pane.add(BorderLayout.NORTH, north);
		pane.add(BorderLayout.CENTER, center);
		pane.add(BorderLayout.SOUTH, south);
		//pane.add(BorderLayout.EAST,east);
		
	}
}
