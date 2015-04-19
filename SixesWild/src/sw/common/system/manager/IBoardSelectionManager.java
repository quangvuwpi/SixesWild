/**
 * @file IBoardSelectionManager.java
 * @date Apr 16, 2015 1:38:24 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.system.manager;

import java.awt.Point;
import java.util.Queue;

import sw.common.model.entity.Square;
import sw.common.model.entity.Tile;

/** Interface for selecting Tiles on a Board */
public interface IBoardSelectionManager {
	
	/**
	 * @param p Point to select
	 * @return true if Square at p is selected, false if not
	 */
	boolean select(Point p);
	
	/**
	 * @return the queue of selected Tiles
	 */
	Queue<Tile> getSelectedTile();
	
	/**
	 * @return the queue of selected Squares
	 */
	Queue<Square> getSelectedSquare();
	
	/** 
	 * @return true if selection was cleared, false if not
	 */
	boolean clearSelection();

}
