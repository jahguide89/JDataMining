package es.ull.datamining.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import es.ull.datamining.core.Dataset;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


public class MainFrame extends JFrame implements IView{
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/es/ull/datamining/gui/images/icon64x64.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JDataMining");
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		btnAnalysis = new JButton("Analysis");
		btnAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				runAnalysis();
			}
		});
		btnAnalysis.setBounds(10, 200, 110, 50);
		getContentPane().add(btnAnalysis);
		
		btnExperimenter = new JButton("Experimenter");
		btnExperimenter.setBounds(130, 200, 124, 50);
		btnExperimenter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				runExperimenter();
			}
		});
		getContentPane().add(btnExperimenter);
		
		btnAbout = new JButton("About");
		btnAbout.setBounds(264, 200, 110, 50);
		btnAbout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new AboutFrame().setVisible(true);;
			}
		});
		getContentPane().add(btnAbout);
		
		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(MainFrame.class.getResource("images/logo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 11, 348, 178);
		getContentPane().add(lblLogo);
	}
	
	protected void runAnalysis() {
		Dataset model = new Dataset();
		IController controller = new AnalysisController(model);
		
	}
	
	protected void runExperimenter() {
		IController controller = new ExperimenterController();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);;
			}
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAnalysis;
	private JButton btnExperimenter;
	private JButton btnAbout;
	private JLabel lblLogo;;
}
