package sw.common.model.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {

	@Test
	public void squareTest() {
		Tile tile= new Tile(4,2);
		Tile newTile = new Tile(5,1);
		Square square = new Square(tile);
		
		assertEquals(square, new Square(tile));
		assertEquals(square.getTile(), tile);
		assertEquals(square.getUseCount(), 0);
		assertEquals(square.isSelectable(), true);
		assertEquals(square.isSelected(), false);
		assertEquals(square.isEmpty(), false);
		square.setTile(newTile);
		assertEquals(square.getTile(), newTile);
	}

}
