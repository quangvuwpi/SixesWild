package sw.app.gui.view.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import sw.common.model.entity.Board;
import sw.common.model.entity.Column;
import sw.common.model.entity.Square;
import sw.common.model.entity.Tile;
import sw.common.system.manager.CommonResourceManager;
import sw.common.system.manager.IResourceManager;

public class BoardColumn extends JPanel {

	/** GENERATED DO NOT CHANGE */
	private static final long serialVersionUID = -7989466787176501882L;

	/** The index of this column */
	int colIdx;

	/** The column of Square to display */
	Column column;

	/** The dimension of the Tile icon */
	Dimension imgSize;

	/** Common icon image map */
	HashMap<String, Image> im;

	/** Resource manager */
	IResourceManager rm;

	/** Keep track of the XY coordinate of each Tile */
	ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();

	/** Whether the column is in animation */
	boolean isMoving = false;
	
	/** Whether to animate Tile falling down */
	boolean enableAnimation = true;

	/**
	 * @param boardPanel
	 * @param i
	 *            column index
	 */
	public BoardColumn(BoardPanel boardPanel, int i) {
		this.im = boardPanel.imageMap;

		// Any initialization that cannot handle repeat call can go here,
		// otherwise it will go to initialize
		initialize(boardPanel, i);
	}

	void initialize(BoardPanel boardPanel, int i) {
		this.colIdx = i;
		this.column = boardPanel.board.getColumn(i);

		this.rm = boardPanel.getResManager();
		this.imgSize = rm.getImageSize();

		// Clear current display
		clear();

		// Calculate size of this column
		Dimension size = new Dimension(imgSize.width, imgSize.height
				* column.size());
		setPreferredSize(size);

		// Load the images we need, will add in more as needed
		for (int y = 0; y < column.size(); y++) {
			Square s = column.getSquare(y); // better not be null here...
			if (s != null && !s.isEmpty()) {
				Tile t = s.getTile();
				String str = rm.getImage(t);
				if (!im.containsKey(str)) {
					im.put(str, loadTileImg(t)); // Guarantee to get an Image
													// here
				}
				BoardTile bt = new BoardTile(t);
				if (bt != null) { // There was a bug here before, so just to be
									// safe
					tiles.add(bt);
				}
			}
		}
	}

	/** Synch the tiles to be displayed with the underlying Column
	 * @return whether Tile position was updated
	 */
	public boolean updateTilePosition() {
		boolean updatePosition = false; // need a temp to prevent racing
										// condition

		// Update the current Tile positions
		for (int y = 0; y < tiles.size(); y++) {
			try {
				BoardTile bt = tiles.get(y);
				int idx = column.indexOf(bt.tile); // can handle null case
				bt.updateDestY(idxToY(idx)); // Update destination y-coordinate
				if (bt.updateCurrentY()) { // Update current y-coordinate
					updatePosition = true; // If any Tile position is updated,
											// we're in animation
				}
			} catch (IllegalAccessError | Exception e) {
				// Tile doesn't exist, it was removed from the column or index
				// out of bound, just remove it
				tiles.remove(y);
			}
		}
		// Add new Tiles, if any
		for (int y = 0; y < column.count(); y++) {
			Tile t = column.getTile(y); // Should not be null here...
			if (t != null && !tilesContains(t)) {
				BoardTile bt = new BoardTile(t);
				if (bt != null) {
					tiles.add(bt); // Add new tile, which doesn't need to be
									// moved
				}
			}
		}

		// Update BoardColumn animating state
		isMoving = updatePosition;
		
		return isMoving;
	}

	/** Synch the BoardColumn with the underlying Column
	 * @return true if Tile position was updated, false if not
	 */
	public boolean update() {
		return updateTilePosition();
	}

	/** Clear the column display */
	public void clear() {
		// TODO Auto-generated method stub
		tiles.clear();
	}

	/**
	 * Paint the Squares in the column, can be upgraded to use Image
	 * 
	 * @throws Exception
	 */
	void paintSquares(Graphics g) throws Exception {
		for (int y = 0; y < column.size(); y++) {
			Square s = column.getSquare(y);
			if (s.isSelected()) {
				g.setColor(Color.YELLOW);
				g.fillRect(0, idxToY(y), imgSize.width, imgSize.height);
			} else {
				g.setColor(Color.BLACK);
				g.drawRect(0, idxToY(y), imgSize.width, imgSize.height);
			}
		}
	}

