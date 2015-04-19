package sw.common.model.entity;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sw.common.model.entity.Tile;
import org.junit.Test;

public class TileTest {

	@Test
	public void testNewTile() {
		Tile t1 = new Tile(6,3);
		Tile t2 = new Tile(2,2);
		assertNotNull(t1);
		assertNotNull(t2);
		
		assertEquals(6, t1.getValue());
		assertEquals(3, t1.getMultiplier());
		
		assertEquals(2, t2.getValue());
		assertEquals(2, t2.getMultiplier());
		
		assertTrue(t1.equals(new Tile(6,3)));
		assertFalse(t2.equals(t1));
		
		try {
			Tile fake = new Tile(7,1);
			fail("Should not reach here, should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test
	public void testTileToString() {
		Tile t = new Tile(4,2);
		String s = t.toString();
		assertNotNull(s);
		assertTrue(s.equals("Value : 4, Multiplier : 2\n"));
	}
}
