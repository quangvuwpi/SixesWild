package sw.app.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sw.app.gui.view.LayoutManager;
import sw.common.model.entity.Level;
import sw.common.system.manager.LevelManager;

public class PostGameController implements ActionListener {

	LayoutManager lm;
	LevelManager lvlm;
	
	public PostGameController(LayoutManager lm, LevelManager lvlm) {
		this.lm = lm;
		this.lvlm = lvlm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Level n = lvlm.getNext();		
		lvlm.setCurrent(n);		
		lm.switchToGameplayView();
	}

}
