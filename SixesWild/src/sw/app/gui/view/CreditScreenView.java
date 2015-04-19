package sw.app.gui.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sw.app.gui.controller.MainMenuController;

import java.awt.Color;

import javax.swing.LayoutStyle.ComponentPlacement;

public class CreditScreenView extends JPanel implements IView{
	
	Image background = new ImageIcon(CreditScreenView.class.getResource("/sw/resource/image/SixesWildMainMenuImage.png")).getImage();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LayoutManager lm;
	public CreditScreenView(LayoutManager lm) {
		setBackground(Color.WHITE);
		this.lm = lm;
		initialize();
	}
	
	public void initialize() {
		
		setPreferredSize(new Dimension(800, 600));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new MainMenuController(lm));
		
		JLabel lblNewLabel = new JLabel("Vanshaj Chowdhary");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("French Script MT", Font.PLAIN, 25));
		
		JLabel label = new JLabel("Trung-Nghia Ngo Huynh");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("French Script MT", Font.PLAIN, 25));
		
		JLabel label_1 = new JLabel("Jessie Greg Johnson");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("French Script MT", Font.PLAIN, 25));
		
		JLabel label_2 = new JLabel("Tuan Quang Vu");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("French Script MT", Font.PLAIN, 25));
		
		JLabel label_3 = new JLabel("Steven Yevchak");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("French Script MT", Font.PLAIN, 25));
		
		JLabel label_4 = new JLabel("Dr. George Heineman");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("French Script MT", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("Special Thanks to");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Forte", Font.PLAIN, 35));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(200)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(325)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(200, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(150)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(label)
					.addGap(18)
					.addComponent(label_1)
					.addGap(18)
					.addComponent(label_2)
					.addGap(18)
					.addComponent(label_3)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(label_4)
					.addGap(55)
					.addComponent(btnBack)
					.addGap(128))
		);
		setLayout(groupLayout);
		
	}
	
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}
