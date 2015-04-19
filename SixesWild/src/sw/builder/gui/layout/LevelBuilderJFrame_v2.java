package sw.builder.gui.layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import sw.app.gui.view.board.BoardPanel;
import sw.common.model.controller.BoardController;
import sw.common.model.controller.IMode;
import sw.common.model.entity.Board;
import sw.common.model.entity.Game;
import sw.common.model.entity.Level;
import sw.common.model.entity.Statistics;
import sw.common.system.manager.CommonResourceManager;
import sw.common.system.manager.IResourceManager;
import sw.mode.Release;

import java.awt.Dimension;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class LevelBuilderJFrame_v2 extends JFrame {

	private JPanel contentPane;
	
	Timer timer;
	
	Board      board;
	BoardPanel boardPanel;
	LevelBuilderTextPanel textPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelBuilderJFrame_v2 frame = new LevelBuilderJFrame_v2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LevelBuilderJFrame_v2() {
		
		final BoardController bc = new BoardController() {

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// Do nothing
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
			
		};
		
		IMode mode = new IMode() {
			
			@Override
			public IResourceManager getResourceManger() {
				return new CommonResourceManager();
			}
			
			@Override
			public BoardController getBoardController() {
				return bc;
			}
		};
		
		setSize(new Dimension(900, 500));
		setResizable(false);	
		
		Level lvl = new Level(1, new Game(), new Statistics(), mode);
		
		board     = lvl.getGame().getBoard();
		
		boardPanel = new BoardPanel();
		//boardPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		boardPanel.setSize(new Dimension(450, 450));
		
		boardPanel.setLevel(lvl);
		boardPanel.disableAnimation();
		boardPanel.initialize();		
		
		textPanel = new LevelBuilderTextPanel(lvl.getGame().getBoard());
		
		JButton previewBtn = new JButton("Preview");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(129, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(150)
							.addComponent(previewBtn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(165))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(previewBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
