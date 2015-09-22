package es.ull.datamining.gui;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class JLoadDatasetPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnOpenDataset;
	JTextField txtDatasetPath;
	JButton btnViewDataset;
	JButton btnSaveDataset;

	public JLoadDatasetPane() {
		setBorder(new TitledBorder(null, "Dataset", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel buttonsPane = new JPanel();
		add(buttonsPane);
		buttonsPane.setLayout(new GridLayout(0, 3, 0, 0));

		btnOpenDataset = new JButton("Open");
		buttonsPane.add(btnOpenDataset);

		btnViewDataset = new JButton("View");
		btnViewDataset.setEnabled(false);
		buttonsPane.add(btnViewDataset);

		btnSaveDataset = new JButton("Save");
		btnSaveDataset.setEnabled(false);
		buttonsPane.add(btnSaveDataset);

		JPanel pathPane = new JPanel();
		add(pathPane);

		JLabel lblPath = new JLabel("Path:");

		txtDatasetPath = new JTextField();

		txtDatasetPath.setHorizontalAlignment(SwingConstants.LEFT);
		txtDatasetPath.setColumns(30);
		GroupLayout gl_pathPane = new GroupLayout(pathPane);
		gl_pathPane.setHorizontalGroup(
				gl_pathPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pathPane.createSequentialGroup()
						.addGap(5)
						.addComponent(lblPath)
						.addGap(5)
						.addComponent(txtDatasetPath, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
				);
		gl_pathPane.setVerticalGroup(
				gl_pathPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pathPane.createSequentialGroup()
						.addGroup(gl_pathPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pathPane.createSequentialGroup()
										.addGap(8)
										.addComponent(lblPath))
										.addGroup(gl_pathPane.createSequentialGroup()
												.addGap(5)
												.addComponent(txtDatasetPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addContainerGap(113, Short.MAX_VALUE))
				);
		pathPane.setLayout(gl_pathPane);

		setVisible(true);
	}
}
