/**
 *  @file   Column.java
 *  @author Tony Vu
 *  @since  Apr 14, 2015
 */
package sw.common.model.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import sw.common.system.factory.TileFactory;

/** Model of a column in a Board */
public class Column {

	/** The index of this Column */
	int colIdx;

	/** The collection of Squares */
	ArrayList<Square> col = new ArrayList<Square>();

	public Column(ArrayList<Square> col) {
		setColumn(col);
	}

	/**
	 * @param newCol
	 *            to manage
	 */
	void setColumn(ArrayList<Square> newCol) {
		col = newCol;
	}

	/**
	 * @param t
	 *            the Tile to search for
	 * @return the index of the Tile
	 * @throws IllegalAccessError
	 *             if not found
	 */
	public int indexOf(Tile t) throws IllegalAccessError {
		if (t != null) {
			for (int y = 0; y < col.size(); y++) {
				Square s = col.get(y);
				if (s != null) {
					if (s.getTile() == t) {
						return y;
					}
				}
			}
		}
		throw new IllegalAccessError("Tile not found!");
	}

	/**
	 * @param t
	 *            Tile to set
	 * @param idx
	 *            position to add to
	 * @return true if Tile is added, false if not
	 */
	boolean setTile(Tile t, int idx) {
		try {
			Square s = col.get(idx);
			s.setTile(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param idx the index
	 * @return the Tile at idx, null if index is empty
	 */
	public Tile getTile(int idx) {
		Square s = getSquare(idx);
		if (s != null) {
			return s.getTile();
		}
		return null;
	}

	/**
	 * @param t
	 *            the Tile instance to remove
	 * @return true if Tile is removed, false if not
	 */
	public boolean removeTile(Tile t) {
		Iterator<Square> si = col.iterator();
		while (si.hasNext()) {
			Square s = si.next();
			if (!s.isEmpty()) {
				if (s.getTile() == t) {
					s.setTile(null);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param idx
	 *            the index of the Tile to remove
	 * @return true if Tile
	 */
	public boolean removeTile(int idx) {
		Square s = getSquare(idx);
		if (s != null && !s.isEmpty()) {
			s.setTile(null);
			return true;
		}
		return false;
	}

	/**
	 * @param idx
	 *            the index
	 * @return the Square at idx, null if error
	 */
	public Square getSquare(int idx) {
		if (!col.isEmpty()) {
			if (idx >= 0 && idx < col.size()) {
				return col.get(idx);
			}
		}
		return null;
	}

	/**
	 * @return how many Square in this Column
	 */
	public int size() {
		return col.size();
	}

	/**
	 * @return how many Tile is in the column
	 */
	public int count() {
		int c = 0;
		Iterator<Square> si = col.iterator();
		while (si.hasNext()) {
			Square s = si.next();
			if (!s.isEmpty()) {
				c++;
			}
		}
		return c;
	}

	/** Remove every Tile in the Column */
	void clear() {
		Iterator<Square> si = col.iterator();
		while (si.hasNext()) {
			Square s = si.next();
			if (s.isEmpty()) {
				s.setTile(null);
			}
		}
	}

	/** Fill any empty Square with a new Tile */
	void fill() {
		Iterator<Square> si = col.iterator();
		while (si.hasNext()) {
			Square s = si.next();
			if (s.isEmpty() && s.isSelectable()) {
				s.setTile(TileFactory.getTile());
			}
		}
	}

	/**
	 * Move all Tile into one contiguous block so we can fill empty Square with
	 * new Tile
	 * 
	 * If we hit a non-selectable block, treat it as the bottom of the column
	 * 
	 * @param col
	 */
	void pack() {
		/*
		 * Find all selectable block, put them on a stack, clear the column,
		 * then put them back from the bottom
		 */
		int bottom = col.size() - 1;
		Stack<Tile> stack = new Stack<Tile>();
		for (int y = 0; y < col.size(); y++) {
			Square s = getSquare(y);
			if (!s.isSelectable()) {
				bottom = y - 1;
				break;
			} else if (!s.isEmpty()) {
				stack.push(s.getTile()); // If non-empty, push it on the stack
				s.setTile(null); // Clear the Tile
			}
		}
		// Fill the column from the bottom
		while (!stack.isEmpty()) {
			setTile(stack.pop(), bottom--);
		}
	}

}
