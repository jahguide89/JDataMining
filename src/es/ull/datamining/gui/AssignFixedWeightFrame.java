package es.ull.datamining.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;

import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AssignFixedWeightFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] weights;
	private JTable tblWeights;

	public int[] getWeights() {
		return weights;
	}
	public AssignFixedWeightFrame(final int k) {
		setResizable(false);
		setTitle("Assign Fixed Vote Weight");
		setType(Type.UTILITY);
		getContentPane().setLayout(null);

		JPanel buttonsPane = new JPanel();
		buttonsPane.setBounds(10, 224, 250, 23);
		getContentPane().add(buttonsPane);
		buttonsPane.setLayout(new GridLayout(1, 0, 10, 0));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < k; i++) {
					tblWeights.setValueAt(new Integer(1), i, 0);
				}
				
			}
		});
		buttonsPane.add(btnReset);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonsPane.add(btnCancel);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				weights = new int[k];
				for (int i = 0; i < weights.length; i++) {
					weights[i] = (Integer) tblWeights.getValueAt(i, 0);
				}
				dispose();
			}
		});
		buttonsPane.add(btnApply);

		tblWeights = new JTable();
		DefaultTableModel model = (DefaultTableModel) tblWeights.getModel();

		Integer[] colWeights = new Integer[k];
		for (int i = 0; i < colWeights.length; i++) {
			colWeights[i] = new Integer(1);
		}

		Integer[] colK = new Integer[k];
		for (int i = 0; i < colWeights.length; i++) {
			colK[i] = i + 1;
		}

		model.addColumn("Weights", colWeights);

		tblWeights.getColumnModel().getColumn(0)
				.setCellEditor(new SpinnerEditor());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 11, 250, 202);

		getContentPane().add(scrollPane);

		scrollPane.setViewportView(tblWeights);
		final Integer[] tmp = colK;
		JList<Integer> rowHeader = new JList<Integer>();
		rowHeader.setFixedCellWidth(30);
		rowHeader.setFixedCellHeight(tblWeights.getRowHeight());
		rowHeader.setModel(new AbstractListModel<Integer>() {
			private static final long serialVersionUID = 1L;
			Integer[] values = tmp;

			public int getSize() {
				return values.length;
			}

			public Integer getElementAt(int index) {
				return values[index];
			}
		});
		rowHeader.setCellRenderer(new RowHeaderRenderer(tblWeights));
		scrollPane.setRowHeaderView(rowHeader);

		setSize(280, 280);
		setVisible(true);
	}

	class RowHeaderRenderer extends JLabel implements ListCellRenderer {

		RowHeaderRenderer(JTable table) {
			JTableHeader header = table.getTableHeader();
			setOpaque(true);
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			setHorizontalAlignment(CENTER);
			setForeground(header.getForeground());
			setBackground(header.getBackground());
			setFont(header.getFont());
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	public static class SpinnerEditor extends DefaultCellEditor {
		JSpinner spinner;
		JSpinner.DefaultEditor editor;
		JTextField textField;
		boolean valueSet;

		// Initializes the spinner.
		public SpinnerEditor() {
			super(new JTextField());
			spinner = new JSpinner();
			editor = ((JSpinner.DefaultEditor) spinner.getEditor());
			textField = editor.getTextField();
			textField.setHorizontalAlignment(JTextField.RIGHT);
			textField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent fe) {
//					System.err.println("Got focus");
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (valueSet) {
								textField.setCaretPosition(1);
							}
						}
					});
				}

				public void focusLost(FocusEvent fe) {
				}
			});
			textField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					stopCellEditing();
				}
			});
		}

		// Prepares the spinner component and returns it.
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			if (!valueSet) {
				spinner.setValue(value);
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textField.requestFocus();
				}
			});
			return spinner;
		}

		public boolean isCellEditable(EventObject eo) {
//			System.err.println("isCellEditable");
			if (eo instanceof KeyEvent) {
				KeyEvent ke = (KeyEvent) eo;
//				System.err.println("key event: " + ke.getKeyChar());
				textField.setText(String.valueOf(ke.getKeyChar()));
				valueSet = true;
			} else {
				valueSet = false;
			}
			return true;
		}

		// Returns the spinners current value.
		public Object getCellEditorValue() {
			return spinner.getValue();
		}

		public boolean stopCellEditing() {
//			System.err.println("Stopping edit");
			try {
				editor.commitEdit();
				spinner.commitEdit();
			} catch (java.text.ParseException e) {
				JOptionPane.showMessageDialog(null,
						"Invalid value, discarding.");
			}
			return super.stopCellEditing();
		}
	}

	

}
