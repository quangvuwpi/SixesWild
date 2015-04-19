package sw.app.gui.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import sw.app.gui.controller.MainMenuController;
import sw.app.gui.controller.PostGameController;
import sw.app.gui.controller.StartGameController;
import sw.common.system.manager.LevelManager;

import java.awt.Dimension;
import java.awt.Color;

public class PostGameView extends JPanel implements IView{
	
	LayoutManager lm;
	LevelManager lvlm;
	
	public PostGameView(LayoutManager lm, LevelManager lvlm) {
		setBackground(Color.WHITE);
		setSize(new Dimension(800, 600));
		this.lm = lm;
		this.lvlm = lvlm;
		initialize();
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

		JButton btnMainmenu = new JButton("MainMenu");
		btnMainmenu.addActionListener(new MainMenuController(lm));
		
		JButton btnNextlevel = new JButton("NextLevel");
		btnNextlevel.addActionListener(new PostGameController(lm, lvlm));
		
		JButton btnReplay = new JButton("Replay");
		btnReplay.addActionListener(new StartGameController(lm, lvlm));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnMainmenu, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(150)
					.addComponent(btnReplay, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
					.addComponent(btnNextlevel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(263, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMainmenu)
						.addComponent(btnReplay)
						.addComponent(btnNextlevel))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
}
