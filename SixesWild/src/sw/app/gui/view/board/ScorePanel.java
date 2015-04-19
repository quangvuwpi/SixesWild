package sw.app.gui.view.board;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ScorePanel extends JPanel {
	
	JLabel star0;
	JLabel star1;
	JLabel star2;
	
	ImageIcon starImg;
	JProgressBar score;
	
	/** default star image */
	String defaultStar = "/sw/resource/image/star.png";
	
	public ScorePanel() {
		setStarImage(defaultStar);
		
		setOpaque(false);		
		setSize(new Dimension(100, 450));
		setMinimumSize(new Dimension(90, 450));
		
		score = new JProgressBar(JProgressBar.VERTICAL);
		score.setStringPainted(true);
		score.setForeground(Color.GREEN);
		
		star0 = new JLabel("");		
		star1 = new JLabel("");		
		star2 = new JLabel("");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(score, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(star2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(star1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(star0, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(score, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addComponent(star2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(87)
							.addComponent(star1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(87)
							.addComponent(star0, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public void setMaximum(int max) {
		this.score.setMaximum(max);
	}
	
	public void setMinimum(int min) {
		this.score.setMinimum(min);
	}
	
	public void setScore(int score) {
		this.score.setValue(score);
		this.score.setString(Integer.toString(score));
	}
	
	public void setStarImage(String location) {
		starImg = new ImageIcon(ScorePanel.class.getResource(location));
	}

	public void setStar(int numStar) {
		if (numStar > 0 && numStar <= 3) {			
			if (numStar >= 1) {
				star0.setIcon(starImg);
			}
			if (numStar >= 2) {
				star1.setIcon(starImg);
			}
			if (numStar >= 3) {
				star2.setIcon(starImg);
			}
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8082985446420559832L;
}
