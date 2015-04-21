/**
 * @file IMode.java
 * @date Apr 11, 2015 4:40:30 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.model.controller;

import sw.common.system.manager.IBoardLocationManager;
import sw.common.system.manager.IBoardSelectionManager;
import sw.common.system.manager.IResourceManager;

/** Interface for every game mode */
public interface IMode {
	
	static enum MODE {
		PUZZLE,
		LIGHTNING,
		ELIMINATION,
		RELEASE
	}

	IResourceManager getResourceManger();
	
	BoardController getBoardController();
	
}
