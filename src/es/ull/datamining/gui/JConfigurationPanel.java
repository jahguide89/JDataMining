package es.ull.datamining.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class JConfigurationPanel extends JPanel {

	private final ButtonGroup btnGroupMode = new ButtonGroup();
	JNewSetPane newSetPane;
	JLoadSetPane loadSetPane;
	JComboBox<String> cbxClassifier;
	JButton btnRun;
	JButton btnStop;

	public JConfigurationPanel() {
		setLayout(null);

		JPanel paneClassifier = new JPanel();
		paneClassifier.setBorder(new TitledBorder(null, "Select Classifier",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paneClassifier.setBounds(10, 472, 332, 50);
		add(paneClassifier);

		cbxClassifier = new JComboBox<String>();
		DefaultComboBoxModel<String> cbxClassifierModel = new DefaultComboBoxModel<String>(
				new String[] { "<Select>", "K - Nearest Neighbor",
						"Naive Bayes" });
		paneClassifier.setLayout(new BoxLayout(paneClassifier, BoxLayout.X_AXIS));
		cbxClassifier.setModel(cbxClassifierModel);
		cbxClassifier.setSelectedIndex(0);
		paneClassifier.add(cbxClassifier);
				
		btnRun = new JButton("");
		btnRun.setIcon(new ImageIcon(JConfigurationPanel.class
				.getResource("images/play128.png")));
		btnRun.setToolTipText("Run");
		btnRun.setBounds(302, 533, 40, 40);
		btnRun.setEnabled(false);
		add(btnRun);
		
		btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(JConfigurationPanel.class
				.getResource("images/stop4.png")));
		btnStop.setToolTipText("Stop");
		btnStop.setBounds(252, 533, 40, 40);
		btnStop.setEnabled(false);
		add(btnStop);
		
		JPanel modePane = new JPanel();
		modePane.setBounds(10, 11, 332, 33);
		add(modePane);
		modePane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnNewTraintestSet = new JRadioButton("New Train/Test Set");
		rdbtnNewTraintestSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSetPane.setVisible(false);
				newSetPane.setVisible(true);
			}
		});
		rdbtnNewTraintestSet.setSelected(true);
		btnGroupMode.add(rdbtnNewTraintestSet);
		modePane.add(rdbtnNewTraintestSet);
		
		JRadioButton rdbtnLoadTraintestSet = new JRadioButton("Load Train/Test Set");
		rdbtnLoadTraintestSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSetPane.setVisible(true);
				newSetPane.setVisible(false);
			}
		});
		btnGroupMode.add(rdbtnLoadTraintestSet);
		modePane.add(rdbtnLoadTraintestSet);
		
		newSetPane =  new JNewSetPane();
		newSetPane.setBounds(10, 55, 332, 406);
		add(newSetPane);
		
		loadSetPane = new JLoadSetPane();
		loadSetPane.setBounds(10, 55, 330, 406);
		loadSetPane.setVisible(false);
		add(loadSetPane);
		
	}
}
