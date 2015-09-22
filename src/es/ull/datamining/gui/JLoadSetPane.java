package es.ull.datamining.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class JLoadSetPane extends JPanel {
	JTextField txtTrain;
	JTextField txtTest;
	JButton btnLoadTrain;
	JButton btnViewTrain;
	JButton btnLoadTest;
	JButton btnViewTest;
	public JLoadSetPane() {
		setBorder(new TitledBorder(null, "Load Train/Test Set", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel trainSetPane = new JPanel();
		FlowLayout fl_trainSetPane = (FlowLayout) trainSetPane.getLayout();
		fl_trainSetPane.setAlignment(FlowLayout.LEFT);
		trainSetPane.setBorder(new TitledBorder(null, "Train Set", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(trainSetPane);
		
		JLabel lblPathTrain = new JLabel("Path:");
		trainSetPane.add(lblPathTrain);
		
		txtTrain = new JTextField();
		trainSetPane.add(txtTrain);
		txtTrain.setColumns(25);
		
		btnLoadTrain = new JButton("Load");
		trainSetPane.add(btnLoadTrain);
		
		btnViewTrain = new JButton("View");
		trainSetPane.add(btnViewTrain);
		
		JPanel testSetPane = new JPanel();
		FlowLayout fl_testSetPane = (FlowLayout) testSetPane.getLayout();
		fl_testSetPane.setAlignment(FlowLayout.LEFT);
		testSetPane.setBorder(new TitledBorder(null, "Test Set", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(testSetPane);
		
		JLabel lblPathTest = new JLabel("Path:");
		testSetPane.add(lblPathTest);
		
		txtTest = new JTextField();
		txtTest.setColumns(25);
		testSetPane.add(txtTest);
		
		btnLoadTest = new JButton("Load");
		testSetPane.add(btnLoadTest);
		
		btnViewTest = new JButton("View");
		testSetPane.add(btnViewTest);
	}
}
