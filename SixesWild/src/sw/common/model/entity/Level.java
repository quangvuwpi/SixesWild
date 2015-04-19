/**
 * @file Level.java
 * @date Apr 11, 2015 4:38:38 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.model.entity;

import sw.common.model.controller.IMode;

/** The model for an arbitrary game level */
public class Level {
	
	Game game;
	Statistics winStats;
	IMode mode;
	int levelNum;
	
	public Level(int levelNum, Game game, Statistics winStats, IMode mode) {
		this.game = game;
		this.winStats = winStats;
		this.mode = mode;
		this.levelNum = levelNum;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @return the winStats
	 */
	public Statistics getWinStats() {
		return winStats;
	}

	/**
	 * @return the mode
	 */
	public IMode getMode() {
		return mode;
	}
	
	public int getStars() {
		return 1;
	}
	
	public int getLevelNum() {
		return levelNum;
	}
	
	public void initialize() {
		
	}
	
	public boolean hasWon() {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mode.toString().concat(" ".concat(Integer.toString(levelNum)));
	}
	
}
