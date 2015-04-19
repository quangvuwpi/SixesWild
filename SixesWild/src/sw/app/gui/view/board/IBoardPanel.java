package sw.app.gui.view.board;

import java.awt.Point;

import sw.app.gui.view.IView;
import sw.common.model.controller.BoardController;
import sw.common.model.entity.Board;
import sw.common.model.entity.IBoard;
import sw.common.model.entity.Level;
import sw.common.system.manager.IBoardLocationManager;
import sw.common.system.manager.IBoardSelectionManager;

/** Interface for BoardPanel */
public interface IBoardPanel extends IView {

	/**
	 * @param level to display
	 */
	void setLevel(Level level);
	
	/** Disable animation */
	void disableAnimation();
	
	/** Enable animation */
	void enableAnimation();

	/**
	 * @param bc the new board controller
	 */
	void setBoardController(BoardController bc);
	
	/** Clear display */
	void clear();

	/**
	 * @return the Board interface
	 */
	IBoard getBoard();
	
	/**
	 * @return the Board location manager interface
	 */
	IBoardLocationManager getLocator();
	
	/**
	 * @return the Board selection manager interface
	 */
	IBoardSelectionManager getSelector();

	/**
	 * @param point the XY coordinate of the panel
	 * @return the board position
	 */
	Point xyToPoint(Point point);

	/**
	 * @return whether the board is in animation
	 */
	boolean isAnimating();
	
	/**
	 * @param colIdx the index of the Column
	 * @return whether the Column is in animation
	 */
	boolean isAnimating(int colIdx);
}