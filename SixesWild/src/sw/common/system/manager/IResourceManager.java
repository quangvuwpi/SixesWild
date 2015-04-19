/**
 *  @file   IResourceManager.java
 *  @author Tony Vu
 *  @since  Apr 11, 2015
 */
package sw.common.system.manager;

import java.awt.Dimension;
import java.util.HashMap;

import sw.common.model.entity.Square;
import sw.common.model.entity.Tile;


/** Every game mode will need to get its own resource manager */
public interface IResourceManager {
	
	/** Get the image file path for every possible Tile
	 * @return the hashmap with Tile as key
	 */
	HashMap<Tile, String> getTileImage();
	
	/** Get the image file path for every possible Square
	 * @return the hashmap with Square as key
	 */
	HashMap<Square, String> getSquareImage();
	
	/** Get image file path for a particular Square */
	String getImage(Square square);
	
	/** Get image file path for a particular Tile */
	String getImage(Tile tile);
	
	/**
	 * @return size of the common icon square image
	 */
	Dimension getImageSize();
	
	/**
	 * @return image file path for star image
	 */
	String getStarImage();
}
