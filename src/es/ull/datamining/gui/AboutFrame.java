package es.ull.datamining.gui;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Window.Type;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class AboutFrame extends JFrame {
	public AboutFrame() {
		setResizable(false);
		
		setTitle("About JDataMining");
		
		JPanel iconPane = new JPanel();
		
		JLabel lblTitle = new JLabel("JDataMining");
		lblTitle.setFont(new Font("Bookman Old Style", Font.PLAIN, 40));
		
		JLabel lblVersion = new JLabel("Version 1.0");
		
		JLabel lblc = new JLabel("(c) 2014-2015");
		
		JLabel lblAutor = new JLabel("Author: Ivan S. Ramirez");
		
		JLabel lblLaLagunaSpain = new JLabel("La Laguna, Spain");
		
		JPanel linksPane = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(iconPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitle)
								.addComponent(lblVersion)
								.addComponent(lblc)
								.addComponent(lblAutor)
								.addComponent(lblLaLagunaSpain)))
						.addComponent(linksPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVersion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAutor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLaLagunaSpain))
						.addComponent(iconPane, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(linksPane, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		
		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(AboutFrame.class.getResource("/es/ull/datamining/gui/images/icon128.png")));
		iconPane.add(lblImg);
		linksPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblLicence = new JLabel("<html><font color=\"#0000CF\"><u>"+"Licence"+"</u></font></html>");
		lblLicence.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLicence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI("http://www.gnu.org/licenses/gpl-2.0.html"));
                } catch (URISyntaxException urlEx) {
                	// TODO: handle exception
                }catch (IOException ioEx) {
					// TODO: handle exception
				}
			}
		});
		lblLicence.setHorizontalAlignment(SwingConstants.CENTER);
		linksPane.add(lblLicence);
		
		JLabel lblHomepage = new JLabel("<html><font color=\"#0000CF\"><u>Homepage</u></font></html>");
		lblHomepage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblHomepage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
                    Desktop.getDesktop().browse(new URI("http://jahguide89.github.io/JDataMining/"));
                } catch (URISyntaxException urlEx) {
                	// TODO: handle exception
                }catch (IOException ioEx) {
					// TODO: handle exception
				}
			}
		});
		lblHomepage.setHorizontalAlignment(SwingConstants.CENTER);
		linksPane.add(lblHomepage);
		getContentPane().setLayout(groupLayout);
		
		setSize(new Dimension(460, 235));
	}
}
