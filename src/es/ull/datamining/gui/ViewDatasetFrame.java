package es.ull.datamining.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import es.ull.datamining.core.Dataset;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class ViewDatasetFrame extends JFrame implements IView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dataset model;
	
	public ViewDatasetFrame(Dataset model) {
		setSize(new Dimension(450, 300));
		this.model = model;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		
		String[] headers = model.getHeaders();
//		String[] headers = new String[model.getHeaders().length];
		
//		for (int i = 0; i < headers.length; i++) {
//			if (model.getAttribute(i).isNumeric())
//				headers[i]+="<html><center>"+model.getHeaders()+"<br>"+model.getAttribute(i).NUMERIC;
//			else
//				headers[i]+="<html><center>"+model.getHeaders()+"<br>"+model.getAttribute(i).NOMINAL;
//		}
		
		Object[][] data = new Object[model.nInstances()][model.nAttributes()];
		for (int i = 0; i < model.nInstances(); i++) {
			for (int j = 0; j < model.nAttributes(); j++) {
				data[i][j] = model.getInstance(i).getValue(j).toString();
			}
		}
		
		JTable table = new JTable(new DefaultTableModel(data,headers));
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null, null, ""},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Value.class, Double.class, Double.class, Double.class, Double.class, Double.class, String.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
	}
	public Dataset getModel() {
		return model;
	}
	public void setModel(Dataset dataset) {
		this.model = dataset;
	}
}
