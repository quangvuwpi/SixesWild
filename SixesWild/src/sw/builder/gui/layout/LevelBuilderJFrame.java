package sw.builder.gui.layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import sw.app.gui.view.IView;

public class LevelBuilderJFrame extends JFrame implements IView{
	BuilderLayoutManager blm = new BuilderLayoutManager(this);
	SplashScreenBuilderView ssbv = new SplashScreenBuilderView(blm);
	
	Container pane = getContentPane();
	long startTime;
	
	
	public LevelBuilderJFrame() {
		this.setSize(800,600);
		this.setLayout(new BorderLayout());
		setResizable(false);
		this.setVisible(true);
		
		this.initialize();


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
	
	
	public void initialize() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(ssbv);
		ssbv.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				blm.switchToLevelBuilder();
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
		// TODO Auto-generated method stub
		
	}

}
