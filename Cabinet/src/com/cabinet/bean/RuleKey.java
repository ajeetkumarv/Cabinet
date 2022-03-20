package com.cabinet.bean;

public class RuleKey {

	private int rowNumber;
	private String columnName;
	private int columnIndex;
	
	public RuleKey( int rowNumber, String columnName, int columnIndex){
		this.rowNumber=rowNumber;
		this.columnName=columnName;
		this.columnIndex=columnIndex;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public String getColumnName() {
		return columnName;
	}

	public int getColumnIndex(){
		return columnIndex;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + rowNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleKey other = (RuleKey) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (rowNumber != other.rowNumber)
			return false;
		return true;
	}

	
	
}
