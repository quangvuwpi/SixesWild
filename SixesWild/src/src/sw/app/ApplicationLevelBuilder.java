package src.sw.app;

import java.awt.EventQueue;

import sw.app.gui.view.SixesWildJFrame;
import sw.builder.gui.layout.LevelBuilder;
import sw.builder.gui.layout.LevelBuilderJFrame;


public class ApplicationLevelBuilder {
	
	private LevelBuilder app;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationLevelBuilder window = new ApplicationLevelBuilder();
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
	public ApplicationLevelBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		app = new LevelBuilder();
		//app.setBounds(100, 100, 450, 300);
		//app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

