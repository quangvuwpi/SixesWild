package sw.app.gui.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashScreenView extends JPanel implements IView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LayoutManager lm;
	public SplashScreenView(LayoutManager lm) {
//		
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setIcon(new ImageIcon(SplashScreenView.class.getResource("/sw/resource/image/SplashScreenResizedtest.png")));
//		add(lblNewLabel);
		
		this.lm = lm;
		//initialize();
	}
	
	public void initialize() {
/*		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		//new MainMenuController(this);
		*/
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon(SplashScreenView.class.getResource("/sw/resource/image/SplashScreenImage.png")).getImage(), 0, 0, null);
	}
}
