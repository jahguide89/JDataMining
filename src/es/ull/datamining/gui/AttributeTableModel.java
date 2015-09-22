package es.ull.datamining.gui;

import javax.swing.table.AbstractTableModel;

import es.ull.datamining.core.Dataset;

public class AttributeTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Dataset dataset;

	protected Class[] columnClasses = new Class[] { Integer.class,
			String.class, String.class, Double.class, Boolean.class };

	protected String[] columnNames = new String[] { "Id", "Attributes", "Type",
			"Weight", "¿Class?" };
	protected boolean[] rowSelected;
	protected double[] weightAtt;

	public AttributeTableModel(Dataset dataset) {
		this.dataset = dataset;
		rowSelected = new boolean[dataset.nAttributes()];
		for (int i = 0; i < rowSelected.length; i++) {
			rowSelected[i] = dataset.getAttribute(i).isClass();
		}
		weightAtt = new double[dataset.nAttributes()];
		for (int i = 0; i < weightAtt.length; i++) {
			weightAtt[i] = dataset.getAttribute(i).getWeight();
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return dataset.nAttributes();
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
			return row;
		case 1:
			return dataset.getAttribute(row).getName();
		case 2:
			return dataset.getAttribute(row).getType();
		case 3:
			return weightAtt[row];
		case 4:
			return rowSelected[row];
		default:
			return null;
		}

	}

	public void setValueAt(Object value, int row, int col) {
		if (isCellEditable(row, col)) {
			if (value instanceof Boolean)
				rowSelected[row] = (Boolean) value;
			else if (value instanceof Double)
				weightAtt[row] = (Double) value;
		}
		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 4 || columnIndex == 3);
	}

}