	/** Paint the Tiles in the column */
	void paintTiles(Graphics g) {
		//for (int y = 0; y < tiles.size(); y++) {
		for (int y = tiles.size() - 1; y >= 0; y--) {
			BoardTile bt = tiles.get(y);
			if (bt != null && bt.isVisible()) {
				g.drawImage(loadTileImg(bt.tile), 0, bt.currentY, null);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			paintSquares(g);
		} catch (Exception e) {
			// Should never throw an exception here
			System.err.println("False exception occured!");
		}
		paintTiles(g);
	}

	/**
	 * @param t
	 *            tile to load the Image for
	 * @return the Image to be drawn for the Tile
	 */
	Image loadTileImg(Tile t) {
		Image img;
		CommonResourceManager crm = new CommonResourceManager();

		// Tries to load the image from IResourceManager, if that doesn't work
		// then use common image
		String path = rm.getImage(t);
		if (!im.containsKey(path)) {
			path = crm.getImage(t);
			img = new ImageIcon(BoardColumn.class.getResource(path)).getImage();
			if (img == null) {
				img = new ImageIcon(BoardColumn.class.getResource(crm
						.getImage(t))).getImage();
			}
			im.put(crm.getImage(t), img); // Store the image
		} else {
			img = im.get(path);
		}
		return img;		
	}

	/**
	 * @return the index of this BoardColumn
	 */
	public int getColIdx() {
		return colIdx;
	}

	/**
	 * Convert index to y-coordinate, top-aligned
	 * 
	 * @throws Exception
	 */
	public int idxToY(int idx) throws Exception {
		if (idx >= 0 && idx < column.size()) {
			return idx * imgSize.height;
		}
		throw new Exception("Index out of bound!");
	}

	/**
	 * Convert y-coordinate to index
	 * 
	 * @throws Exception
	 */
	public int yToIdx(int y) throws Exception {
		for (int i = 0; i < column.size(); i++) {
			int yPos = idxToY(i);
			if (y >= yPos && y < yPos + imgSize.height) {
				return i;
			}
		}
		throw new Exception("Selection out of bound!");
	}

	/**
	 * @return true if Tiles are still moving to final destination, false if
	 *         they're already there
	 */
	synchronized public boolean isAnimating() {
		if (enableAnimation) {
			return isMoving;
		} else {
			return false;
		}
	}

	/**
	 * @param idx
	 * @return an iterator of all occupant in this index
	 */
	Iterator<BoardTile> getOccupant(int idx) {
		ArrayList<BoardTile> abt = new ArrayList<BoardTile>();
		try {
			Iterator<BoardTile> bti = tiles.iterator();
			while (bti.hasNext()) {
				BoardTile bt = bti.next();
				if (yToIdx(bt.currentY) == idx) {
					abt.add(bt);
				}
			}
		} catch (Exception e) {

		}
		return abt.iterator();
	}

	/**
	 * @param idx
	 * @return true if idx is occupied, false if not
	 */
	boolean isIdxOccupied(int idx) {
		Iterator<BoardTile> bti = getOccupant(idx);
		return bti.hasNext();
	}

	/**
	 * @param t
	 *            the Tile
	 * @return whether the Tile is in the display list
	 */
	boolean tilesContains(Tile t) {
		Iterator<BoardTile> bti = tiles.iterator();
		while (bti.hasNext()) {
			if (bti.next().tile == t) {
				return true;
			}
		}
		return false;
	}
	
	void enableAnimation() {
		this.enableAnimation = true;
	}
	
	void disableAnimation() {
		this.enableAnimation = false;
	}

	/**
	 * Internal class to keep track of the current and destination y-coordinate
	 * of the Tile
	 */
	class BoardTile {
		int currentY; // Current y position
		int destY; // Destination y position
		Tile tile; // The tile to keep track of

		BoardTile(Tile t) {
			this.tile = t;
			try {
				this.destY = idxToY(column.indexOf(t));
			} catch (Exception e) { // Should not throw exception here
				System.err.println("False exception occured!");
			}
			if (enableAnimation) {
				this.currentY = 0;
			} else {
				this.currentY = this.destY;
			}
		}

		void updateDestY(int newY) {
			this.destY = newY;
		}

		boolean updateCurrentY() {
			if (enableAnimation) {
				if (currentY < destY) {
					currentY += 3;
					return true;
				} else if (currentY > destY) { // for swap move
					currentY = destY;
				}
				return false;
			} else {
				if (currentY != destY) {
					currentY = destY;
					return true;
				}
				return false;
			}
		}

		boolean isVisible() {
			if (enableAnimation) {
				try {
					int hiIdx = 0;
					int idx = yToIdx(currentY);
					Iterator<BoardTile> bti = getOccupant(idx);
					while (bti.hasNext()) {					
						BoardTile o = bti.next();
						hiIdx = Math.max(tiles.indexOf(o), hiIdx);
					}
					return tiles.indexOf(this) == hiIdx;
				} catch (Exception e) {
					return false;
				}
			}
			return true;
		}
	}

}
