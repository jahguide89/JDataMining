package es.ull.datamining.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PredictionSetEditorFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AnalysisController controller;
	private JTextArea txtrPredictionSetEditor;
	private String text;
	private String[] instances;
	
	public PredictionSetEditorFrame(AnalysisController controller) {
		this.controller = controller;
		setText(null);
		showGUI();
	}
	public void updateGUI(String text){
		this.setText(text);
		showGUI();
	}
	public void showGUI(){
		setTitle("Prediction Set Editor");
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(335, 228, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setInstances(txtrPredictionSetEditor.getText().split("\\r?\\n"));
				setText(txtrPredictionSetEditor.getText());
				dispose();
				controller.updatePredictionSetEditor();
				
				if (JOptionPane.showConfirmDialog(getContentPane(),
				    "Do you want to save the instances?",
				    "Save instances",
				    JOptionPane.YES_NO_OPTION) == 0)
				    controller.savePredictionSet();
			}
		});
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(236, 228, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancel);
		
		txtrPredictionSetEditor = new JTextArea(text);
		txtrPredictionSetEditor.setBounds(10, 11, 414, 206);
		getContentPane().add(txtrPredictionSetEditor);
		setSize(450, 300);
		setVisible(true);
	}

	public String[] getInstances() {
		return instances;
	}
	public void setInstances(String[] instances) {
		this.instances = instances;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
