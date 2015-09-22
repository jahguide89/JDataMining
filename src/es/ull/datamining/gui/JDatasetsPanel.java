package es.ull.datamining.gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.GridLayout;

public class JDatasetsPanel extends JPanel {
	public JDatasetsPanel() {
		setBorder(new TitledBorder(null, "Datasets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel controlPane = new JPanel();
		add(controlPane, BorderLayout.SOUTH);
		controlPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnAdd = new JButton("Add");
		controlPane.add(btnAdd);
		
		JButton btnDel = new JButton("Del");
		controlPane.add(btnDel);
		
		JButton btnEdit = new JButton("Edit");
		controlPane.add(btnEdit);
		
		JList listDatasets = new JList();
		add(listDatasets, BorderLayout.CENTER);
	}

}
