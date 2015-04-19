/**
 * @file CommonResourceManager.java
 * @date Apr 11, 2015 5:11:30 PM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.common.system.manager;

import java.awt.Dimension;
import java.util.HashMap;

import sw.common.model.entity.Square;
import sw.common.model.entity.Tile;
import sw.common.system.factory.TileFactory;

/**
 *
 */
public class CommonResourceManager implements IResourceManager {
	
	HashMap<Tile, String> tileMap = new HashMap<Tile, String>();
	
	Dimension imgSize = new Dimension(50,50);
	
	String imagePath = "/sw/resource/image/";

	/* (non-Javadoc)
	 * @see sw.common.system.manager.IResourceManager#getImage()
	 */
	@Override
	public HashMap<Tile, String> getTileImage() {
		for (int i = Tile.minValue; i <= Tile.maxValue; i++) {
			for (int m = Tile.minMultiplier; m <= Tile.maxMultiplier; m++) {
				Tile t = TileFactory.getTile(i, m);
				if (!tileMap.containsKey(t)) {
					tileMap.put(t, getImage(t));
				}
			}
		}
		return tileMap;
	}

	@Override
	public String getImage(Square square) {
		return getUnusedImage(square.getTile().getValue());		
	}
	
	public String getImage(Tile tile) {
		return getTileImage(tile.getValue());
	}
	
	String getUnusedImage(int i) {
		return imagePath.concat(String.format("%d_unused.png", i));
	}
	
	String getTileImage(int i) {
		return imagePath.concat(String.format("tile_%d.png", i));
	}

	/* (non-Javadoc)
	 * @see sw.common.system.manager.IResourceManager#getImageSize()
	 */
	@Override
	public Dimension getImageSize() {
		return imgSize;
	}
	
	public String getStarImage() {
		return imagePath.concat("star.png");
	}

	@Override
	public HashMap<Square, String> getSquareImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
