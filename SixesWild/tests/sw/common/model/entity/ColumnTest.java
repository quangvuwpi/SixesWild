package sw.common.model.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sw.common.system.factory.SquareFactory;

public class ColumnTest {

	Column col;
	ArrayList<Square> sl = new ArrayList<Square>();	
	
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 5; i++) {
			sl.add(SquareFactory.getSquare(true));
		}
		
		col = new Column(sl);
		assertNotNull("Column is null!", col);
		assertEquals("Column length is not correct!", 5, col.count());
	}

	@After
	public void tearDown() throws Exception {
		col = null;
	}

	@Test
	public void testRemoveTileIdx() {
		for (int i = 0, count = 5; i < 5; i++, count--) {
			col.removeTile(i);
			
			// the Tile should be removed
			Tile t = col.getTile(i);
			assertNull(t);
			
			// count should have decreased by 1
			assertTrue((count - 1) == col.count());
		}
	}
	
	@Test
	public void testRemoveTileObj() {		
		for (int i = 0, count = 5; i < 5; i++, count--) {
			Tile t = col.col.get(i).getTile();
			
			col.removeTile(t);
			
			// the Tile should be removed
			while (col.col.iterator().hasNext()) {
				Square s = col.col.iterator().next();
				assertNotEquals("Tile was not removed!", t, s.getTile());
			}
			
			// count should have decreased by 1
			assertTrue((count - 1) == col.count());
		}
	}

	@Test
	public void testPack() {
		Tile t4 = col.getTile(4);
		Tile t3 = col.getTile(3);
		Tile t2 = col.getTile(2);
		Tile t1 = col.getTile(1);
		Tile t0 = col.getTile(0);		
		
		col.removeTile(t0);
		col.removeTile(t2);
		
		col.pack();
		assertNull(col.getTile(0));
		assertNull(col.getTile(1));
		assertEquals(t1, col.getTile(2));
		assertEquals(t3, col.getTile(3));
		assertEquals(t4, col.getTile(4));
		
	}

}
