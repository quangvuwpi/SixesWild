package sw.app.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import sw.app.gui.controller.CreditScreenController;
import sw.app.gui.controller.PlayGameScreenController;
import sw.app.gui.controller.ScoreboardScreenController;

public class MainMenuView extends JPanel implements IView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1717902879125448300L;
	LayoutManager lm;

	public MainMenuView(LayoutManager lm) {
		this.lm = lm;
		setLayout(null);
		initialize();
	}

	@Override
	public void initialize() {
		//this.setBackground(Color.WHITE);
		JButton btnPlay = new JButton();
		btnPlay.setBorder(null);
		add(btnPlay);
		btnPlay.setBounds(300, 250, 190, 50);
		btnPlay.setIcon(new ImageIcon("src/sw/resource/image/PlayButton.png"));
		
		JButton btnScoreboard = new JButton();
		btnScoreboard.setBorder(null);
		add(btnScoreboard);
		btnScoreboard.setBounds(300, 325, 190, 50);
		btnScoreboard.setIcon(new ImageIcon("src/sw/resource/image/ScoreboardButton.png"));
		
		JButton btnCredits = new JButton();
		btnCredits.setBorder(null);
		add(btnCredits);
		btnCredits.setBounds(300, 400, 190, 50);
		btnCredits.setIcon(new ImageIcon("src/sw/resource/image/CreditsButton.png"));
		
		
		
		btnPlay.addActionListener(new PlayGameScreenController(lm));
		btnScoreboard.addActionListener(new ScoreboardScreenController(lm));
		btnCredits.addActionListener(new CreditScreenController(lm));
		
		JLabel background = new JLabel();
		add(background);
		background.setBounds(0, 0, 800, 600);
		background.setIcon(new ImageIcon("src/sw/resource/image/SixesWildMainMenuImage.png"));
	}

	public Dimension getPrefferredSize() {
		return new Dimension(800, 600);
	}

	@Override
	public void cleanup() {

	}
}
