package sw.app.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import sw.app.gui.view.LayoutManager;
import sw.app.gui.view.PlayGameScreenView;
import sw.common.model.entity.Level;
import sw.common.system.manager.LevelManager;
public class StartGameController implements ActionListener{
	
	LayoutManager lm;
	LevelManager lvlm;
	
	public StartGameController(LayoutManager lm, LevelManager lvlm){
		this.lm = lm;
		this.lvlm = lvlm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel current = lm.getCurrentView();
		if (current instanceof PlayGameScreenView) {
			Level l = ((PlayGameScreenView) current).getSelectedLevel();
			lvlm.setCurrent(l);
		} 
		lm.switchToGameplayView();		
	}
	
	

}
