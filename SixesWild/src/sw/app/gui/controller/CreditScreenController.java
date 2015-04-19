package sw.app.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sw.app.gui.view.LayoutManager;

public class CreditScreenController implements ActionListener {
	
	LayoutManager lm;
	
	public CreditScreenController(LayoutManager lm) {
		this.lm = lm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		lm.switchToCreditScreenView();
	}

}
