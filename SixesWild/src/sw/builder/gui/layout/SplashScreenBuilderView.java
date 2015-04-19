package sw.builder.gui.layout;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sw.app.gui.view.IView;

public class SplashScreenBuilderView extends JPanel implements IView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BuilderLayoutManager blm;
	public SplashScreenBuilderView(BuilderLayoutManager bblm) {
//		
//		JLabel lblNewLabel = new JLabel("");
//		lblNewLabel.setIcon(new ImageIcon(SpashScreenBuilderView.class.getResource("/sw/resource/image/SplashScreenResizedtest.png")));
//		add(lblNewLabel);
		
		this.blm = blm;
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
		g.drawImage(new ImageIcon(SplashScreenBuilderView.class.getResource("/sw/resource/image/SplashScreenImage.png")).getImage(), 0, 0, null);
	}
}
