/**
 * @file QuitButtonActionListener.java
 * @date Apr 11, 2015 3:59:47 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.model.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sw.app.gui.view.LayoutManager;

/** Common controller for quit buttons */
public class ButtonQuitActionListener implements ActionListener {

	LayoutManager manager;
	
	public ButtonQuitActionListener(LayoutManager manager) {
		super();
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		manager.switchToMainMenu();
	}

}
