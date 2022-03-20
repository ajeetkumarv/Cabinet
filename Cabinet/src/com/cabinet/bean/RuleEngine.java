package com.cabinet.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.TableModel;

import com.cabinet.util.DataModel;

public class RuleEngine {

	private List<String> rules;
	private TableModel dataModel;
	private Map<RuleKey,String> ruleMap;
	
	public RuleEngine(List<String> rules, TableModel dataModel){
		this.rules=rules;
		this.dataModel=dataModel;
	}
	
	public void processRules(){
		if(!rules.isEmpty()){
			ruleMap = new HashMap<RuleKey, String>();
			
			String[] columnNames = null;
			String [] newValues;
			
			int rowNumber=1;
			for(String rule: rules){
				
				String [] ruleParts = rule.split("\t");
				
				rowNumber = Integer.valueOf(ruleParts[0]);
				columnNames = ruleParts[1].split(" and |&|,");
				
				newValues = ruleParts[2].split(" and |&|,");
				
				if(newValues.length==1 && columnNames.length>1){
					String valueToRepeat = newValues[0];
					newValues = new String[columnNames.length];
					for(int i=0;i<columnNames.length;i++){
						newValues[i]=valueToRepeat;
					}
				}
				
				int totalColumns = columnNames.length;
				DataModel d = (DataModel)dataModel;
				RuleKey tempKey;
				for(int columnIndex=0;columnIndex<totalColumns;columnIndex++) {
					String newValue = newValues[columnIndex].trim();
					if(newValue.equalsIgnoreCase("null")){
						newValue="";
					}
					
					tempKey = new RuleKey(rowNumber,columnNames[columnIndex].trim(),d.getColumnIndex(columnNames[columnIndex].trim()));
					ruleMap.put(tempKey, newValue);
				}
			}
		}
	}
	
	public void runRules(){
		
		if(ruleMap==null){
			return ;
		}
		
		for(RuleKey r: ruleMap.keySet()){
			dataModel.setValueAt(ruleMap.get(r), r.getRowNumber()-1, r.getColumnIndex());
		}
	}
}
