package sw.common.model.entity;

import java.awt.Dimension;
import java.awt.Point;

public interface IBoard {
	
	/**
	 * @param p the location
	 * @return the Tile at the location
	 */
	Tile getTile(Point p);
	
	/**
	 * @param p the position
	 * @return true if the Square at the position is empty; false if not
	 */
	boolean isEmpty(Point p);
	
	/**
	 * @param p the position to remove
	 * @return true if the Square at the position is empty; false if not
	 */
	boolean remove(Point p);
	
	/**
	 * @param p the position to replace
	 * @param t the new Tile to put into the position, remove any Tile currently occupying the Square
	 * @return true if the new Tile was placed into the Square at the position; false if not
	 */
	boolean replace(Point p, Tile t);

	/** Remove all Tile in the board */
	void clear();

	/** If there is unused space in each column, create new Tile to fill it */
	void fill();

	/** Compact Tiles into one contiguous block on each column */
	void pack();
	
	/**
	 * @return how may Tiles are currently in the Board
	 */
	int count();
	
	/**
	 * @return the dimension of the Board
	 */
	Dimension size();
}