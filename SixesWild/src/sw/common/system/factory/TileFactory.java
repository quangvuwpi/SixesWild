/**
 *  @file   TileFrequencyFactory.java
 *  @author Tony Vu
 *  @since  Apr 11, 2015
 */
package sw.common.system.factory;

import sw.common.model.entity.Tile;

/** Creates Tiles depends on the TileFrequency */
public class TileFactory {
	
	/** Probability of each value and multiplier to appear in the game */
	static TileFrequency frequencies = new TileFrequency();	
	
	/**
	 * @param freq the new frequencies
	 */
	static public void setFreq(TileFrequency freq) {
		TileFactory.frequencies = freq;
	}
	
	/**
	 * @return a Tile generated based on the frequencies
	 */
	static public Tile getTile() {		
		return new Tile(frequencies.nextVal(), frequencies.nextMul());
	}
	
	/**
	 * @param t the Tile
	 * @return a new Tile with the same value and multiplier as t
	 */
	static public Tile getTile(Tile t) {
		return new Tile(t.getValue(), t.getMultiplier());
	}
	
	/**
	 * @param val value
	 * @param mul multiplier
	 * @return new Tile with specified value and multiplier, or null if input is invalid
	 */
	static public Tile getTile(int val, int mul) {
		try {
			return new Tile(val, mul);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
