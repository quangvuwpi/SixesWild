package sw.app.gui.view.board;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import javax.swing.LayoutStyle.ComponentPlacement;

import sw.common.model.controller.PwrUpRemoveActionListener;
import sw.common.model.controller.PwrUpShuffleActionListener;
import sw.common.model.controller.PwrUpSwapActionListener;

public class PowerUpPanel extends JPanel {
	public PowerUpPanel() {
		setOpaque(false);
		setBorder(null);
		
		JButton btnSwap = new JButton("Swap");
		btnSwap.addActionListener(new PwrUpSwapActionListener());
		
		JButton btnShuffle = new JButton("Shuffle");
		btnShuffle.addActionListener(new PwrUpShuffleActionListener());
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new PwrUpRemoveActionListener());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnShuffle, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSwap, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(400, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnSwap, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnShuffle, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9018711320856474573L;
}
