package sw.app.gui.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import sw.app.gui.controller.MainMenuController;
import sw.app.gui.controller.StartGameController;
import sw.common.model.entity.Level;
import sw.common.system.manager.LevelManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayGameScreenView extends JPanel implements IView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LayoutManager lm;
	LevelManager lvlm;
	ArrayList<Level> lvlList;
	
	JComboBox<String> levelList;
	
	JButton btnBack;
	JButton btnStartGame;
	
	public PlayGameScreenView(LayoutManager lm, LevelManager lvlm) {
		this.lm = lm;
		this.lvlm = lvlm;	
		this.lvlList = lvlm.getLevels();	
	}
	
	public void initialize() {
		setBackground(Color.WHITE);		
		
		JLabel lblLevel = new JLabel("Level");
		
		levelList = new JComboBox<String>();
		levelList.setBackground(Color.WHITE);
		
		for (int i = 0; i < lvlList.size(); i++) {
			String name = lvlList.get(i).toString();
			levelList.addItem(name);
		}
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new MainMenuController(lm));
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new StartGameController(lm, lvlm));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
					.addComponent(btnStartGame, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(112)
					.addComponent(lblLevel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(levelList, 0, 143, Short.MAX_VALUE)
					.addGap(146))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(124)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(levelList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnStartGame))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	@Override
	public void cleanup() {
		levelList.setSelectedIndex(0);		
	}
	
	void addLevel(Level level) {
		if (level != null) {
			levelList.addItem(level.toString());
		}
	}
	
	public Level getSelectedLevel() {
		return lvlm.getLevels().get(levelList.getSelectedIndex());
	}

}
