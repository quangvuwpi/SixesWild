/**
 *  @file   Board.java
 *  @author Tony Vu
 *  @since  Apr 11, 2015
 */
package sw.common.model.entity;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import sw.common.system.factory.SquareFactory;
import sw.common.system.manager.IBoardLocationManager;
import sw.common.system.manager.IBoardSelectionManager;

/** The model for the game board 
 * 
 *  Layout:
 * 
 *     00  01  02  03  04  05  06  07  08
 *     10  11  12  13  14  15  16  17  18
 *     ...
 *     80  81  82  83  84  85  86  87  88 
 * 
 * */
public class Board implements IBoardSelectionManager, IBoardLocationManager, IBoard {
	
	/** Default dimension for the board */
	public static int COLUMN = 9;
	public static int ROW    = 9;
	
	/** The grid of Square */
	ArrayList<Column> grid = new ArrayList<Column>();
	
	/** The selection queue */
	ArrayBlockingQueue<Square> selection = new ArrayBlockingQueue<Square>(Board.COLUMN * Board.ROW, true);
			
	/** Create a new Board
	 * @param fill the board with randomly generated value or not
	 */
	public Board() {
		createBoard(true);
	}
	
	public Board(boolean fill) {
		createBoard(fill);
	}
	
	/** Create new Board
	 * @param fill whether to fill board with Tile
	 */
	private void createBoard(boolean fill) {
		// Create the board
		for (int x = 0; x < Board.COLUMN; x++) {
			// Create rows first since we want easy access to columns
			ArrayList<Square> row = new ArrayList<Square>();
			for (int y = 0; y < Board.ROW; y++) {
				row.add(SquareFactory.getSquare(fill));
			}
			// Add the new row to the grid			
			grid.add(new Column(row));
		}		
	}
	
	/**
	 * @param col to get
	 * @return the column of Square
	 */
	public Column getColumn(int col) {
		try {
			return grid.get(col);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}		
	}
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#clear()
	 */
	@Override
	public void clear() {
		for (int x = 0; x < Board.COLUMN; x++) {
			grid.get(x).clear();
		}		
	}
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#fill()
	 */
	@Override
	public void fill() {
		for (int x = 0; x < Board.COLUMN; x++) {
			grid.get(x).fill();
		}		
	}
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#pack()
	 */
	@Override
	public void pack() {
		for (int x = 0; x < Board.COLUMN; x++) {
			grid.get(x).pack();
		}	
	}
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#size()
	 */
	@Override
	public Dimension size() {
		return new Dimension(Board.COLUMN, Board.ROW);
	}	
	
	/**
	 * @param p position to remove
	 * @param fill whether to fill empty spaces with new Tiles
	 */
	public boolean remove(Point p) {
		if (isValidPoint(p)) {
			Column c = grid.get(p.x);
			return c.removeTile(p.y);  // Remove Tile			
		}
		return false;
	}
	
	/**
	 * @return the number of Tile currently in the Board
	 */
	@Override
	public int count() {
		int total = 0;
		for (int x = 0; x < Board.COLUMN; x++) {
			total += grid.get(x).count();
		}
		return total;
	}

	/**
	 * @param p the position
	 * @param t the new Tile to place at the position
	 * @return true if the new Tile was placed at the position, false if not
	 */
	@Override
	public boolean replace(Point p, Tile t) {
		if (remove(p)) {
			return grid.get(p.x).setTile(t, p.y);
		}
		return false;
	}
	
	/**
	 * @param p the positon
	 * @return true if the position is empty, false if not
	 */
	@Override
	public boolean isEmpty(Point p) {
		return grid.get(p.x).getTile(p.y) == null;		
	}
	
	//////////////////////////////////////////////////////////
	// IBoardLocationnManager methods
	//
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#isValidX(int)
	 */
	@Override
	public boolean isValidX(int x) {
		return (x >= 0 && x < Board.COLUMN);
	}
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#isValidY(int)
	 */
	@Override
	public boolean isValidY(int y) {
		return (y >= 0 && y < Board.ROW);
	}
	
	/* (non-Javadoc)
	 * @see sw.common.model.entity.IBoard#isValidPoint(java.awt.Point)
	 */
	@Override
	public boolean isValidPoint(Point p) {
		return (isValidX(p.x) && isValidY(p.y));
	}
	
	/**
	 * @param tile the Tile to search for
	 * @return the position of the Tile in the Board
	 */
	public Point getPoint(Tile tile) {
		for (int x = 0; x < Board.COLUMN; x++) {
			try {
				int y = grid.get(x).indexOf(tile);
				return new Point(x, y);
			} catch (IllegalAccessError e) {
				continue;
			}			
		}
		return null;
	}
	
	/**
	 * @param p the position
	 * @return the Tile at the position
	 */
	public Tile getTile(Point p) {
		if (isValidPoint(p)) {
			return grid.get(p.x).getTile(p.y);
		}
		return null;
	}
	
	/**
	 * @param p the position
	 * @return the Square at the position
	 */
	public Square getSquare(Point p) {
		if (isValidPoint(p)) {
			return grid.get(p.x).getSquare(p.y);
		}
		return null;
	}
	
	//////////////////////////////////////////////////////////
	// IBoardSelectionManager methods
	//
	
	/**
	 * @param p the position to select
	 */
	public boolean select(Point p) {
		Square s = getSquare(p);
		if (!s.isSelected()) {
			s.setSelected(true);
			try {
				selection.put(s);
				return true;
			} catch (InterruptedException e) {
				// Queue is full, discard this selection
				System.err.println("Selection queue is full!");
				return false;
			}
		}
		return true;  // if Square already selected, return true
	}
	
	/**
	 * @return the selected Squares
	 */
	public Queue<Square> getSelectedSquare() {
		return selection;
	}
	
	/**
	 * @return the selected Tiles
	 */
	public Queue<Tile> getSelectedTile() {
		Queue<Tile> tq = new ArrayBlockingQueue<Tile>(Board.COLUMN * Board.ROW, true);
		Iterator<Square> si = selection.iterator();
		while (si.hasNext()) {
			tq.add(si.next().getTile());
		}
		return tq;
	}
	
	public boolean clearSelection() {
		Iterator<Square> si = selection.iterator();
		while (si.hasNext()) {
			si.next().setSelected(false);
			si.remove();
		}
		// Just in case...
		if (!selection.isEmpty()) {
			selection.clear();
		}
		return true;
	}

}
