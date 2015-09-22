package es.ull.datamining.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import es.ull.datamining.classifiers.ChebyshevDistance;
import es.ull.datamining.classifiers.DistanceMetric;
import es.ull.datamining.classifiers.EuclideanDistance;
import es.ull.datamining.classifiers.ManhattanDistance;
import javax.swing.UIManager;
import java.awt.Color;

public class JKnnOptionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtKnn;
	private JTextField txtThreshold;
	private JLabel lblPercentRule;
	private JSlider sliderThresholdMajority;
	private JComboBox<DistanceMetric> cbxMetric;
	private JComboBox<String> cbxWeight;
	private JComboBox<String> cbxRule;
	
	public String getRule(){
		return (String)cbxRule.getSelectedItem();
	}
	
	public String getWeight(){
		return (String)cbxWeight.getSelectedItem();
	}
	
	public DistanceMetric getMetric(){
		return (DistanceMetric)cbxMetric.getSelectedItem();
	}
	
	public int getKnn(){
		return Integer.parseInt(txtKnn.getText());
	}
	
	public int getThreshold() {
		return Integer.parseInt(txtThreshold.getText());
	}
	
	public JKnnOptionPanel() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblKNearestNeighbor = new JLabel("K Nearest Neighbor:");

		JLabel lblDecisionRule = new JLabel("Decision Rule:");

		JLabel lblDistanceMetric = new JLabel("Distance Metric:");

		JLabel lblDistanceWeight = new JLabel("Distance Weight:");

		txtKnn = new JTextField();
		txtKnn.setText("1");
		txtKnn.setHorizontalAlignment(SwingConstants.RIGHT);
		txtKnn.setColumns(10);

		cbxMetric = new JComboBox<DistanceMetric>();
		DefaultComboBoxModel<DistanceMetric> cbxMetricModel = new DefaultComboBoxModel<DistanceMetric>(
				new DistanceMetric[] { new EuclideanDistance(),
						new ManhattanDistance(), new ChebyshevDistance() });
		cbxMetric.setModel(cbxMetricModel);
		cbxMetric.setSelectedIndex(0);

		cbxWeight = new JComboBox<String>();
		DefaultComboBoxModel<String> cbxWeightModel = new DefaultComboBoxModel<String>(
				new String[] { "Same Weight", "Nearest Weight", "Fixed Vote Weight" });
		cbxWeight.setModel(cbxWeightModel);
		cbxWeight.setSelectedIndex(0);

		cbxRule = new JComboBox<String>();
		DefaultComboBoxModel<String> cbxRuleModel = new DefaultComboBoxModel<String>(
				new String[] { "Simple Majority", "Threshold Majority" });
		cbxRule.setModel(cbxRuleModel);
		cbxRule.setSelectedIndex(0);

		txtThreshold = new JTextField();
		txtThreshold.setText("50");
		txtThreshold.setColumns(3);
		txtThreshold.setVisible(false);

		lblPercentRule = new JLabel("%");
		lblPercentRule.setVisible(false);

		sliderThresholdMajority = new JSlider();
		sliderThresholdMajority.setVisible(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKNearestNeighbor)
						.addComponent(lblDistanceMetric, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDistanceWeight, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDecisionRule, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxMetric, 0, 281, Short.MAX_VALUE)
						.addComponent(txtKnn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxWeight, 0, 290, Short.MAX_VALUE)
						.addComponent(cbxRule, 0, 281, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPercentRule, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sliderThresholdMajority, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKNearestNeighbor)
						.addComponent(txtKnn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDistanceMetric)
						.addComponent(cbxMetric, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDistanceWeight)
						.addComponent(cbxWeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDecisionRule)
						.addComponent(cbxRule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPercentRule, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(sliderThresholdMajority, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(69))
		);
		setLayout(groupLayout);
		
		cbxRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxRule.getSelectedItem().equals("Threshold Majority")) {
					txtThreshold.setVisible(true);
					lblPercentRule.setVisible(true);
					sliderThresholdMajority.setVisible(true);
				} else if (cbxRule.getSelectedItem().equals("Simple Majority")) {
					txtThreshold.setVisible(false);
					lblPercentRule.setVisible(false);
					sliderThresholdMajority.setVisible(false);
					sliderThresholdMajority.setValue(50);
				}
			}
		});
		sliderThresholdMajority.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String typed = txtThreshold.getText();
				sliderThresholdMajority.setValue(0);
				if (!typed.matches("\\d+") || typed.length() > 3) {
					return;
				}
				int value = Integer.parseInt(typed);
				sliderThresholdMajority.setValue(value);
			}
		});

		sliderThresholdMajority.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtThreshold.setText(String.valueOf(sliderThresholdMajority
						.getValue()));
			}
		});
	}

	public JComboBox<String> getCbxWeight() {
		return cbxWeight;
	}

	public void setCbxWeight(JComboBox<String> cbxWeight) {
		this.cbxWeight = cbxWeight;
	}
}
