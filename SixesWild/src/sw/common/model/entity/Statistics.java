/**
 *  @file   Statistics.java
 *  @author Tony Vu
 *  @since  Apr 11, 2015
 */
package sw.common.model.entity;

import java.sql.Time;

/**
 * 
 */
public class Statistics {
	
	Time current = Time.valueOf("00:00:00");
	int numMoves = 0;
	int score = 0;
	
	/**
	 * @return the current
	 */
	public Time getCurrent() {
		return current;
	}
	
	/**
	 * @return the numMoves
	 */
	public int getNumMoves() {
		return numMoves;
	}
	
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}	
	
}
