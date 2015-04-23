/**
 *  @file   BoardPanelController.java
 *  @author Tony Vu
 *  @since  Apr 16, 2015
 */
package sw.common.model.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Queue;

import sw.app.gui.view.board.IBoardPanel;
import sw.common.model.entity.IBoard;
import sw.common.model.entity.Square;
import sw.common.model.entity.Tile;
import sw.common.system.manager.IBoardLocationManager;
import sw.common.system.manager.IBoardSelectionManager;

/** Model for an abstract BoardController */
public abstract class BoardController extends MouseAdapter {
	
	IBoardPanel            panel    = null;
	IBoard                 board    = null;
	IBoardLocationManager  locator  = null;
	IBoardSelectionManager selector = null;
	
	public BoardController(){}
	
	public BoardController(IBoardPanel bp) {
		initialize(bp);
	}
	
	public void initialize(IBoardPanel bp) {
		this.panel    = bp;
		this.board    = bp.getBoard();
		this.locator  = bp.getLocator();
		this.selector = bp.getSelector();
	}
	
	protected boolean select(Point p) {
		return selector.select(p);
	}
	
	protected Point getPoint(MouseEvent e) {
		return panel.xyToPoint(e.getPoint());
	}

	protected Queue<Square> getSelectedSquare() {
		return selector.getSelectedSquare();
	}
	
	protected Queue<Tile> getSelectedTile() {
		return selector.getSelectedTile();
	}

	protected boolean clearSelection() {
		return selector.clearSelection();
	}
	
	protected void removeSelection() {
		Iterator<Tile> ti = getSelectedTile().iterator();
		while (ti.hasNext()) {
			boardRemove(getPoint(ti.next()));
		}
	}

	protected boolean isValidX(int x) {
		return locator.isValidX(x);
	}

	protected boolean isValidY(int y) {
		return locator.isValidY(y);
	}

	protected boolean isValidPoint(Point p) {
		return locator.isValidPoint(p);
	}

	protected Point getPoint(Tile tile) {
		return locator.getPoint(tile);
	}

	protected Tile getTile(Point p) {
		return locator.getTile(p);
	}

	protected Square getSquare(Point p) {
		return locator.getSquare(p);
	}

	protected Dimension boardSize() {
		return board.size();
	}

	protected boolean isEmpty(Point p) {
		return board.isEmpty(p);
	}

	protected boolean boardRemove(Point p) {
		return board.remove(p);
	}

	protected boolean boardReplace(Point p, Tile t) {
		return board.replace(p, t);
	}

	protected void boardClear() {
		board.clear();
	}

	protected void boardFill() {
		board.fill();		
	}

	protected void boardPack() {
		board.pack();
	}

	protected int boardCount() {
		return board.count();
	}	
	
}
