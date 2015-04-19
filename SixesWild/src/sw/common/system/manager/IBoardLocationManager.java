/**
 * @file IBoardLocationManager.java
 * @date Apr 16, 2015 1:59:20 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.system.manager;

import java.awt.Dimension;
import java.awt.Point;

import sw.common.model.entity.Square;
import sw.common.model.entity.Tile;

/** Interface for working with location on a Board */
public interface IBoardLocationManager {
	
	/**
	 * @param x the x-position to check
	 * @return true if valid, false if not
	 */
	boolean isValidX(int x);
	
	/**
	 * @param y the y-position to check
	 * @return true if valid, false if not
	 */
	boolean isValidY(int y);
	
	/**
	 * @param p the Point to check
	 * @return true if valid, false if not
	 */
	boolean isValidPoint(Point p);
	
	/**
	 * @param tile a particular instance of Tile
	 * @return the location of tile, null if not found
	 */
	Point getPoint(Tile tile);

	/**
	 * @param p the location
	 * @return the Tile at the location
	 */
	Tile getTile(Point p);
	
	/**
	 * @param p the location
	 * @return the Square at the location
	 */
	Square getSquare(Point p);
	
	/**
	 * @return the dimension of the Board
	 */
	Dimension size();
}
