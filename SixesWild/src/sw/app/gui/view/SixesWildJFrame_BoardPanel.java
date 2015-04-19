package sw.app.gui.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;

import sw.common.system.manager.LevelManager;

public class SixesWildJFrame_BoardPanel extends JFrame implements ActionListener {
	/** GENERATED DO NOT CHANGE */
	private static final long serialVersionUID = -3162315874662880626L;

	private LayoutManager layoutManager;
	private LevelManager levelManager;
	
	Dimension size = new Dimension(800, 600);
	GameplayView panel;
	
	public SixesWildJFrame_BoardPanel() {
		getContentPane().setMinimumSize(new Dimension(685, 564));
		layoutManager = new LayoutManager(this);
		levelManager = new LevelManager();
		
		panel = new GameplayView(layoutManager, levelManager);
		
		setResizable(false);
		setTitle("Sixes Wild");
		setPreferredSize(panel.getPreferredSize());
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		panel.initialize();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
