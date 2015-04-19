package sw.builder.gui.layout;

import java.awt.AWTKeyStroke;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import sw.common.model.entity.Board;
import sw.common.model.entity.Tile;
import sw.common.system.factory.TileFactory;

public class LevelBuilderTextPanel extends JPanel implements ActionListener {

	Board board;
	HashMap<Point, JTextField> textFields = new HashMap<Point, JTextField>();
	
	/**
	 * Create the panel.
	 */
	public LevelBuilderTextPanel(Board board) {
		this.board = board;
		
		setLayout(null);

		Dimension size = board.size();
		Dimension txtSize = new Dimension(35,35);
		
		int xPos = 0;
		int yPos = 0;
		
		// Print y-labels
		xPos = 0;
		yPos = txtSize.height;
		for (int y = 0; y < size.height; y++) {					
			
			JLabel yLabel = new JLabel();
			yLabel.setText(Integer.toString(y));		
			yLabel.setHorizontalAlignment(SwingConstants.CENTER);
			yLabel.setSize(txtSize);
			yLabel.setLocation(new Point(xPos, yPos));
			add(yLabel);
			
			yPos += txtSize.height;
		}
		
		// Print x-labels
		xPos = txtSize.width;
		yPos = 0;
		for (int x = 0; x < size.width; x++) {					
			
			JLabel xLabel = new JLabel();
			xLabel.setText(Integer.toString(x));		
			xLabel.setHorizontalAlignment(SwingConstants.CENTER);
			xLabel.setSize(txtSize);
			xLabel.setLocation(new Point(xPos, yPos));
			add(xLabel);
			
			xPos += txtSize.height;
		}
		
		// Add text fields
		xPos = txtSize.width;
		yPos = txtSize.height;
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {					
				Point p = new Point(x, y);
				
				JTextField field = new JTextField();
				
				Tile t = board.getTile(p);
				//field.setText(String.format("%d/%d", t.getValue(), t.getMultiplier()));
				field.setHorizontalAlignment(JTextField.CENTER);
				
				field.setBounds(xPos, yPos, txtSize.width, txtSize.height);
				field.setColumns(3);
				add(field);
				
				AbstractDocument d = (AbstractDocument) field.getDocument();
				d.setDocumentFilter(new FieldFilter());				
				
				field.addKeyListener(new KeyFilter());				
				field.addFocusListener(new FieldFocusListener());
				
				textFields.put(p, field);
				
				yPos += txtSize.height;
			}
			xPos += txtSize.width;
			yPos = txtSize.height;
		}
		
		updateTextField();
		updateBoard();
		
		//this.addKeyListener(new KeyFilter());
	}
	
	public void updateTextField() {
		Dimension size = board.size();
		
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {					
				Point p = new Point(x, y);
				updateTextField(p);
			}
		}	
	}
	
	public void updateTextField(Point p) {
		if (board.isValidPoint(p)) {
			Tile t = board.getTile(p);
			
			JTextField field = textFields.get(p);				
			field.setText(String.format("%d/%d", t.getValue(), t.getMultiplier()));
		}
	}
	
	public void updateBoard() {
		Dimension size = board.size();
		
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {				
				Point p = new Point(x, y);				
				try {
					JTextField field = textFields.get(p);
					String text = field.getText();
					
					String[] data = text.split("/");
					if (data.length == 2) {
						int val = Integer.valueOf(text.split("/")[0]);
						int mul = Integer.valueOf(text.split("/")[1]);
						
						Tile t = new Tile(val, mul);
						board.replace(p, t);
					} else {
						updateTextField(p);
					}
				} catch (IllegalArgumentException e) {
					updateTextField(p);
				}
			}
		}	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		updateBoard();		
	}
	
	private class FieldFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			((JTextField) arg0.getSource()).setCaretPosition(0);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			updateBoard();
		}
		
	}
	
	private class KeyFilter implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {			
			process(e);
			//e.consume();
		}
		
		void process(KeyEvent e) {
			JTextField tf = (JTextField) e.getSource();
			int pos = tf.getCaretPosition();
			
			if (e.getKeyChar() == '\n') {
				updateBoard();				
			} 
//			else if (pos == 0) {
//				int val = Integer.valueOf(String.valueOf(e.getKeyChar()));
//				int mul = Integer.valueOf(tf.getText().split("/")[1]);
//				String newStr = String.format("%d/%d", val, mul);
//				tf.setText("");
//				tf.setText(newStr);
//				tf.setCaretPosition(2);
//				newStr = tf.getText();
//			} else if (pos == 2) {
//				int mul = Integer.valueOf(String.valueOf(e.getKeyChar()));
//				int val = Integer.valueOf(tf.getText().split("/")[0]);
//				String newStr = String.format("%d/%d", val, mul);
//				tf.setText(newStr);
//				
//				for (Point p : textFields.keySet()) {
//					if (textFields.get(p) == tf) {						
//						Point next = nextPoint(p);
//						textFields.get(next).requestFocus();
//					}
//				}
//			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}		
	}
	
	Point nextPoint(Point p) {
		if (board.isValidPoint(p)) {
			Point next = new Point();
			if (board.isValidX(p.x + 1)) {
				next.x = p.x + 1;
				next.y = p.y;
			} else if (board.isValidY(p.y + 1)) {
				next.x = 0;
				next.y = p.y + 1;
			} else {
				next = null;
			}
			return next;
		}
		return null;
	}
	
	/** Restrict allowable character in each text field to 3 */
	private class FieldFilter extends DocumentFilter {

		/* (non-Javadoc)
		 * @see javax.swing.text.DocumentFilter#insertString(javax.swing.text.DocumentFilter.FilterBypass, int, java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void insertString(FilterBypass arg0, int arg1, String arg2,
				AttributeSet arg3) throws BadLocationException {
			if (arg0.getDocument().getLength() + arg2.length() <= 3) {
				super.insertString(arg0, arg1, arg2, arg3);
			}
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
			// TODO Auto-generated method stub
			if (arg3.length() <= 3) {
				super.replace(arg0, arg1, arg2, arg3, arg4);
			}			
		}
			
	}

}
