package es.ull.datamining.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import java.awt.Toolkit;

public class ExperimenterFrame extends JFrame {

	private ExperimenterController controller;
	JConfigurationPanel configurationPane;
	JTextPane resultsTextArea;

	public ExperimenterFrame(final ExperimenterController controller) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExperimenterFrame.class.getResource("images/icon64x64.png")));
		setSize(new Dimension(362, 640));
		setResizable(false);
		setTitle("Experimenter");
		this.controller = controller;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		
		configurationPane = new JConfigurationPanel();
		tabbedPane.addTab("Configuration", null, configurationPane, null);
		
		configurationPane.cbxClassifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setClassifier((String)configurationPane.cbxClassifier.getSelectedItem());
				configurationPane.btnRun.setEnabled(true);
			}
		});
		
		configurationPane.newSetPane.loadDatasetPane.btnOpenDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadDataset();
				configurationPane.newSetPane.loadDatasetPane.txtDatasetPath.setText(controller.getPathDataset());
				if (controller.isDatasetReady())
					configurationPane.newSetPane.loadDatasetPane.btnViewDataset.setEnabled(true);
			}
		});
		
		configurationPane.newSetPane.loadDatasetPane.btnViewDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.viewDataset();
			}
		});
		
		configurationPane.newSetPane.btnGenerate
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (configurationPane.newSetPane.rdbtnCrossValidation
								.isSelected()) {

							int folds = Integer
									.parseInt(configurationPane.newSetPane.txtFolds
											.getText());

							if (configurationPane.newSetPane.chkSeed
									.isSelected()) {
								long seed = Long
										.parseLong(configurationPane.newSetPane.txtSeed
												.getText());
								controller.generateCrossValidation(folds,
										new Random(seed));
							} else {
								controller.generateCrossValidation(folds);
							}
							JOptionPane.showMessageDialog(
									configurationPane.newSetPane,
									"Cross Validatiom was generate");
						} else if (configurationPane.newSetPane.rdbtnSplit.isSelected()) {
							double percentage = 1d - (Double.parseDouble(configurationPane.newSetPane.spnTrainPercent
									.getValue().toString()) / 100d);
							if(configurationPane.newSetPane.rdbtnRandom.isSelected()){
								if (configurationPane.newSetPane.chkSeed.isSelected()){
									long seed = Long
											.parseLong(configurationPane.newSetPane.txtSeed
													.getText());
									controller.generateTestTrainSet(percentage, new Random(seed));
								}else
									controller.generateTestTrainSet(percentage);
							}else if(configurationPane.newSetPane.rdbtnOrder.isSelected()){
								controller.generateTestTrainSet(percentage, false);
							}
							if (JOptionPane.showConfirmDialog(
									configurationPane.newSetPane,
									"Train/Test Set was generated\n"
											+ "Do you save them?", "Message",
									JOptionPane.YES_NO_OPTION) == 0) {
								controller.loadTrainTestSet();
								controller.saveTrainSet();
								controller.saveTestSet();
							}else{
								controller.loadTrainTestSet();
							}
						}
					}
				});
		
		configurationPane.loadSetPane.btnLoadTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadTrainSet();
				configurationPane.loadSetPane.txtTrain.setText(controller.getPathTrainSet());
			}
		});
		
		configurationPane.loadSetPane.btnViewTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.viewTrainSet();
			}
		});
		
		configurationPane.loadSetPane.btnLoadTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadTestSet();
				configurationPane.loadSetPane.txtTest.setText(controller.getPathTestSet());
			}
		});
		
		configurationPane.loadSetPane.btnViewTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.viewTestSet();
			}
		});
		
		configurationPane.btnRun.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				controller.runExperimenter();
				
				configurationPane.btnStop.setEnabled(true);
				
			}
		});
		
		JPanel resultPane = new JResultPanel();
		tabbedPane.addTab("Result", null, resultPane, null);
		resultPane.setLayout(null);
		
		JScrollPane scrollPaneResult = new JScrollPane();
		scrollPaneResult.setBounds(10, 11, 332, 560);
		resultPane.add(scrollPaneResult);
		
		resultsTextArea = new JTextPane();
		//resultsTextArea.setContentType("text/html");
		scrollPaneResult.setViewportView(resultsTextArea);
		resultsTextArea.setEditable(false);
	}
}
