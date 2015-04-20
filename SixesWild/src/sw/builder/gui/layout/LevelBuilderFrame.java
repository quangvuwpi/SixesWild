package sw.builder.gui.layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import sw.common.model.entity.Tile;
import sw.common.system.manager.CommonResourceManager;
import sw.common.system.manager.IResourceManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LevelBuilderFrame extends JFrame implements ActionListener {

	private JPanel contentPane;	

	Board board;
	BoardPanel boardPanel;
	LevelBuilderPanel lvlPanel;
	
	JButton btnSave;
	JButton btnLoad;
	JButton btnRand;
	
	/**
	 * Create the frame.
	 */
	public LevelBuilderFrame() {
		initialize();
		
		setSize(new Dimension(900, 500));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(900, 500));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(25)
							.addComponent(btnRand, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnLoad, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lvlPanel, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(386, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lvlPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnLoad)
								.addComponent(btnRand))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);		
	}

	private void initialize() {
		IMode mode = new IMode() {			
			@Override
			public IResourceManager getResourceManger() {
				return new LevelBuilderResourceManager();
			}
			
			@Override
			public BoardController getBoardController() {
				return null;
			}
		};
		
		Level lvl = new Level(1, new Game(), new Statistics(), mode);
		
		board     = lvl.getGame().getBoard();
		
		boardPanel = new BoardPanel();		
		boardPanel.setSize(new Dimension(450, 450));
		
		boardPanel.setLevel(lvl);
		boardPanel.disableAnimation();
		boardPanel.initialize();		
		
		lvlPanel  = new LevelBuilderPanel(board);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(this);
		
		btnRand = new JButton("Randomize");
		btnRand.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRand) {
			board.clear();
			board.fill();
			lvlPanel.update();
		} else if (e.getSource() == btnSave) {
			
		} else if (e.getSource() == btnLoad) {
			
		}		
	}
	
	private class LevelBuilderResourceManager extends CommonResourceManager {
		
		@Override
		protected String getTileImage(int i) {
			return imagePath.concat(String.format("%d_35x35.png", i));
		}

		/* (non-Javadoc)
		 * @see sw.common.system.manager.CommonResourceManager#getImageSize()
		 */
		@Override
		public Dimension getImageSize() {
			return new Dimension(35, 35);
		}	
		
	}
}
