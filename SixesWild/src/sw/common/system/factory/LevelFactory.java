/**
 * @file LevelFactory.java
 * @date Apr 14, 2015 8:39:04 AM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.system.factory;

import sw.common.model.controller.IMode;
import sw.common.model.entity.Game;
import sw.common.model.entity.Level;
import sw.common.model.entity.Statistics;

/**
 *
 */
public class LevelFactory {

	public static Level getLevel(int lvl, IMode mode, Statistics winStats) {
		return new Level(lvl, new Game(), winStats, mode, new TileFrequency());
	}
}
