package git_manager.tool;

import git_manager.constants.OptionBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.border.Border;

public class GitOptionToolbar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final int space1 = 5;
	private int modeX2, modeX1, modeY1, modeY2;

	public GitOptionToolbar() {
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 145);
		this.setVisible(true);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.black);
		populateToolBar();
	}

	private void populateToolBar() {
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_INIT_ICON, OptionBar.ACTION_INIT));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_SNAP_ICON, OptionBar.ACTION_SNAP));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_DIFF_ICON, OptionBar.ACTION_DIFF));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_PUSH_ICON, OptionBar.ACTION_PUSH));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_REVERT_ICON, OptionBar.ACTION_REVERT));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_RM_ICON, OptionBar.ACTION_RM));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_STATUS_ICON, OptionBar.ACTION_STATUS));

		this.add(Box.createHorizontalGlue());
		// this.add(addButton(OptionBar.GIT_STATUS_ICON,
		// OptionBar.ACTION_STATUS));
		// drawModeSelectionMenu("Basic");
		this.add(new SelectionMenu("Novice"));
	}

	private JButton addButton(String imageLocation, String actionCommand) {
		JButton b = new JButton();
		// b.setToolTipText("Initialize repo");
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				imageLocation));
		b.setIcon(icon);
		b.setFocusPainted(false);
		b.setBackground(Color.black);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		b.setBorder(emptyBorder);
		return b;
	}

	@SuppressWarnings("serial")
	class SelectionMenu extends JPanel {

		String menuTitle;

		public SelectionMenu(String menuTitle) {
			setPreferredSize(new Dimension(20, 20));
			this.setOpaque(false);
			this.menuTitle = menuTitle;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setColor(new Color(255, 255, 255));
			g.setFont(this.getFont());

			// the following few pieces of code have been adapted from
			// Processing so as to resemble it

			final int modeGapWidth = 8;
			final int modeBoxHeight = 6;

			FontMetrics metrics = g.getFontMetrics(this.getFont());

			int modeTextWidth = metrics.stringWidth(menuTitle);
			int modeTextAscent = metrics.getHeight();
			modeX2 = getWidth() - 46;
			modeX1 = modeX2
					- (modeGapWidth + modeTextWidth + modeGapWidth
							+ OptionBar.ARROW_WIDTH + modeGapWidth);
			// modeY1 = 8; //(getHeight() - modeBoxHeight) / 2;
			modeY1 = (getHeight() - 4 * modeBoxHeight) / 2;
			modeY2 = modeY1 + modeBoxHeight + modeTextAscent; // modeY1 + modeH
																// + modeGapV*2;
			// g.setColor(modeButtonColor);
			g.drawRect(modeX1, modeY1, modeX2 - modeX1, modeY2 - modeY1 - 1);
			g.drawString(menuTitle, modeX1 + modeGapWidth, modeY1
					+ (modeBoxHeight + modeTextAscent) / 2 + modeTextAscent / 3);
			g.drawImage(
					new ImageIcon((this.getClass()
							.getResource(OptionBar.MODE_MENU_ARROW)))
							.getImage(), modeX2 - OptionBar.ARROW_WIDTH
							- modeGapWidth, modeY1 + 1
							+ (modeY2 - modeY1 - 1 - OptionBar.ARROW_HEIGHT)
							/ 2, OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT,
					null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		// ignore mouse presses so hitting 'run' twice doesn't cause problems
		if (isEnabled()) {
			int x = e.getX();
			int y = e.getY();
			if (x > modeX1 && x < modeX2 && y > modeY1 && y < modeY2) {
				JPopupMenu popup = new JPopupMenu("test");
				// popup.add(popup);
				popup.show(this, x, y);
			}
			/*
			 * // Need to reset the rollover here. If the window isn't active,
			 * // the rollover wouldn't have been updated. //
			 * http://code.google.com/p/processing/issues/detail?id=561
			 * checkRollover(x, y); if (rollover != null) {
			 * //handlePressed(rollover); handlePressed(e,
			 * buttons.indexOf(rollover)); }
			 */
		}
	}

	@Override
	public boolean isRollover() {
		// TODO Auto-generated method stub
		return super.isRollover();
	}

}
