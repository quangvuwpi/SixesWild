package sw.app.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sw.app.gui.view.LayoutManager;

public class ScoreboardScreenController implements ActionListener {
	
	LayoutManager lm;
	
	public ScoreboardScreenController(LayoutManager lm) {
		this.lm = lm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		lm.switchToScoreBoardView();
		// set controller for previous button
	}

}
