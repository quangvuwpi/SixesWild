package sw.builder.gui.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import sw.common.model.entity.Board;
import sw.common.model.entity.Tile;

public class LevelBuilderTextPanel extends JPanel {

	Board board;
	HashMap<Point, JTextField> textFields = new HashMap<Point, JTextField>();
	
	Stack<Point> highlighted = new Stack<Point>();
	Color bgc = null;
	
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
				updateBoard(p);
			}
		}	
	}
	
	public void updateBoard(Point p) {
		if (board.isValidPoint(p)) {
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
	
	void highlightField(Point p) {
		if (board.isValidPoint(p)) {
			highlighted.push(p);
			JTextField tf = textFields.get(p);
			bgc = tf.getBackground();
			tf.setBackground(Color.YELLOW);
		}
	}
	
	void clearHighlight() {
		while (!highlighted.isEmpty()) {
			Point p = highlighted.pop();
			JTextField tf = textFields.get(p);
			if (tf.getBackground().equals(Color.YELLOW)) {
				tf.setBackground(bgc);
			}			
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
	
	Point getPoint(JTextField tf) {
		if (textFields.containsValue(tf)) {
			Set<Entry<Point, JTextField>> entries = textFields.entrySet();
			for (Entry<Point, JTextField> entry : entries) {
				if (entry.getValue() == tf) {
					return entry.getKey();
				}
			}
		}
		return null;
	}
	
	/** Update board when we done editing a text field */
	private class FieldFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			board.clearSelection();
			clearHighlight();
			
			JTextField tf = (JTextField) arg0.getSource();			
			tf.setCaretPosition(0);
			
			Point p = getPoint(tf);
			if (p != null) {
				board.select(p);
			}
			highlightField(p);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			updateBoard(getPoint((JTextField) arg0.getSource()));
		}
		
	}
	
	/** Typing to automatically go to the next text field */
	private class KeyFilter implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {			
			process(e);
		}
		
		void process(KeyEvent e) {
			JTextField tf = (JTextField) e.getSource();
			int pos = tf.getCaretPosition();
			
			if (e.getKeyChar() == '\n') {
				updateBoard();				
			} else if (pos == 0 && String.valueOf(e.getKeyChar()).matches("[0-9]")) {
				e.consume();
				int val = Integer.valueOf(String.valueOf(e.getKeyChar()));
				int mul = Integer.valueOf(tf.getText().split("/")[1]);
				String newStr = String.format("%d/%d", val, mul);				
				tf.setText(newStr);
				tf.setCaretPosition(2);				
			} else if (pos == 2 && String.valueOf(e.getKeyChar()).matches("[0-9]")) {
				e.consume();
				int mul = Integer.valueOf(String.valueOf(e.getKeyChar()));
				int val = Integer.valueOf(tf.getText().split("/")[0]);
				String newStr = String.format("%d/%d", val, mul);
				tf.setText(newStr);
				
				for (Point p : textFields.keySet()) {
					if (textFields.get(p) == tf) {						
						Point next = nextPoint(p);
						if (next != null) {
							textFields.get(next).requestFocus();							
						} else {							
							textFields.get(new Point(0,0)).requestFocus();
						}
						break;
					}
				}				
			}
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
			super.remove(fb, offset, length);
		}

		/* (non-Javadoc)
		 * @see javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter.FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void replace(FilterBypass arg0, int arg1, int arg2, String arg3,
				AttributeSet arg4) throws BadLocationException {
			if (arg3.length() == 3) {
				super.replace(arg0, arg1, arg2, arg3, arg4);
			}			
		}
			
	}

}
