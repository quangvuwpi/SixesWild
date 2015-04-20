package sw.builder.gui.layout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import sw.app.gui.view.board.BoardPanel;
import sw.common.model.controller.BoardController;
import sw.common.model.controller.IMode;
import sw.common.model.entity.Board;
import sw.common.model.entity.Game;
import sw.common.model.entity.Level;
import sw.common.model.entity.Statistics;
import sw.common.system.manager.CommonResourceManager;
import sw.common.system.manager.IResourceManager;

public class LevelBuilder extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 764185992103138774L;

	private JPanel contentPane;

	Board board;
	BoardPanel boardPanel;
	LevelBuilderTextPanel textPanel;

	/**
	 * Create the frame.
	 */
	public LevelBuilder() {
		initializePanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 525);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(208, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(btnPreview, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(125))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPreview, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	void initializePanel() {		
		
		IMode mode = new IMode() {			
			@Override
			public IResourceManager getResourceManger() {
				return new CommonResourceManager();
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
		
		textPanel = new LevelBuilderTextPanel(board);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		textPanel.updateBoard();
	}
}
