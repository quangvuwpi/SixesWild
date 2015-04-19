/**
 *  @file   Square.java
 *  @author Tony Vu
 *  @since  Apr 11, 2015
 */
package sw.common.model.entity;

/** The model for an arbitrary position in the Board */
public class Square {
	
	/** The current tile */
	Tile tile = null;
	
	/** The number of time this Square has been
	 * used in a valid move
	 */
	int useCount = 0;
	
	/** Current select status - selected in a potential move */
	boolean selected = false;
	
	/** Whether this Square can be selected in a move */
	boolean selectable = true;

	/** Constructors */
	public Square() {}
	
	public Square(Tile tile) {
		this.tile = tile;
	}
	
	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	/**
	 * @return the useCount
	 */
	public int getUseCount() {
		return useCount;
	}

	/**
	 * @param useCount the useCount to set
	 */
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the selectable
	 */
	public boolean isSelectable() {
		return selectable;
	}

	/**
	 * @param selectable the selectable to set
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 0; // returns the same number so method equals is called by HashMap
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square s = (Square) obj;
			return (tile.value == s.tile.value) &&
					(tile.multiplier == s.tile.multiplier) &&
					(isUsed() == s.isUsed()) &&
					(isSelected() == s.isSelected()) &&
					(isSelectable() == s.isSelectable());					
		}
		return false;
	}
	
	/**
	 * @return whether this Square has been used in a valid move
	 */
	public boolean isUsed() {
		return useCount > 0;
	}
	
	/**
	 * @return whether this Square has a Tile associated with it
	 */
	public boolean isEmpty() {
		return tile == null;
	}

}
