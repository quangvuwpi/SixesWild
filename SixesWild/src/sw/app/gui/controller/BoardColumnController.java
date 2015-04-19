/**
 * @file BoardColumnController.java
 * @date Apr 15, 2015 9:17:12 AM
 * @author Tony Vu (quangvu@wpi.edu)
 */
package sw.app.gui.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sw.app.gui.view.board.BoardColumn;
import sw.common.model.entity.Board;

/**
 *
 */
public class BoardColumnController extends MouseAdapter {

	BoardColumn col;
	Board       board;
	
	public BoardColumnController(BoardColumn bc, Board b) {
		// TODO Auto-generated constructor stub
		this.col = bc;
		this.board = b;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!col.isAnimating()) {  // If column is still moving, don't allow click
			try {
				int y = col.yToIdx(e.getY());
				board.remove(new Point(col.getColIdx(), y));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.err
						.println("Out of bound error in BoardColumnController::mouseClicked!");
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseDragged(e);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseEntered(e);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseExited(e);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
	}

	
}
