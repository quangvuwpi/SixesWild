package sw.app.gui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sw.common.system.manager.LevelManager;

public class SixesWildJFrame extends JFrame implements IView{
	/*
	public static void main(String[] args) {
		SixesWildJFrame swj = new SixesWildJFrame();

		swj.setVisible(true);
		swj.initialize();
	}*/


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LevelManager levelManager = new LevelManager();
	LayoutManager lm = new LayoutManager(this);
	
	
	SplashScreenView ssv = new SplashScreenView(lm);
	
	//MainMenuView mmv = new MainMenuView(lm);
	Container pane = getContentPane();
	long startTime;

	public SixesWildJFrame() {
		setTitle("Sixes Wild");
		/*
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1006, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 724, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.add(ssv);
		setSize(1060, 850);
		getContentPane().setLayout(groupLayout);*/
		
		
		this.setSize(800,600);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		this.setVisible(true);
		
		this.initialize();


	}

	@Override
	public void initialize() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(ssv);
		ssv.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				lm.switchToMainMenu();
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}

	@Override
	public void cleanup() {

	}
	
	void switchToScreen(JPanel screen){
		System.out.println("Switch to next screen");
		
		// call cleanup on all IView components
		int count = pane.getComponentCount();
		Component[] c = pane.getComponents();
		for (int i = 0; i < count; i++) {
			if (c[i] instanceof IView) {
				((IView) c[i]).cleanup();
			}
		}
		
		pane.removeAll();
		pane.add(screen);		
		pane.revalidate();
		pane.repaint();
		
		// initialize the new view
		if (screen instanceof IView) {
			((IView) screen).initialize();
		}
	}
	
	public LevelManager getLevelManager(){
		return levelManager;
	}
	
	
}

