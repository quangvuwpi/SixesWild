package sw.app.gui.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import sw.app.gui.controller.MainMenuController;
import sw.app.gui.controller.PostGameMenuController;
import sw.app.gui.view.board.BoardPanel;
import sw.app.gui.view.board.GameInfoPanel;
import sw.app.gui.view.board.PowerUpPanel;
import sw.app.gui.view.board.ScorePanel;
import sw.app.gui.view.board.TimeMovePanel;
import sw.common.model.entity.Level;
import sw.common.system.manager.LevelManager;

import java.awt.Color;

public class GameplayView extends JPanel implements IView {

	/** GENERATED DO NOT CHANGE */
	private static final long serialVersionUID = -7540685505032159107L;
	
	Dimension size = new Dimension(630, 540);
	
	BoardPanel boardPanel;
	ScorePanel scorePanel;
	GameInfoPanel gameInfoPanel;
	TimeMovePanel timeMovePanel;
	PowerUpPanel powerUpPanel;
	
	LayoutManager manager;
	LevelManager levelManager;
	
	Image background = new ImageIcon(SplashScreenView.class.getResource("/sw/resource/image/SplashScreenResizedtest.png")).getImage();
	
	private JButton btnPostgamemenutest;
	
	JButton quitButton;	
	MainMenuController mmc;
	String quitBtnPath = "/sw/resource/image/button_quit.png";
	ImageIcon quitBtnImg;
	
	/**
	 * Create the panel.
	 */
	public GameplayView(LayoutManager layoutManager, LevelManager levelManager) {
		setBackground(Color.WHITE);
		this.manager = layoutManager;
		this.levelManager = levelManager;
		
		this.boardPanel    = new BoardPanel();
		this.timeMovePanel = new TimeMovePanel();
		this.scorePanel    = new ScorePanel();
		this.gameInfoPanel = new GameInfoPanel();
		this.powerUpPanel  = new PowerUpPanel();
		
		this.quitButton    = new JButton();
		this.mmc           = new MainMenuController(manager);
		this.quitBtnImg    = new ImageIcon(GameplayView.class.getResource(quitBtnPath));
		
	}
	
	private void initializeLevel() {
		Level level = levelManager.getCurrent();
		if (level == null) {
			throw new IllegalStateException("Current level is null!");
		}
		
		boardPanel.setLevel(level);
		gameInfoPanel.setLevelName(level.toString());		
	}
	
	private void initializeLayout() {
		setPreferredSize(new Dimension(685, 564));
		setMinimumSize(getPreferredSize());
		
		boardPanel.setBackground(Color.WHITE);		
		timeMovePanel.setBackground(Color.WHITE);		
		scorePanel.setBackground(Color.WHITE);				
		gameInfoPanel.setBackground(Color.WHITE);		
		powerUpPanel.setBackground(Color.WHITE);
		
		quitButton.setIcon(quitBtnImg);
		quitButton.addActionListener(mmc);
		
		btnPostgamemenutest = new JButton("PostGameMenuTest");
		btnPostgamemenutest.addActionListener(new PostGameMenuController(manager));		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(powerUpPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(timeMovePanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(scorePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(220)
							.addComponent(gameInfoPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(239)
							.addComponent(btnPostgamemenutest)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(gameInfoPanel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(timeMovePanel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scorePanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(powerUpPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPostgamemenutest)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@Override
	public void initialize() {
		try {
			initializeLevel();
			initializeLayout();

			timeMovePanel.initialize();
			timeMovePanel.setTimerAlarm(new dummyAlarm(), Time.valueOf("00:00:05"));

			boardPanel.initialize();

			scorePanel.setMinimum(0);
			scorePanel.setMaximum(1000);
			scorePanel.setScore(300);
			scorePanel.setStar(2);		

			timeMovePanel.startTimer();
		} catch (IllegalStateException e) {
			System.err.println("Cannot initialize the Gameplay View!");
			manager.switchToMainMenu();
		}
	}
	
	class dummyAlarm implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeMovePanel.setMove(25);
		}		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		boardPanel.cleanup();
		
		// Remove all action listeners for this button
		ActionListener[] listeners = quitButton.getActionListeners();
		for (int i = 0; i < listeners.length; i++) {
			quitButton.removeActionListener(listeners[i]);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(background, 0, 0, null);
	}
}
