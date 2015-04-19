package sw.app.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sw.app.gui.view.LayoutManager;

public class PostGameMenuController implements ActionListener {
	
	LayoutManager lm;
	public PostGameMenuController(LayoutManager lm) {
		this.lm = lm;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lm.switchToPostGameView();
	}

}
