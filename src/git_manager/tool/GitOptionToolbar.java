package git_manager.tool;

import git_manager.constants.OptionBar;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

public class GitOptionToolbar extends JToolBar implements MouseInputListener {

	private static final long serialVersionUID = 1L;
	private final int space1 = 5;
	// Expertise Selection Menu variables
	private int elmX2, elmX1, elmY1, elmY2, elmTextWidth, elmTextAscent;
	// ArrayList<PointCoordinates> points;
	ArrayList<JButton> buttons;

	// Repo Selection Menu variables
	private int rsmX2, rsmX1, rsmY1, rsmY2;
	private JLabel buttonDescription;

	public GitOptionToolbar() {
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 145);
		this.setVisible(true);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.black);

		buttonDescription = new JLabel();
		buttonDescription.setText("");
		buttonDescription.setForeground(Color.WHITE);
		// points = new ArrayList<PointCoordinates>();
		buttons = new ArrayList<JButton>();

		populateToolBar();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private void populateToolBar() {
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_INIT_ICON, OptionBar.ACTION_INIT,
				OptionBar.DESCRIP_INIT));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_SNAP_ICON, OptionBar.ACTION_SNAP,
				OptionBar.DESCRIP_SNAP));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_DIFF_ICON, OptionBar.ACTION_DIFF,
				OptionBar.DESCRIP_DIFF));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_PUSH_ICON, OptionBar.ACTION_PUSH,
				OptionBar.DESCRIP_PUSH));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_REVERT_ICON, OptionBar.ACTION_REVERT,
				OptionBar.DESCRIP_REVERT));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_RM_ICON, OptionBar.ACTION_RM,
				OptionBar.DESCRIP_RM));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_STATUS_ICON, OptionBar.ACTION_STATUS,
				OptionBar.DESCRIP_STATUS));
		this.add(Box.createHorizontalStrut(space1));
		this.add(Box.createHorizontalStrut(space1));
		this.add(buttonDescription);

		this.add(Box.createHorizontalGlue());

	}

	private JButton addButton(String imageLocation, String actionCommand,
			String buttonName) {
		JButton b = new JButton();
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				imageLocation));
		// b.setLayout(new BorderLayout());
		// b.setIcon(icon);
		// JLabel label = new JLabel(icon);
		// b.add(label);
		b.setIcon(icon);
		// b.setIconTextGap(0);
		// b.setMargin(new Insets(0, 0, 0, 0));
		// b.setSize(d);
		b.setFocusPainted(false);
		b.setBackground(Color.black);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		b.setBorder(emptyBorder);
		b.setName(buttonName);
		b.setActionCommand(actionCommand);
		b.addMouseListener(this);
		b.addMouseMotionListener(this);

		buttons.add(b);

		return b;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		initializeDimensions(g);
		renderRepoSelectionMenu(g);
		renderExpertiseLevelMenu(g);

	}

	// ALWAYS call renderExpertiseLevelMenu() before renderRepoSelectionMenu()

	void renderExpertiseLevelMenu(Graphics g) {

		g.setColor(new Color(255, 255, 255));
		g.setFont(this.getFont());

		// the following few pieces of code have been adapted from
		// Processing so as to resemble it

		g.drawRect(elmX1, elmY1, elmX2 - elmX1, elmY2 - elmY1 - 1);
		g.drawString("Novice", elmX1 + OptionBar.MODE_GAP_WIDTH, elmY1
				+ (OptionBar.MODE_BOX_HEIGHT + elmTextAscent) / 2
				+ elmTextAscent / 3);
		g.drawImage(
				new ImageIcon((this.getClass()
						.getResource(OptionBar.MODE_MENU_ARROW))).getImage(),
				elmX2 - OptionBar.ARROW_WIDTH - OptionBar.MODE_GAP_WIDTH, elmY1
						+ 1 + (elmY2 - elmY1 - 1 - OptionBar.ARROW_HEIGHT) / 2,
				OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT, null);

	}

	void renderRepoSelectionMenu(Graphics g) {
		g.setColor(new Color(255, 255, 255));

		g.drawRect(rsmX1, rsmY1, rsmX2 - rsmX1, rsmY2 - rsmY1 - 1);
		g.drawImage(
				new ImageIcon((this.getClass()
						.getResource(OptionBar.MODE_MENU_ARROW))).getImage(),
				rsmX1 + OptionBar.MODE_GAP_WIDTH, rsmY1 + 1
						+ (rsmY2 - rsmY1 - 1 - OptionBar.ARROW_HEIGHT) / 2,
				OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT, null);
	}

	void initializeDimensions(Graphics g) {
		FontMetrics metrics = g.getFontMetrics(this.getFont());

		elmTextWidth = metrics.stringWidth("Novice");
		elmTextAscent = metrics.getHeight();
		elmX2 = getWidth() - 46;
		elmX1 = elmX2
				- (OptionBar.MODE_GAP_WIDTH + elmTextWidth
						+ OptionBar.MODE_GAP_WIDTH + OptionBar.ARROW_WIDTH + OptionBar.MODE_GAP_WIDTH);
		elmY1 = (getHeight() - 4 * OptionBar.MODE_BOX_HEIGHT) / 2;
		elmY2 = elmY1 + OptionBar.MODE_BOX_HEIGHT + elmTextAscent;

		rsmY1 = elmY1;
		rsmY2 = elmY2;
		rsmX1 = elmX1 - OptionBar.MODE_GAP_WIDTH - OptionBar.MODE_GAP_WIDTH
				- OptionBar.ARROW_WIDTH - OptionBar.MODE_GAP_WIDTH;
		rsmX2 = elmX1 - OptionBar.MODE_GAP_WIDTH;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("clicked x,y: " + x + " " + y);
		if (x > elmX1 && x < elmX2 && y > elmY1 && y < elmY2) {
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		for (JButton b : buttons) {
			// TODO: Find out how to correct this button-image offset. Button
			// not exactly aligned with icon!
			// Making the button as a border-layout doesn't work

			// System.out.println("Mouse: x= " + e.getX() + " y: " + e.getY()
			// + "  Bn: x= " + b.getX() + "-" + b.getX() + b.getWidth()
			// + " y= " + b.getY() + "-" + b.getY() + b.getHeight());
			if (b.getX() < (e.getX()) && b.getY() < (e.getY())
					&& (b.getX() + b.getWidth()) > e.getX()
					&& (b.getY() + b.getHeight()) > e.getY()) {
				String prnt = e.getComponent().getName();
				buttonDescription.setText(prnt);
				return;
			}
		}
		buttonDescription.setText("");

	}

	// public class PointCoordinates {
	// Point tl; // top-left
	// Point br; // bottom-right
	//
	// public PointCoordinates(Point tl, int width, int height) {
	//
	// this.tl = new Point(tl);
	// this.br = new Point(tl.x + width, tl.y + height);
	// }
	//
	// public boolean isWithin(int x, int y) {
	// if (x > tl.x && x < br.x && y > tl.y && y < br.y)
	// return true;
	// else
	// return false;
	// }
	//
	// @Override
	// public String toString() {
	// return "tl=(" + tl.x + "," + tl.y + "), br=(" + br.x + "," + br.y
	// + ")";
	// }
	//
	// }

}
