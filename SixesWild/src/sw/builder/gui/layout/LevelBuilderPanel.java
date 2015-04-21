package sw.builder.gui.layout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import sw.common.model.entity.Board;
import sw.common.system.factory.TileFrequency;
import sw.mode.Elimination;
import sw.mode.Lightning;
import sw.mode.Puzzle;
import sw.mode.Release;

public class LevelBuilderPanel extends JPanel {
	
	JComboBox<String> mode;
	JTextField level;
	
	JTextField maxMove;
	
	JTextField time;
	JCheckBox countDown;
	
	JTextField scr1Star;
	JTextField scr2Star;
	JTextField scr3Star;
	
	JTextField val1;
	JTextField val2;
	JTextField val3;
	JTextField val4;
	JTextField val5;
	JTextField val6;
	
	JTextField mul1;
	JTextField mul2;
	JTextField mul3;
	
	LevelBuilderTextPanel txtPanel;
	
	TileFrequency oriFreq;
	
	/**
	 * Create the panel.
	 */
	public LevelBuilderPanel(Board board) {
		setSize(new Dimension(500, 455));
		
		txtPanel = new LevelBuilderTextPanel(board);
		txtPanel.setMinimumSize(new Dimension(0, 0));
		
		JLabel lblMode = new JLabel("Mode");
		lblMode.setHorizontalAlignment(SwingConstants.CENTER);		
		
		mode = new JComboBox<String>();
		mode.addItem(new Puzzle().toString());
		mode.addItem(new Lightning().toString());
		mode.addItem(new Elimination().toString());
		mode.addItem(new Release().toString());
		
		JLabel lblLevel = new JLabel("Level");
		
		level = new JTextField();
		level.setHorizontalAlignment(SwingConstants.CENTER);
		level.setText("#");
		level.setColumns(10);
		
		JLabel lblMoves = new JLabel("Move");
		lblMoves.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMoves.setHorizontalAlignment(SwingConstants.CENTER);
		
		maxMove = new JTextField();
		maxMove.setHorizontalAlignment(SwingConstants.CENTER);
		maxMove.setText("max");
		maxMove.setColumns(10);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		time = new JTextField();
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setText("hh:mm:ss");
		time.setColumns(10);
		
		countDown = new JCheckBox("Count down");
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		
		scr1Star = new JTextField();
		scr1Star.setHorizontalAlignment(SwingConstants.CENTER);
		scr1Star.setText("# - 1 star");
		scr1Star.setColumns(10);
		
		scr2Star = new JTextField();
		scr2Star.setHorizontalAlignment(SwingConstants.CENTER);
		scr2Star.setText("# - 2stars");
		scr2Star.setColumns(10);
		
		scr3Star = new JTextField();
		scr3Star.setText("# - 3 stars");
		scr3Star.setHorizontalAlignment(SwingConstants.CENTER);
		scr3Star.setColumns(10);
		
		JLabel lblValue_1 = new JLabel("Value %");
		lblValue_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_1 = new JLabel("2");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_2 = new JLabel("3");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_3 = new JLabel("4");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_4 = new JLabel("5");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_5 = new JLabel("6");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMultiplier = new JLabel("Multiplier %");
		lblMultiplier.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_7 = new JLabel("1");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_8 = new JLabel("2");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label_9 = new JLabel("3");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		
		val1 = new JTextField();
		val1.setHorizontalAlignment(SwingConstants.CENTER);
		val1.setText("%");
		val1.setColumns(10);
		val1.addFocusListener(new FreqFocusListener());
		((AbstractDocument) val1.getDocument()).setDocumentFilter(new FreqFilter());
		
		val2 = new JTextField();
		val2.setText("%");
		val2.setHorizontalAlignment(SwingConstants.CENTER);
		val2.setColumns(10);
		val2.addFocusListener(new FreqFocusListener());
		((AbstractDocument) val2.getDocument()).setDocumentFilter(new FreqFilter());
		
		val3 = new JTextField();
		val3.setText("%");
		val3.setHorizontalAlignment(SwingConstants.CENTER);
		val3.setColumns(10);
		val3.addFocusListener(new FreqFocusListener());
		((AbstractDocument) val3.getDocument()).setDocumentFilter(new FreqFilter());
		
		val4 = new JTextField();
		val4.setText("%");
		val4.setHorizontalAlignment(SwingConstants.CENTER);
		val4.setColumns(10);
		val4.addFocusListener(new FreqFocusListener());
		((AbstractDocument) val4.getDocument()).setDocumentFilter(new FreqFilter());
		
		val5 = new JTextField();
		val5.setText("%");
		val5.setHorizontalAlignment(SwingConstants.CENTER);
		val5.setColumns(10);
		val5.addFocusListener(new FreqFocusListener());
		((AbstractDocument) val5.getDocument()).setDocumentFilter(new FreqFilter());
		
		val6 = new JTextField();
		val6.setText("%");
		val6.setHorizontalAlignment(SwingConstants.CENTER);
		val6.setColumns(10);
		val6.addFocusListener(new FreqFocusListener());
		((AbstractDocument) val6.getDocument()).setDocumentFilter(new FreqFilter());
		
		mul1 = new JTextField();
		mul1.setText("%");
		mul1.setHorizontalAlignment(SwingConstants.CENTER);
		mul1.setColumns(10);
		mul1.addFocusListener(new FreqFocusListener());
		((AbstractDocument) mul1.getDocument()).setDocumentFilter(new FreqFilter());
		
		mul2 = new JTextField();
		mul2.setText("%");
		mul2.setHorizontalAlignment(SwingConstants.CENTER);
		mul2.setColumns(10);
		mul2.addFocusListener(new FreqFocusListener());
		((AbstractDocument) mul2.getDocument()).setDocumentFilter(new FreqFilter());
		
		mul3 = new JTextField();
		mul3.setText("%");
		mul3.setHorizontalAlignment(SwingConstants.CENTER);
		mul3.setColumns(10);
		mul3.addFocusListener(new FreqFocusListener());
		((AbstractDocument) mul3.getDocument()).setDocumentFilter(new FreqFilter());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMode, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMoves, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(maxMove, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(time, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(countDown))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(mode, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblLevel)
									.addGap(10)
									.addComponent(level, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblScore, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scr1Star, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scr2Star, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scr3Star, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMultiplier, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mul1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mul2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mul3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(val6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(val5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(val4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(val3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(val2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblValue_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(val1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMode)
						.addComponent(mode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLevel)
						.addComponent(level, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMoves)
						.addComponent(maxMove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTime)
						.addComponent(time, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(countDown))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScore)
						.addComponent(scr1Star, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scr2Star, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scr3Star, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblValue_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(val1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(val2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(val3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(val4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(val5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(val6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblMultiplier)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_7)
								.addComponent(mul1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_8)
								.addComponent(mul2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9)
								.addComponent(mul3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public void update() {
		txtPanel.updateTextField();
		
		TileFrequency testFreq = getFrequency();
		if (testFreq == null) {
			setFrequency(oriFreq);
		}
	}
	
	public TileFrequency getFrequency() {
		HashMap<Integer, Double> valFreq = new HashMap<Integer, Double>();
		try {			
			double sum = Double.valueOf(val1.getText());
			valFreq.put(1, Double.valueOf(val1.getText()));
			
			sum += Double.valueOf(val2.getText());
			valFreq.put(2, Double.valueOf(val2.getText()));
			
			sum += Double.valueOf(val3.getText());
			valFreq.put(3, Double.valueOf(val3.getText()));
			
			sum += Double.valueOf(val4.getText());
			valFreq.put(4, Double.valueOf(val4.getText()));
			
			sum += Double.valueOf(val5.getText());
			valFreq.put(5, Double.valueOf(val5.getText()));
			
			if ((sum + Double.valueOf(val6.getText())) == 100.0) {
				valFreq.put(6, Double.valueOf(val6.getText()));
			} else {
				// make sure we add up to 100 in case of rounding error
				valFreq.put(6, Double.valueOf(100.0 - sum));
			}
			
			HashMap<Integer, Double> mulFreq = new HashMap<Integer, Double>();
			
			sum = Double.valueOf(mul1.getText());
			mulFreq.put(1, Double.valueOf(mul1.getText()));
			
			sum = Double.valueOf(mul1.getText());
			mulFreq.put(2, Double.valueOf(mul2.getText()));
			
			if ((sum + Double.valueOf(mul3.getText())) == 100.0) {
				mulFreq.put(3, Double.valueOf(mul3.getText()));
			} else {
				mulFreq.put(3, Double.valueOf(100.0 - sum));
			}
					
			return new TileFrequency(valFreq, mulFreq);
		} catch (IllegalArgumentException e) {			
			return null;
		}
	}
	
	public void setFrequency(TileFrequency freq) {
		oriFreq = freq;
		
		val1.setText(String.format("%03.02f", freq.getValPercent(1)));
		val2.setText(String.format("%03.02f",freq.getValPercent(2)));
		val3.setText(String.format("%03.02f",freq.getValPercent(3)));
		val4.setText(String.format("%03.02f",freq.getValPercent(4)));
		val5.setText(String.format("%03.02f",freq.getValPercent(5)));
		val6.setText(String.format("%03.02f",freq.getValPercent(6)));
		
		mul1.setText(String.format("%03.02f",freq.getMulPercent(1)));
		mul2.setText(String.format("%03.02f",freq.getMulPercent(2)));
		mul3.setText(String.format("%03.02f",freq.getMulPercent(3)));
		
//		val1.setText(String.format("%d",Math.round(freq.getValPercent(1))));
//		val2.setText(String.format("%d",Math.round(freq.getValPercent(2))));
//		val3.setText(String.format("%d",Math.round(freq.getValPercent(3))));
//		val4.setText(String.format("%d",Math.round(freq.getValPercent(4))));
//		val5.setText(String.format("%d",Math.round(freq.getValPercent(5))));
//		val6.setText(String.format("%d",Math.round(freq.getValPercent(6))));
//		
//		mul1.setText(String.format("%d",Math.round(freq.getMulPercent(1))));
//		mul2.setText(String.format("%d",Math.round(freq.getMulPercent(2))));
//		mul3.setText(String.format("%d",Math.round(freq.getMulPercent(3))));
	}
	
	private class FreqFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			((JTextField) arg0.getSource()).setCaretPosition(0);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			//TileFrequency testFreq = getFrequency();
			//if (testFreq == null) {
			//	setFrequency(oriFreq);
			//}
		}
		
	}
	
	/** Restrict allowable character in each text field to 3 */
	private class FreqFilter extends DocumentFilter {

		/* (non-Javadoc)
		 * @see javax.swing.text.DocumentFilter#insertString(javax.swing.text.DocumentFilter.FilterBypass, int, java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void insertString(FilterBypass arg0, int arg1, String arg2,
				AttributeSet arg3) throws BadLocationException {
			
			
			//if (!arg2.matches(".") && !arg2.matches("[0-9]") && !arg2.matches("(\\d+).(\\d+)")) { return; }
			
			//if (arg0.getDocument().getLength() + arg2.length() <= 6) {
				super.insertString(arg0, arg1, arg2, arg3);
			//}
		}		

		/* (non-Javadoc)
		 * @see javax.swing.text.DocumentFilter#remove(javax.swing.text.DocumentFilter.FilterBypass, int, int)
		 */
		@Override
		public void remove(FilterBypass fb, int offset, int length)
				throws BadLocationException {
			// TODO Auto-generated method stub
			super.remove(fb, offset, length);
		}

		/* (non-Javadoc)
		 * @see javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter.FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void replace(FilterBypass arg0, int arg1, int arg2, String arg3,
				AttributeSet arg4) throws BadLocationException {
			// Possible bug with this class, should have called insert instead of replace
			//if (arg3.length() == 1 && (arg3.matches("[0-9]") || arg3.matches("."))) {
			//	this.insertString(arg0, arg1, arg3, arg4);
			//	return;
			//} 
			//if (!arg3.matches("(\\d+).(\\d+)")) { return; }
			//if (arg0.getDocument().getLength() + arg3.length() <= 6) {
				super.replace(arg0, arg1, arg2, arg3, arg4);
			//}			
		}
			
	}
}
