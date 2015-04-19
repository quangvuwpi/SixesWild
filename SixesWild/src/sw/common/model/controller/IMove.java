/**
 * @file IMove.java
 * @date Apr 16, 2015 2:50:12 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.model.controller;

/** The interface for a Move */
public interface IMove {
	
	/**
	 * @return true if move was performed, false if not
	 */
	boolean doMove();
	
	/**
	 * @return true if move was undo, false if not
	 */
	boolean undoMove();
	
}
