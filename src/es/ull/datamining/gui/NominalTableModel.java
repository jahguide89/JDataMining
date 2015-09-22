package es.ull.datamining.gui;

import javax.swing.table.AbstractTableModel;

import es.ull.datamining.core.AttributeNominal;

public class NominalTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected AttributeNominal attribute;
	
	protected String[] columnNames = new String[] { "Class", "Freq"};
	
	protected Class[] columnClasses = new Class[] { String.class, Integer.class};

	public NominalTableModel(AttributeNominal attribute) {
		this.attribute = attribute;
	}

	public int getRowCount() {
		return attribute.getClasses().size();
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
		switch (col) {
		case 0:
			return attribute.getClass(row).getValue();
		case 1:
			return attribute.getClass(row).getFrequency();
		default:
			return null;
		}

	}

}
