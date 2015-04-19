/**
 *  @file   SquareFactory.java
 *  @author Tony Vu
 *  @since  Apr 12, 2015
 */
package sw.common.system.factory;

import sw.common.model.entity.Square;

/** Factory class for creating Square */
public class SquareFactory {

	/** Get a new Square with randomly generated value
	 * @return the new Square
	 */
	public static Square getSquare(boolean fill) {
		if (fill) {
			return new Square(TileFactory.getTile());
		} else {
			return getEmptySquare();
		}
	}
	
	/** Get a new Square with no Tile
	 * @return the new Square
	 */
	public static Square getEmptySquare() {
		return new Square();
	}
}
