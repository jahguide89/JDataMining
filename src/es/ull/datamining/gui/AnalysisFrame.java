package es.ull.datamining.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import es.ull.datamining.core.Attribute;
import es.ull.datamining.core.AttributeNominal;
import es.ull.datamining.core.AttributeNumeric;
import es.ull.datamining.core.Dataset;
import es.ull.datamining.filters.AddAttribute;
import es.ull.datamining.filters.AddInstance;
import es.ull.datamining.filters.Filter;
import es.ull.datamining.filters.Normalize;
import es.ull.datamining.filters.RemoveAttribute;
import es.ull.datamining.filters.RemoveInstance;
import es.ull.datamining.filters.Standardized;
import es.ull.datamining.util.Util;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class AnalysisFrame extends JFrame implements IView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem mntmSave;
	
	JComboBox<Filter> cbxFilters;
	JComboBox<String> cbxClassifier;
	JButton btnOptions;
	JButton btnApplyFilter;
	JTable tblAttributes;
	JScrollPane scrollPanelAttribute;
	JTextPane resultsTextArea;
	JTextField txtPredictionSet;
	JKnnOptionPanel confPane;
	JLoadDatasetPane panelDataset;
	
	private AnalysisController controller;
	private Dataset model;
	
	
	private JScrollPane scrollPane;
	private JTable tblInfoAtt;
	private JTabbedPane tabbedPane;
	private JPanel explorePane;
	private JPanel classifierPane;
	private JPanel selectClassPane;
	private JButton btnOpenFile;
	private JRadioButton rdbtnLoadTestSet;
	private JRadioButton rdbtnManually;
	private JButton btnCreate;
	private JButton btnEdit;
	private JButton btnDelete;
	private JPanel resultsPane;
	
	private int rowMousePressed;
	private int colMoussePressed;
	
	public AnalysisFrame(AnalysisController controller) {
		setTitle("Analysis");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnalysisFrame.class.getResource("/es/ull/datamining/gui/images/icon64x64.png")));
		
		this.controller = controller;
		createGUI();
	}
	
	public void createGUI(){
		
		setSize(new Dimension(528, 685));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 512, 630);
		getContentPane().add(tabbedPane);
		
		explorePane = new JPanel();
		tabbedPane.addTab("Explore", null, explorePane, null);
		explorePane.setLayout(null);
		
		JPanel panelFilters = new JPanel();
		panelFilters.setBounds(10, 102, 484, 80);
		explorePane.add(panelFilters);
		panelFilters.setBorder(new TitledBorder(null, "Filters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFilters.setLayout(null);
		
		cbxFilters = new JComboBox<Filter>();
		// comboBox.setModel(new DefaultComboBoxModel(new String[]
		// {"Add Attribute", "Remove Attribute", "Add Instanciable",
		// "Remove Instanciable", "Standardized", "Normalize"}));

		cbxFilters.addItem(new Normalize());
		cbxFilters.addItem(new Standardized());
		cbxFilters.setBounds(61, 11, 195, 20);
		cbxFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setFilter((Filter) cbxFilters.getSelectedItem());

			}
		});
		panelFilters.add(cbxFilters);

		btnApplyFilter = new JButton("Apply");
		btnApplyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.applyFilter();
				JOptionPane.showMessageDialog(getContentPane(),
						"Filtro aplicado!");
			}
		});
		btnApplyFilter.setBounds(375, 10, 99, 23);
		panelFilters.add(btnApplyFilter);

		btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxFilters.getSelectedIndex() == 0)
					controller.showOptionFilterFrame();
			}
		});
		btnOptions.setBounds(266, 10, 99, 23);
		panelFilters.add(btnOptions);
		
		panelDataset = new JLoadDatasetPane();
		panelDataset.setBounds(10, 11, 484, 80);
		
		
		panelDataset.btnOpenDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDataset.txtDatasetPath.setText(Util.getOpenFileName());
				controller.loadDataset();
				setModel(controller.getDataset());
				tblAttributes.setModel(new AttributeTableModel(getModel()));
				if (controller.isDataReady()){
					panelDataset.btnViewDataset.setEnabled(true);
					panelDataset.btnSaveDataset.setEnabled(true);
				}
			}
		});
		panelDataset.btnViewDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showDataset();
			}
		});
		panelDataset.btnSaveDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDataset();
			}
		});
		
		explorePane.add(panelDataset);
		
		
		
		JPanel attributesPanel = new JPanel();
		attributesPanel.setBounds(10, 193, 484, 206);
		explorePane.add(attributesPanel);
		attributesPanel.setBorder(new TitledBorder(null, "Attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		attributesPanel.setLayout(null);
		
		tblAttributes = new JTable();
		
		
		scrollPane = new JScrollPane(tblAttributes);
		scrollPane.setBounds(16, 21, 452, 168);
		attributesPanel.add(scrollPane);
		
		JPanel infoAttPanel = new JPanel();
		infoAttPanel.setBounds(10, 410, 484, 173);
		explorePane.add(infoAttPanel);
		infoAttPanel.setBorder(new TitledBorder(null, "Info Attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoAttPanel.setLayout(null);
		
		tblInfoAtt = new JTable();
		JScrollPane scrollPaneInfoAtt = new JScrollPane(tblInfoAtt);
		scrollPaneInfoAtt.setBounds(10, 21, 452, 141);
		infoAttPanel.add(scrollPaneInfoAtt);
		
		tblAttributes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rowMousePressed = tblAttributes.rowAtPoint(e.getPoint());
		        colMoussePressed = tblAttributes.columnAtPoint(e.getPoint());
		        Attribute att = controller.getDataset().getAttribute(rowMousePressed);
		        if (colMoussePressed == 4 && !att.isNumeric()){
		        	if (!(Boolean) tblAttributes.getModel().getValueAt(rowMousePressed, colMoussePressed)){
		        		att.setIsClass(true);
		        		tblAttributes.getModel().setValueAt(Boolean.TRUE, rowMousePressed, colMoussePressed);
		        	}
		        	else{
		        		att.setIsClass(false);
		        		tblAttributes.getModel().setValueAt(Boolean.FALSE, rowMousePressed, colMoussePressed);
		        	}
		        }else{
		        	if( tblAttributes.getModel().getValueAt(rowMousePressed, 2) == Attribute.NUMERIC)
		        		tblInfoAtt.setModel(new NumericTableModel((AttributeNumeric)att));
		        	else
		        		tblInfoAtt.setModel(new NominalTableModel((AttributeNominal)att));
		        }
			}
		});
		tblAttributes.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("tableCellEditor")) {
					Attribute att = controller.getDataset().getAttribute(rowMousePressed);
			        if (colMoussePressed == 3){
			        	att.setWeight((Double) tblAttributes.getModel().getValueAt(rowMousePressed, colMoussePressed));
			        }
				}
			}
		});
		
		classifierPane = new JPanel();
		tabbedPane.addTab("Classifier", null, classifierPane, null);
		classifierPane.setLayout(null);
		
		confPane = new JKnnOptionPanel();
		confPane.setBounds(10, 276, 487, 257);
		classifierPane.add(confPane);
				
		JPanel testSetPane = new JPanel();
		testSetPane.setBounds(10, 11, 487, 183);
		classifierPane.add(testSetPane);
		testSetPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Prediction set", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		testSetPane.setLayout(null);
		
		ButtonGroup optionsTestGroup = new ButtonGroup();
		
		rdbtnLoadTestSet = new JRadioButton("Load test set");
		rdbtnLoadTestSet.setSelected(true);
		rdbtnLoadTestSet.setBounds(10, 30, 120, 23);
		testSetPane.add(rdbtnLoadTestSet);
		rdbtnLoadTestSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBtnTestConf();
			}
		});
		optionsTestGroup.add(rdbtnLoadTestSet);
		
		txtPredictionSet = new JTextField();
		txtPredictionSet.setBounds(63, 60, 205, 20);
		testSetPane.add(txtPredictionSet);
		txtPredictionSet.setColumns(10);
		
		btnOpenFile = new JButton("Open file...");
		btnOpenFile.setBounds(278, 59, 100, 23);
		testSetPane.add(btnOpenFile);
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPredictionSet.setText(Util.getOpenFileName());
				if(txtPredictionSet.getText() != null) {
				  controller.loadPredictionSet();
				}
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(278, 121, 89, 23);
		testSetPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.dropPredictionSetEditor();
				JOptionPane.showMessageDialog(null, "Testset dropped.");
			}
		});
		btnDelete.setEnabled(false);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(179, 121, 89, 23);
		testSetPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.editPredictionSetEditor();
			}
		});
		btnEdit.setEnabled(false);
		
		btnCreate = new JButton("Create");
		btnCreate.setEnabled(false);
		btnCreate.setBounds(83, 121, 89, 23);
		testSetPane.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createPredictionSetEditor();
			}
		});
		
		rdbtnManually = new JRadioButton("Create manually");
		rdbtnManually.setBounds(10, 91, 120, 23);
		testSetPane.add(rdbtnManually);
		rdbtnManually.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBtnTestConf();
			}
		});
		optionsTestGroup.add(rdbtnManually);
		
		JButton btnRun = new JButton("");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.runAlgorithm();
			}
		});
		btnRun.setIcon(new ImageIcon(AnalysisFrame.class.getResource("images/play128.png")));
		btnRun.setToolTipText("Run");
		btnRun.setBounds(457, 544, 40, 40);
		classifierPane.add(btnRun);
		
		JButton btnStop = new JButton("");
		btnStop.setToolTipText("Stop");
		btnStop.setIcon(new ImageIcon(AnalysisFrame.class.getResource("images/stop4.png")));
		btnStop.setBounds(407, 544, 40, 40);
		classifierPane.add(btnStop);
		
		selectClassPane = new JPanel();
		selectClassPane.setBounds(10, 205, 487, 60);
		classifierPane.add(selectClassPane);
		selectClassPane.setBorder(new TitledBorder(null, "Select Classifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		selectClassPane.setLayout(null);
		
		JButton btnApplyClassifier = new JButton("Apply");
		btnApplyClassifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxClassifier.getSelectedIndex() == 1){
				controller.applyClassifier(
						cbxClassifier.getSelectedIndex(),
						confPane.getKnn(), 
						confPane.getMetric(),
						confPane.getWeight(),
						confPane.getRule());
				}else{
					controller.applyClassifier(
							cbxClassifier.getSelectedIndex(), 0, null, null,
							null);
				}
			}
		});
		btnApplyClassifier.setBounds(388, 20, 89, 23);
		selectClassPane.add(btnApplyClassifier);
		
		cbxClassifier = new JComboBox<String>();
		cbxClassifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxClassifier.getSelectedIndex() == 1)
					confPane.setVisible(true);
				else
					confPane.setVisible(false);
			}
		});
		DefaultComboBoxModel<String> cbxClassifierModel = new DefaultComboBoxModel<String>(
				new String[] { "<Select>", "K - Nearest Neighbor",
						"Naive Bayes" });
		cbxClassifier.setModel(cbxClassifierModel);
		
		cbxClassifier.setSelectedIndex(0);
		cbxClassifier.setBounds(63, 21, 315, 20);
		selectClassPane.add(cbxClassifier);
		
		resultsPane = new JPanel();
		tabbedPane.addTab("Results", null, resultsPane, null);
		resultsPane.setLayout(null);
		
		JScrollPane scrollPaneResult = new JScrollPane();
		scrollPaneResult.setBounds(10, 11, 487, 580);
		resultsPane.add(scrollPaneResult);
		
		resultsTextArea = new JTextPane();
		resultsTextArea.setContentType("text/html");
		scrollPaneResult.setViewportView(resultsTextArea);
		resultsTextArea.setEditable(false);
		
		confPane.getCbxWeight().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (confPane.getCbxWeight().getSelectedIndex() == 2)
					controller.showVoteWeightFrame(confPane.getKnn());
			}
		});
		
	}

	protected void updateBtnTestConf() {
		btnCreate.setEnabled(rdbtnManually.isSelected());
		btnEdit.setEnabled(rdbtnManually.isSelected());
		btnDelete.setEnabled(rdbtnManually.isSelected());
		txtPredictionSet.setEnabled(rdbtnLoadTestSet.isSelected());
		btnOpenFile.setEnabled(rdbtnLoadTestSet.isSelected());
	}
	
	public Dataset getModel() {
		return model;
	}

	public void setModel(Dataset model) {
		this.model = model;
	}
}
