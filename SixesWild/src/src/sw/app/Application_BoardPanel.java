package src.sw.app;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;

import sw.app.gui.view.SixesWildJFrame_BoardPanel;

public class Application_BoardPanel {

	private SixesWildJFrame_BoardPanel app;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application_BoardPanel window = new Application_BoardPanel();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application_BoardPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		app = new SixesWildJFrame_BoardPanel();
		
		Rectangle rec = new Rectangle(app.getPreferredSize());
		app.setBounds(rec);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
