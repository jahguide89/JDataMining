package es.ull.datamining.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JKnnOptionsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JKnnOptionPanel confPane;
	public JKnnOptionsFrame(final ExperimenterController controller) {
		setTitle("K Nearest Neighbor");
		confPane = new JKnnOptionPanel();
		getContentPane().add(confPane);
		
		JPanel btnPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPane.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(btnPane, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPane.add(btnCancel);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.setK(confPane.getKnn());
				controller.setDistanceMetric(confPane.getMetric());
				controller.setDistanceWeightStr(confPane.getWeight());
				controller.setDecisionRuleStr(confPane.getRule());
				if (confPane.getRule().equals("Threshold Majority"))
					controller.setThreshold(confPane.getThreshold());
				dispose();
			}
		});
		btnPane.add(btnApply);
		
		confPane.getCbxWeight().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(confPane.getCbxWeight().getSelectedItem().equals("Fixed Vote Weight")){
					controller.setAttributesWeights(confPane.getKnn());
				}
			}
		});
		
		pack();
	}

}
