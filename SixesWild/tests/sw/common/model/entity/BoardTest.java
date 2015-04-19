package sw.common.model.entity;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw.common.system.manager.IBoardLocationManager;

public class BoardTest {

	Board board;
	IBoardLocationManager lm;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();  // Create new board and fill it
		lm    = (IBoardLocationManager) board;
		
		testBoardSize();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBoardSize() {
		// Make sure there are 81 Tiles in the grid
		assertEquals(9, board.grid.size());
		for (int i = 0; i < board.grid.size(); i++) {			
			assertEquals(9, board.grid.get(i).size());
		}		
	}

	@Test
	public void testGetTile() {
		// Get location of every Tile in the board, must not be null
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Tile t = board.getTile(new Point(x,y));
				assertNotNull(t);
				
				// Is this location correct?
				Tile t1 = board.grid.get(x).getTile(y);
				assertTrue(t.equals(t1));
				assertEquals(t, t1);
			}
		}		
	}

	@Test
	public void testGetTileLocation() {		
		// Pick a Tile from the grid, the return location has to be correct
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Tile t = board.grid.get(x).getTile(y);
				
				// it has to exist
				Point p = lm.getPoint(t);
				assertNotNull(String.format("Location %d,%d is null", x,y), p);
				assertEquals(String.format("Location %d,%d failed", x,y), x, p.x);
				assertEquals(String.format("Location %d,%d failed", x,y), y, p.y);
				
				// Create a "fake" tile with the same value and multiplier
				Tile fake = new Tile(t.value, t.multiplier);
				Point fp = lm.getPoint(fake);
				assertNull(fp); // This Tile should not exist in the Board
			}
		}
	}
	
	/** Remove a Tile from the Board, update location, Board returns null for getTileLocation */
	@Test
	public void testRemoveTile() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Tile t = board.grid.get(x).getTile(y);
				
				board.grid.get(x).getSquare(y).setTile(null);
				
				// Square is now empty, Board must return null
				Point p = board.getPoint(t);
				assertNull(String.format("Location %d,%d is not null", x,y), p);				
				
				// Create a "fake" tile with the same value and multiplier
				Tile fake = new Tile(t.value, t.multiplier);
				Point fp = board.getPoint(fake);
				assertNull(fp); // This Tile should not exist in the Board
			}
		}
	}

}
