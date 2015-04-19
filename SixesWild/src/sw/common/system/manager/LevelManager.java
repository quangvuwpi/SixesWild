/**
 * @file LevelManager.java
 * @date Apr 12, 2015 12:51:53 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.system.manager;

import java.util.ArrayList;

import sw.common.model.entity.Game;
import sw.common.model.entity.Level;
import sw.common.model.entity.Statistics;
import sw.common.system.factory.LevelFactory;
import sw.mode.Elimination;
import sw.mode.Lightning;
import sw.mode.Puzzle;
import sw.mode.Release;

/**
 *
 */
public class LevelManager {
	
	int lvlCount = 0;
	int maxLvl   = 5;
	
	ArrayList<Level> list = new ArrayList<Level>();
	
	Level current;
	
	public LevelManager() {
		// Test create levels
//		for (int i = 0; i < maxLvl; i++) {
//			list.add(LevelFactory.getLevel(++lvlCount, new Release() , new Statistics()));
//		}
		list.add(LevelFactory.getLevel(++lvlCount, new Puzzle() , new Statistics()));
		list.add(LevelFactory.getLevel(++lvlCount, new Release() , new Statistics()));
		list.add(LevelFactory.getLevel(++lvlCount, new Lightning() , new Statistics()));
		list.add(LevelFactory.getLevel(++lvlCount, new Elimination() , new Statistics()));
		list.add(LevelFactory.getLevel(++lvlCount, new Puzzle() , new Statistics()));
		list.add(LevelFactory.getLevel(++lvlCount, new Release() , new Statistics()));
		list.add(LevelFactory.getLevel(++lvlCount, new Lightning() , new Statistics()));
		
		// first level is the current level by default
		current = list.get(0);
	}
	
	public Level getCurrent() {
		return current;
	}
	
	public Level getNext() {
		int i = list.indexOf(current);
		if (i < list.size() - 1) {
			return list.get(i+1);			
		} else {
			return null;
		}
	}
	
	public ArrayList<Level> getLevels() {
		return list;
	}
	
	public void setCurrent(Level level) {
		current = level;
	}

}
