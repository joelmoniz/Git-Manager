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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import processing.app.Base;
import processing.app.Editor;

public class GitOptionToolbar extends JToolBar implements MouseInputListener,
		ActionListener {

	private static final long serialVersionUID = 1L;
	private final int space1 = 5;
	// Expertise Selection Menu variables
	private int elmX2, elmX1, elmY1, elmY2, elmTextWidth, elmTextAscent;
	// ArrayList<PointCoordinates> points;
	ArrayList<JButton> buttons;

	// Repo Selection Menu variables
	private int rsmX2, rsmX1, rsmY1, rsmY2;
	private JLabel buttonDescription;
	private GitOperations gitops;
	private Editor editor;

	public GitOptionToolbar(Editor e) {
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 145);
		this.setVisible(true);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		buttonDescription = new JLabel();
		buttonDescription.setText("");
		if (Base.isLinux())
			buttonDescription.setForeground(Color.BLACK);
		else
			buttonDescription.setForeground(Color.WHITE);
		// points = new ArrayList<PointCoordinates>();
		buttons = new ArrayList<JButton>();

		editor = e;

		gitops = new GitOperations(editor);

		populateToolBar();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private void populateToolBar() {
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_INIT_ICON, OptionBar.ACTION_INIT,
				OptionBar.DESCRIP_INIT, OptionBar.GIT_INIT_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_SNAP_ICON, OptionBar.ACTION_SNAP,
				OptionBar.DESCRIP_SNAP, OptionBar.GIT_SNAP_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_DIFF_ICON, OptionBar.ACTION_DIFF,
				OptionBar.DESCRIP_DIFF, OptionBar.GIT_DIFF_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_PUSH_ICON, OptionBar.ACTION_PUSH,
				OptionBar.DESCRIP_PUSH, OptionBar.GIT_PUSH_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_REVERT_ICON, OptionBar.ACTION_REVERT,
				OptionBar.DESCRIP_REVERT, OptionBar.GIT_REVERT_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_RM_ICON, OptionBar.ACTION_RM,
				OptionBar.DESCRIP_RM, OptionBar.GIT_RM_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(addButton(OptionBar.GIT_STATUS_ICON, OptionBar.ACTION_STATUS,
				OptionBar.DESCRIP_STATUS, OptionBar.GIT_STATUS_SELECTED_ICON));
		this.add(Box.createHorizontalStrut(space1));
		this.add(Box.createHorizontalStrut(space1));
		this.add(buttonDescription);

		this.add(Box.createHorizontalGlue());

	}

	private JButton addButton(String imageLocation, String actionCommand,
			String buttonName, String rolloverImageLocation) {
		JButton b = new JButton();
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				imageLocation));
		ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(
				rolloverImageLocation));
		// b.setLayout(new BorderLayout());
		// b.setIcon(icon);
		// JLabel label = new JLabel(icon);
		// b.add(label);
		b.setIcon(icon);
		b.setRolloverEnabled(true);
		b.setRolloverIcon(rolloverIcon);
		// b.setIconTextGap(0);
		// b.setMargin(new Insets(0, 0, 0, 0));
		// b.setSize(d);
		b.setFocusPainted(false);
		b.setBackground(Color.black);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		b.setBorder(emptyBorder);
		b.setName(buttonName);
		b.setActionCommand(actionCommand);
		b.addActionListener(this);
		b.addMouseListener(this);
		b.addMouseMotionListener(this);

		buttons.add(b);

		return b;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// try {
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } catch (InstantiationException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (UnsupportedLookAndFeelException e) {
		// e.printStackTrace();
		// }
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		initializeDimensions(g);
		renderRepoSelectionMenu(g);
		renderExpertiseLevelMenu(g);

	}

	// ALWAYS call renderExpertiseLevelMenu() before renderRepoSelectionMenu()

	void renderExpertiseLevelMenu(Graphics g) {

		if (Base.isLinux()) {
			g.setColor(new Color(0, 0, 0));
			g.drawImage(
					new ImageIcon((this.getClass()
							.getResource(OptionBar.MODE_MENU_ARROW_LINUX)))
							.getImage(), elmX2 - OptionBar.ARROW_WIDTH
							- OptionBar.MODE_GAP_WIDTH, elmY1 + 1
							+ (elmY2 - elmY1 - 1 - OptionBar.ARROW_HEIGHT) / 2,
					OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT, null);
		} else {
			g.setColor(new Color(255, 255, 255));
			g.drawImage(
					new ImageIcon((this.getClass()
							.getResource(OptionBar.MODE_MENU_ARROW)))
							.getImage(), elmX2 - OptionBar.ARROW_WIDTH
							- OptionBar.MODE_GAP_WIDTH, elmY1 + 1
							+ (elmY2 - elmY1 - 1 - OptionBar.ARROW_HEIGHT) / 2,
					OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT, null);
		}
		g.setFont(this.getFont());

		// the following few pieces of code have been adapted from
		// Processing so as to resemble it

		g.drawRect(elmX1, elmY1, elmX2 - elmX1, elmY2 - elmY1 - 1);
		g.drawString("Novice", elmX1 + OptionBar.MODE_GAP_WIDTH, elmY1
				+ (OptionBar.MODE_BOX_HEIGHT + elmTextAscent) / 2
				+ elmTextAscent / 3);

	}

	void renderRepoSelectionMenu(Graphics g) {
		if (Base.isLinux())
			g.setColor(new Color(0, 0, 0));
		else
			g.setColor(new Color(255, 255, 255));

		g.drawRect(rsmX1, rsmY1, rsmX2 - rsmX1, rsmY2 - rsmY1 - 1);
		if (Base.isLinux())
			g.drawImage(
					new ImageIcon((this.getClass()
							.getResource(OptionBar.MODE_MENU_ARROW_LINUX)))
							.getImage(), rsmX1 + OptionBar.MODE_GAP_WIDTH,
					rsmY1 + 1 + (rsmY2 - rsmY1 - 1 - OptionBar.ARROW_HEIGHT)
							/ 2, OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT,
					null);
		else
			g.drawImage(
					new ImageIcon((this.getClass()
							.getResource(OptionBar.MODE_MENU_ARROW)))
							.getImage(), rsmX1 + OptionBar.MODE_GAP_WIDTH,
					rsmY1 + 1 + (rsmY2 - rsmY1 - 1 - OptionBar.ARROW_HEIGHT)
							/ 2, OptionBar.ARROW_WIDTH, OptionBar.ARROW_HEIGHT,
					null);
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
			expertiseLevelMenuRender(e);
			return;
		}

		if (x > rsmX1 && x < rsmX2 && y > rsmY1 && y < rsmY2) {
			onlineRepoMenuRender(e);
			return;
		}
	}

	private void expertiseLevelMenuRender(MouseEvent e) {
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
		popup.show(e.getComponent(), e.getX(), e.getY());
		popup.requestFocus();

	}

	private void onlineRepoMenuRender(MouseEvent e) {
		
		// TODO: Add separators between the items
		JPopupMenu popup = new JPopupMenu("Online Repo Selection");
		popup.setLayout(new BoxLayout(popup,BoxLayout.X_AXIS));

		JMenuItem item = new JMenuItem(new ImageIcon(this.getClass()
				.getResource(OptionBar.REPO_GITHUB)));
		
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					public Void doInBackground() {
						new GitGHOperations();
						return null;
					}
					//
					// @Override
					// public void done() {
					// }
				};
				worker.execute();
			}
		});
		popup.add(item);

		// popup.add(Box.createHorizontalStrut(5));
		// popup.add(new JSeparator(SwingConstants.VERTICAL));
		// popup.add(Box.createHorizontalStrut(5));

		JMenuItem item2 = new JMenuItem(new ImageIcon(this.getClass()
				.getResource(OptionBar.REPO_BITBUCKET)));
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					public Void doInBackground() {
						new GitBBOperations();
						return null;
					}
					//
					// @Override
					// public void done() {
					// }
				};
				worker.execute();
				new GitBBOperations();
			}
		});
		popup.add(item2);

		// popup.add(Box.createHorizontalStrut(5));
		// popup.add(new JSeparator(SwingConstants.VERTICAL));
		// popup.add(Box.createHorizontalStrut(5));

		JMenuItem item3 = new JMenuItem(new ImageIcon(this.getClass()
				.getResource(OptionBar.REPO_SOURCEFORGE)));
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		popup.add(item3);

		popup.pack();
		popup.setVisible(true);
		popup.show(e.getComponent(), e.getX() - 120, e.getY());
		// TODO: 120 has been hard-coded to push the menu to the left.
		// Replace with sum of all image widths
		popup.requestFocus();
		
		JMenuItem item4 = new JMenuItem(new ImageIcon(this.getClass()
				.getResource(OptionBar.REPO_GOOGLE_PROJ_HOST)));
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		popup.add(item4);

		popup.pack();
		popup.setVisible(true);
		popup.show(e.getComponent(), e.getX() - 120, e.getY());
		// TODO: 120 has been hard-coded to push the menu to the left.
		// Replace with sum of all image widths
		popup.requestFocus();

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

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if (a.equals(OptionBar.ACTION_INIT))
			gitops.initRepo();
		else if (a.equals(OptionBar.ACTION_ADD))
			gitops.addFiles();
		else if (a.equals(OptionBar.ACTION_DIFF))
			System.out.println("Not yet implemented");
		else if (a.equals(OptionBar.ACTION_PUSH)) {
			System.out.println("Pushing...");
			// TODO: Design a button different from the current push and the
			// push that displays on mouseover which remains until the push
			// action is complete, and then changes back to original push
			// button
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				@Override
				public Void doInBackground() {
					gitops.getUnameandPass();
					gitops.pushToRemote();
					return null;
				}
				//
				// @Override
				// public void done() {
				// }
			};
			worker.execute();

		} else if (a.equals(OptionBar.ACTION_REVERT))
			System.out.println("Not yet implemented");
		else if (a.equals(OptionBar.ACTION_SNAP))
			gitops.addAndCommit(getMessage("Enter commit message"));
		else if (a.equals(OptionBar.ACTION_RM))
			System.out.println("Not yet implemented");
		else if (a.equals(OptionBar.ACTION_STATUS))
			System.out.println("Not yet implemented");

	}

	public String getMessage(String dialogText) {
		return JOptionPane.showInputDialog(this, dialogText, null);
	}

}
