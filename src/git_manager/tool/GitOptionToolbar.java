package git_manager.tool;

import git_manager.constants.OptionBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

public class GitOptionToolbar extends JToolBar implements MouseInputListener {

	private static final long serialVersionUID = 1L;
	private final int space1 = 5;
	public int modeX2, modeX1, modeY1, modeY2;
	private int selectionCompX, selectionCompY;
	private SelectionMenu selectionMenu;

	public GitOptionToolbar() {
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 145);
		this.setVisible(true);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.black);
		populateToolBar();
		addMouseListener(this);
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

		selectionMenu = new SelectionMenu("Novice");
		this.add(selectionMenu);

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



			FontMetrics metrics = g.getFontMetrics(this.getFont());

			int modeTextWidth = metrics.stringWidth(menuTitle);
			int modeTextAscent = metrics.getHeight();
			modeX2 = getWidth() - 46;
			modeX1 = modeX2
					- (OptionBar.MODE_GAP_WIDTH + modeTextWidth + OptionBar.MODE_GAP_WIDTH
							+ OptionBar.ARROW_WIDTH + OptionBar.MODE_GAP_WIDTH);
			modeY1 = (getHeight() - 4 * OptionBar.MODE_BOX_HEIGHT) / 2;
			modeY2 = modeY1 + OptionBar.MODE_BOX_HEIGHT + modeTextAscent;
			// g.setColor(modeButtonColor);
			g.drawRect(modeX1, modeY1, modeX2 - modeX1, modeY2 - modeY1 - 1);
			g.drawString(menuTitle, modeX1 + OptionBar.MODE_GAP_WIDTH, modeY1
					+ (OptionBar.MODE_BOX_HEIGHT + modeTextAscent) / 2 + modeTextAscent / 3);
			g.drawImage(
					new ImageIcon((this.getClass()
							.getResource(OptionBar.MODE_MENU_ARROW)))
							.getImage(), modeX2 - OptionBar.ARROW_WIDTH
							- OptionBar.MODE_GAP_WIDTH, modeY1 + 1
							+ (modeY2 - modeY1 - 1 - OptionBar.ARROW_HEIGHT)
							/ 2, OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT,
					null);

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		 System.out.println("clicked x,y: "+x+" "+y);
		if (x > selectionCompX + modeX1 && x < selectionCompX + modeX2
				&& y > selectionCompY + modeY1 && y < selectionCompY + modeY2) {
			JPopupMenu popup = new JPopupMenu("Expertise Level");


			JRadioButtonMenuItem item = new JRadioButtonMenuItem("Novice");
			// doesn't need a listener, since it doesn't do anything
			item.setSelected(true);
			popup.add(item);

			JMenuItem item2 = new JMenuItem("Pro");
			item2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			popup.add(item2);
			popup.setVisible(true);
			popup.show(e.getComponent(), x, y);
			popup.requestFocus();
		}
	}

	public void setSelectionCompX(int selectionCompX) {
		this.selectionCompX = selectionCompX;
	}

	public void setSelectionCompY(int selectionCompY) {
		this.selectionCompY = selectionCompY;
	}

	public Point getSelectionMenuLocation() {
		return this.selectionMenu.getLocationOnScreen();
	}

	@Override
	public boolean isRollover() {
		return super.isRollover();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
