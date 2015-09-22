package es.ull.datamining.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class JNewSetPane extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLoadDatasetPane loadDatasetPane;
	JButton btnGenerate;
	JRadioButton rdbtnCrossValidation;
	JRadioButton rdbtnSplit;
	JTextField txtSeed;
	JTextField txtFolds;
	JCheckBox chkSeed;
	JSpinner spnTrainPercent;
	JRadioButton rdbtnRandom;
	JRadioButton rdbtnOrder;
	
	private final ButtonGroup btnGroupExperiment = new ButtonGroup();
	private final ButtonGroup btnGroupTrainTest = new ButtonGroup();
	private JLabel lblFolds;
	private JLabel lblTrainPercent;
	private JPanel foldsPane;
	private JPanel trainPercentPane;
	private JPanel randomPane;
	private JPanel seedPane;
	
	public JNewSetPane() {
		setBorder(new TitledBorder(null, "New Train/Test Set", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		loadDatasetPane = new JLoadDatasetPane();
		loadDatasetPane.setBounds(10, 22, 311, 79);

		rdbtnCrossValidation = new JRadioButton("Cross Validation");
		rdbtnCrossValidation.setBounds(10, 108, 311, 27);
		rdbtnCrossValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFolds.setEnabled(true);
				lblFolds.setEnabled(true);

				lblTrainPercent.setEnabled(false);
				spnTrainPercent.setEnabled(false);
				rdbtnRandom.setEnabled(false);
				rdbtnOrder.setEnabled(false);
				
				chkSeed.setEnabled(true);
			}
		});
		rdbtnCrossValidation.setSelected(true);

		rdbtnSplit = new JRadioButton("Train/Test percentage split");
		rdbtnSplit.setBounds(10, 179, 311, 27);
		rdbtnSplit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFolds.setEnabled(false);
				lblFolds.setEnabled(false);

				lblTrainPercent.setEnabled(true);
				spnTrainPercent.setEnabled(true);
				rdbtnRandom.setEnabled(true);
				rdbtnOrder.setEnabled(true);
				
				if (rdbtnOrder.isSelected())
					chkSeed.setEnabled(false);
			}
		});
		btnGroupExperiment.add(rdbtnCrossValidation);
		btnGroupExperiment.add(rdbtnSplit);
		
		foldsPane = new JPanel();
		foldsPane.setBounds(20, 142, 301, 30);
		foldsPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblFolds = new JLabel("Folds:");
		foldsPane.add(lblFolds);

		txtFolds = new JTextField();
		txtFolds.setText("10");
		foldsPane.add(txtFolds);
		txtFolds.setColumns(10);

		trainPercentPane = new JPanel();
		trainPercentPane.setBounds(20, 213, 301, 30);
		trainPercentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						
		lblTrainPercent = new JLabel("Train percentage:");
		trainPercentPane.add(lblTrainPercent);
		lblTrainPercent.setEnabled(false);

		spnTrainPercent = new JSpinner();
		trainPercentPane.add(spnTrainPercent);
		spnTrainPercent.setEnabled(false);
		spnTrainPercent.setModel(new SpinnerNumberModel(80, 0, 100, 1));

		randomPane = new JPanel();
		randomPane.setBounds(20, 254, 301, 37);
		randomPane.setLayout(new GridLayout(1, 0, 0, 0));

		rdbtnRandom = new JRadioButton("Data randomize");
		randomPane.add(rdbtnRandom);
		rdbtnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkSeed.setEnabled(true);
			}
		});
		rdbtnRandom.setEnabled(false);
		rdbtnRandom.setSelected(true);
		btnGroupTrainTest.add(rdbtnRandom);

		rdbtnOrder = new JRadioButton("Order preserved");
		randomPane.add(rdbtnOrder);
		rdbtnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkSeed.setEnabled(false);
			}
		});
		rdbtnOrder.setEnabled(false);

		btnGroupTrainTest.add(rdbtnOrder);

		seedPane = new JPanel();
		seedPane.setBounds(10, 302, 311, 43);
		seedPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		chkSeed = new JCheckBox("Seed:");
		seedPane.add(chkSeed);

		txtSeed = new JTextField();
		txtSeed.setColumns(10);
		seedPane.add(txtSeed);
		txtSeed.setEnabled(false);
		chkSeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtSeed.setEnabled(chkSeed.isSelected());
			}
		});
		setLayout(null);
		add(loadDatasetPane);
		add(rdbtnCrossValidation);
		add(foldsPane);
		add(trainPercentPane);
		add(rdbtnSplit);
		add(randomPane);
		add(seedPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 356, 311, 2);
		add(separator);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(232, 369, 89, 23);
		add(btnGenerate);
	}
}
