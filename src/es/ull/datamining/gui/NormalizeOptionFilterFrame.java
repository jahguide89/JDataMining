package es.ull.datamining.gui;

import javax.swing.JFrame;

import es.ull.datamining.filters.Normalize;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class NormalizeOptionFilterFrame extends JFrame implements
		IOptionFilterFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Normalize filter;
	private JTextField txtTranslation;
	private JTextField txtScale;
	private double scale;
	private double translation;

	public NormalizeOptionFilterFrame(Normalize filter) {
		setTitle("Normalize Options");
		setType(Type.UTILITY);
		setSize(new Dimension(225, 140));
		setResizable(false);
		this.filter = filter;
		getContentPane().setLayout(null);
		
		JLabel lblScale = new JLabel("Scale:");
		lblScale.setBounds(10, 11, 70, 14);
		getContentPane().add(lblScale);
		
		JLabel lblTranslation = new JLabel("Translation:");
		lblTranslation.setBounds(10, 42, 70, 14);
		getContentPane().add(lblTranslation);
		
		txtTranslation = new JTextField(""+filter.getTranslation());
		txtTranslation.setBounds(90, 39, 98, 20);
		getContentPane().add(txtTranslation);
		txtTranslation.setColumns(10);
		
		txtScale = new JTextField(""+filter.getScale());
		txtScale.setBounds(90, 8, 98, 20);
		getContentPane().add(txtScale);
		txtScale.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(10, 70, 86, 20);
		getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					setScale(Double.parseDouble(txtScale.getText()));
				}catch(NumberFormatException ex){
					setScale(1d);
				}
				try{
					setTranslation(Double.parseDouble(txtTranslation.getText()));
				}catch(NumberFormatException ex){
					setTranslation(0d);
				}
				dispose();
			}
		});
		btnOk.setBounds(102, 70, 86, 20);
		getContentPane().add(btnOk);
		
	}

	public Normalize getFilter() {
		return filter;
	}

	public void setFilter(Normalize filter) {
		this.filter = filter;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getTranslation() {
		return translation;
	}

	public void setTranslation(double translation) {
		this.translation = translation;
	}
}
