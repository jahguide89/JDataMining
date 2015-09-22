package es.ull.datamining.gui;

import javax.swing.table.AbstractTableModel;

import es.ull.datamining.core.AttributeNumeric;

public class NumericTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AttributeNumeric attribute;
	
	protected String[] columnNames;
	
	protected String[] statNames = new String[] { "Min", "Max","Avg", "StdDev"};
	
	protected Class[] columnClasses = new Class[] { String.class, Double.class};
	
	public NumericTableModel(AttributeNumeric attribute) {
		this.attribute = attribute;
		columnNames = new String[] { attribute.getName(), "Value"};
	}

	public int getRowCount() {
		return 4;
	}

	public int getColumnCount() {
		return 2;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int col) {
		return columnClasses[col];
	}

	public Object getValueAt(int row, int col) {
		if (col == 0){
			return statNames[row];
		}
		else{
			switch (row) {
			case 0: return attribute.getMin();
			case 1: return attribute.getMax();
			case 2: return attribute.getAvg();
			case 3: return attribute.getStdDev();
			default: return null;
			}
		}

	}

}
