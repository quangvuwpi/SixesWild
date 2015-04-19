package sw.app.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sw.app.gui.view.LayoutManager;

public class MainMenuController implements ActionListener {
	
	LayoutManager lm;
	
	public MainMenuController(LayoutManager lm) {
		this.lm = lm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		lm.switchToMainMenu();
	}

}
