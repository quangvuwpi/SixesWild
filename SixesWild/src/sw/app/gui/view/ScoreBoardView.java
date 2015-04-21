package sw.app.gui.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.sql.Time;

import javax.swing.JTextPane;

import sw.app.gui.controller.MainMenuController;
import sw.common.model.entity.Game;
import sw.common.model.entity.Level;
import sw.common.model.entity.Statistics;
import sw.common.system.factory.TileFrequency;
import sw.mode.Release;

import javax.swing.SwingConstants;

public class ScoreBoardView extends JPanel implements IView {
	
	LayoutManager lm;
	
	JLabel lblLevel;
	JLabel lblScore;
	JLabel lblMove;
	JLabel lblTime;
	
	int yPos = 77;
	int gap = 22;
	int count = 0;
	
	int levelX = 40;
	int scoreX = 230;
	int moveX  = 420;
	int timeX  = 610;
	
	Dimension fieldSize = new Dimension(150,15);
	private JButton btnNewButton_1;
	
	public ScoreBoardView(LayoutManager lm) {
		setSize(new Dimension(800, 600));
		setBackground(Color.WHITE);
		this.lm = lm;
		initialize();
	}
	
	public void initialize(){
		
		JButton btnBack = new JButton("Back");
		
		lblLevel = new JLabel("Level");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMove = new JLabel("Move");
		lblMove.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Scoreboard");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnNewButton_1 = new JButton("Add score test");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Level newLevel = new Level(count, new Game(), new Statistics(), new Release(), new TileFrequency());
				addLevelStats(newLevel);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(325)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(285)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblMove, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(683, Short.MAX_VALUE)
					.addComponent(btnNewButton_1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblScore)
							.addComponent(lblLevel)
							.addComponent(lblMove))
						.addComponent(lblTime))
					.addGap(433)
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(btnNewButton_1))
		);
		setLayout(groupLayout);
		
		btnBack.addActionListener(new MainMenuController(lm));
		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
	public void addLevelStats(Level level) {
		
		// Limit to 25 entries
		if (count > 25) {
			return;
		}
		
		// Add new entry
		count++;
		
		JLabel levelNum = new JLabel();
		levelNum.setText(Integer.toString(level.getLevelNum()));		
		levelNum.setHorizontalAlignment(SwingConstants.CENTER);
		levelNum.setSize(fieldSize);
		levelNum.setLocation(new Point(levelX, yPos + (fieldSize.height * count) + gap));
		this.add(levelNum);
		
		JLabel score = new JLabel();
		score.setText(Integer.toString(level.getGame().getStats().getScore()));		
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setSize(fieldSize);
		score.setLocation(new Point(scoreX, yPos + (fieldSize.height * count) + gap));
		this.add(score);
		
		JLabel move = new JLabel();
		move.setText(Integer.toString(level.getGame().getStats().getNumMoves()));		
		move.setHorizontalAlignment(SwingConstants.CENTER);
		move.setSize(fieldSize);
		move.setLocation(new Point(moveX, yPos + (fieldSize.height * count) + gap));
		this.add(move);
		
		JLabel time = new JLabel();
		time.setText(level.getGame().getStats().getCurrent().toString());		
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setSize(fieldSize);
		time.setLocation(new Point(timeX, yPos + (fieldSize.height * count) + gap));
		this.add(time);
		
		this.invalidate();
		this.repaint();		
	}
}
