package src.sw.app;

import java.awt.EventQueue;

import sw.app.gui.view.SixesWildJFrame;

public class Application {

	SixesWildJFrame app;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
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
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		app = new SixesWildJFrame();
		//app.setBounds(100, 100, 450, 300);
		//app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
