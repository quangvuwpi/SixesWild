package sw.app.gui.view.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import sw.app.gui.view.IView;

import java.awt.Color;

public class TimeMovePanel extends JPanel implements IView, ActionListener {
	
	JLabel timeLabel;
	JLabel time;
	JLabel moveLabel;
	JLabel move;
	
	Timer timer;
	int timerPeriod;
	boolean countDown;
	
	Time alarm;
	ActionListener listener;
	
	int ONE_SEC = 1000;  // msec per second
	
	public TimeMovePanel() {		
		setBackground(Color.LIGHT_GRAY);		
		
		setPreferredSize(new Dimension(450, 15));
		
		timeLabel = new JLabel("Time");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		time = new JLabel();
		time.setHorizontalAlignment(SwingConstants.CENTER);
		
		move = new JLabel();
		move.setHorizontalAlignment(SwingConstants.CENTER);
		
		moveLabel = new JLabel("Move");
		moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(time, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
					.addComponent(moveLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(move, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(time, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(move, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(moveLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);		
	}
	
	public void setCountDown(boolean countDown) {
		this.countDown = countDown;
	}
	
	public void setTime(Time time) {
		this.time.setText(time.toString());
	}
	
	public Time getTime() {
		return Time.valueOf(this.time.getText());
	}
	
	public void setMove(int move) {
		this.move.setText(Integer.toString(move));
	}
	
	public int getMove() {
		return Integer.parseInt(this.move.getText());
	}
	
	/**
	 * @param period in seconds
	 */
	public void setTimerPeriod(int period) {
		this.timerPeriod = period * ONE_SEC; // timer takes period in msec
		
		stopTimer();
		timer = new Timer(timerPeriod, this);
	}

	public void startTimer() {
		if (timer != null && !timer.isRunning()) {
			timer.start();
		}
	}
	
	public void stopTimer() {
		if (timer != null && timer.isRunning()) {
			timer.stop();
		}
	}
	
	public void setTimerAlarm(ActionListener al, Time time) {
		listener = al;
		alarm = time;
	}
	
	public void reset() {		
		setTime(Time.valueOf("00:00:00"));
		setMove(0);
		
		stopTimer();
		setTimerPeriod(1);
	}
	
	@Override
	public void initialize() {
		reset();		
	}

	@Override
	public void cleanup() {
		stopTimer();		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Time currentTime = getTime();
		long time = currentTime.getTime();  // msec
		
		if (currentTime.compareTo(alarm) == 0) {
			// Fire action event
			listener.actionPerformed(new ActionEvent(this, 0, null));
		}
		
		if (countDown && time >= timerPeriod) {
			time -= timerPeriod;			
		} else {
			time += timerPeriod;
		}
		
		setTime(new Time(time));
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8208502195019158726L;

}
