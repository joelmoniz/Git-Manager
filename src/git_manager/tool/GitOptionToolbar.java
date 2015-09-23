/*
  Part of the Git Manager for Processing- http://joelmoniz.com/git-manager/ 
    
  A tool for the Processing - http://processing.org
  
  Copyright (c) 2015 Joel Moniz
  
  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 2
  of the License, or (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
  USA.
 */
package git_manager.tool;

import git_manager.constants.OptionBar;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import processing.app.Base;
import processing.app.Platform;
import processing.app.ui.Editor;
import processing.app.ui.Toolkit;

public class GitOptionToolbar extends JPanel implements MouseInputListener,
		ActionListener {

  private static final long serialVersionUID = 6679503189108207242L;

  /**
   * used to debug the positioning of stuff by adding in borders 
   */
  private static final boolean DEBUG_BORDER = false;
  
  /**
   * Used to add in the horizontal structs between each of the option buttons
   */
	private static final int space1 = 5;
	
	/**
	 * Used to add in tiny vertical structs between each of the tiny buttons
	 */
	private static final int space2 = 2;
	// Expertise Selection Menu variables
	private int elmX2, elmX1, elmY1, elmY2, elmTextWidth, elmTextAscent;
	// ArrayList<PointCoordinates> points;
	ArrayList<JButton> buttons;

	// Repo Selection Menu variables
	private int rsmX2, rsmX1, rsmY1, rsmY2;
	private JLabel buttonDescription;
	private GitOperations gitops;
	private Editor editor;
	private JPanel buttonToolbar;
  private JPanel descriptionToolbar;
  private JPanel tinyToolbar;

	public GitOptionToolbar(Editor e) {
		this.setName("ActionBar");
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
//		this.setBounds(0, 0, 400, 145);
		this.setVisible(true);
//		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		this.setOrientation(JToolBar.VERTICAL);

		buttonDescription = new JLabel();
		// TODO: Add padding instead of adding the space
		buttonDescription.setText(OptionBar.DESCRIP_STATUS + "   ");
//		if (Base.isLinux())
//			buttonDescription.setForeground(Color.BLACK);
//		else
			buttonDescription.setForeground(Color.WHITE);
			
		if (DEBUG_BORDER) {
		  buttonDescription.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		}
		// points = new ArrayList<PointCoordinates>();
		buttons = new ArrayList<JButton>();

		buttonToolbar = new JPanel();
		descriptionToolbar = new JPanel();
		tinyToolbar = new JPanel();

		editor = e;

		gitops = new GitOperations(editor);

		buttonToolbar.setLayout(new BoxLayout(buttonToolbar, BoxLayout.LINE_AXIS));
		buttonToolbar.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		descriptionToolbar.setLayout(new BoxLayout(descriptionToolbar, BoxLayout.LINE_AXIS));
    tinyToolbar.setLayout(new BoxLayout(tinyToolbar, BoxLayout.PAGE_AXIS));
    tinyToolbar.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

		populateButtonBar();
		populateDescribeBar();
		populateTinyBar();
		
		JPanel pna = new JPanel();
		pna.setLayout(new BoxLayout(pna, BoxLayout.PAGE_AXIS));
		pna.add(buttonToolbar);
//		pna.add(Box.createHorizontalGlue());
		pna.add(descriptionToolbar);
		
    if (DEBUG_BORDER) {
      buttonToolbar
          .setBorder(BorderFactory.createCompoundBorder(BorderFactory
              .createMatteBorder(1, 1, 1, 1, Color.CYAN), buttonToolbar
              .getBorder()));
      tinyToolbar.setBorder(BorderFactory.createCompoundBorder(BorderFactory
          .createMatteBorder(1, 1, 1, 1, Color.PINK), tinyToolbar.getBorder()));
      descriptionToolbar.setBorder(BorderFactory
          .createMatteBorder(1, 1, 1, 1, Color.YELLOW));
      pna.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
    }
		
//		this.add(pna, BorderLayout.PAGE_START);
////		this.add(buttonToolbar, BorderLayout.PAGE_START);
////		this.add(tinyToolbar, BorderLayout.LINE_END);
//		this.add(descriptionToolbar, BorderLayout.CENTER);
		
		this.add(pna);
//		this.add(Box.createHorizontalGlue());
//		
//		JPanel pan = new JPanel();
//		pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
//		pan.setBackground(Color.BLACK);
//		pan.setOpaque(true);
//		
//		pan.add(Box.createHorizontalGlue());
//		pan.add(tinyToolbar);
//		
//		this.add(pan);
		
		this.add(tinyToolbar);
		
//		buttonToolbar.setPreferredSize(this.getPreferredSize());
		buttonToolbar.setBackground(Color.BLACK);
		buttonToolbar.setOpaque(true);
//		buttonToolbar.setFloatable(false);
		
		descriptionToolbar.setBackground(Color.BLACK);
		descriptionToolbar.setOpaque(true);
//		descriptionToolbar.setFloatable(false);
		
		tinyToolbar.setBackground(Color.BLACK);
		tinyToolbar.setOpaque(true);  
		
    pna.setBackground(Color.BLACK);
    pna.setOpaque(true);
		
		disableButtons();
		
//		JLabel t = new JLabel("Test");
//		t.setForeground(Color.WHITE);
//		descriptionToolbar.add(t);
		
//		addMouseListener(this);
//		addMouseMotionListener(this);
		this.setPreferredSize(this.getPreferredSize());
	}

	private void populateButtonBar() {
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(addButton(OptionBar.GIT_INIT_ICON, OptionBar.ACTION_INIT,
				OptionBar.DESCRIP_INIT, OptionBar.GIT_INIT_SELECTED_ICON,
				OptionBar.GIT_INIT_ICON));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(addButton(OptionBar.GIT_SNAP_ICON, OptionBar.ACTION_SNAP,
				OptionBar.DESCRIP_SNAP, OptionBar.GIT_SNAP_SELECTED_ICON,
				OptionBar.GIT_SNAP_DISABLED_ICON));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		// TODO: Uncomment this to add in a git diff
//		this.add(addButton(OptionBar.GIT_DIFF_ICON, OptionBar.ACTION_DIFF,
//				OptionBar.DESCRIP_DIFF, OptionBar.GIT_DIFF_SELECTED_ICON));
//		this.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(addButton(OptionBar.GIT_PUSH_ICON, OptionBar.ACTION_PUSH,
				OptionBar.DESCRIP_PUSH, OptionBar.GIT_PUSH_SELECTED_ICON,
				OptionBar.GIT_PUSH_DISABLED_ICON));
    this.buttonToolbar.add(Box.createHorizontalStrut(space1));
    this.buttonToolbar.add(addButton(OptionBar.GIT_PULL_ICON, OptionBar.ACTION_PULL,
                       OptionBar.DESCRIP_PULL, OptionBar.GIT_PULL_SELECTED_ICON,
                       OptionBar.GIT_PULL_DISABLED_ICON));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(addButton(OptionBar.GIT_REVERT_ICON, OptionBar.ACTION_REVERT,
				OptionBar.DESCRIP_REVERT, OptionBar.GIT_REVERT_SELECTED_ICON,
				OptionBar.GIT_REVERT_DISABLED_ICON));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(addButton(OptionBar.GIT_RM_ICON, OptionBar.ACTION_RM,
				OptionBar.DESCRIP_RM, OptionBar.GIT_RM_SELECTED_ICON,
				OptionBar.GIT_RM_DISABLED_ICON));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
    this.buttonToolbar.add(addButton(OptionBar.GIT_LOG_ICON, OptionBar.ACTION_LOG,
                       OptionBar.DESCRIP_LOG, OptionBar.GIT_LOG_SELECTED_ICON,
                       OptionBar.GIT_LOG_DISABLED_ICON));
    this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(addButton(OptionBar.GIT_STATUS_ICON, OptionBar.ACTION_STATUS,
				OptionBar.DESCRIP_STATUS, OptionBar.GIT_STATUS_SELECTED_ICON,
				OptionBar.GIT_STATUS_DISABLED_ICON));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(Box.createHorizontalStrut(space1));
		this.buttonToolbar.add(buttonDescription);
    this.buttonToolbar.add(Box.createHorizontalStrut(space1));

    this.buttonToolbar.add(Box.createHorizontalGlue());
	}
  
	// TODO: Add all this in into OptionBar class 
  private void populateDescribeBar() {

     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));
     this.descriptionToolbar.add(addLabel("Start", 41, 0));
     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));
     this.descriptionToolbar.add(addLabel("Commit", 41, 0));
     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));
     // TODO: Uncomment this to add in a git diff
//     this.add(addButton(OptionBar.GIT_DIFF_ICON, OptionBar.ACTION_DIFF,
//         OptionBar.DESCRIP_DIFF, OptionBar.GIT_DIFF_SELECTED_ICON));
//     this.add(Box.createHorizontalStrut(space1));
     this.descriptionToolbar.add(addLabel("Online", 41*2, 1));
     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));
     this.descriptionToolbar.add(addLabel("Undo", 41+42, 1));
     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));
     this.descriptionToolbar.add(addLabel("Logs", 41+42, 1));
     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));
     this.descriptionToolbar.add(Box.createHorizontalStrut(space1));

     // TODO: Can add something to descibe in more details here
     // this.descriptionToolbar.add(buttonDescription);

     this.descriptionToolbar.add(Box.createHorizontalGlue());

   }
  
// TODO: Figure out why on earth all havoc breaks lose when I use 
//  Box.createVerticalStruct() instead of the incorrect Box.createHorizontalStruct()
  private void populateTinyBar() {
    this.tinyToolbar.add(addButton(OptionBar.HELP_ICON, OptionBar.ACTION_HELP,
                                   OptionBar.DESCRIP_HELP, OptionBar.HELP_SELECTED_ICON,
                                   OptionBar.HELP_ICON));
    this.tinyToolbar.add(Box.createHorizontalStrut(space2));
    this.tinyToolbar.add(addButton(OptionBar.BUG_ICON, OptionBar.ACTION_BUG,
                                     OptionBar.DESCRIP_BUG, OptionBar.BUG_SELECTED_ICON,
                                     OptionBar.BUG_ICON));
    this.tinyToolbar.add(Box.createHorizontalStrut(space2));
    this.tinyToolbar.add(addButton(OptionBar.SITE_ICON, OptionBar.ACTION_SITE,
                                   OptionBar.DESCRIP_SITE, OptionBar.SITE_SELECTED_ICON,
                                   OptionBar.SITE_ICON));
    this.tinyToolbar.add(Box.createVerticalGlue());
  }

	protected JButton addButton(String imageLocation, String actionCommand,
			String buttonName, String rolloverImageLocation, 
			String disabledImageLocation) {
		JButton b = new JButton();
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				imageLocation));
		ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(
				rolloverImageLocation));
		
		/*
//		Image normalImage = icon.getImage();
//		Image grayImage = GrayFilter.createDisabledImage(normalImage);
		
    final int w = icon.getIconWidth();
    final int h = icon.getIconHeight();
    GraphicsEnvironment ge =
        GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    GraphicsConfiguration gc = gd.getDefaultConfiguration();
    BufferedImage image = gc.createCompatibleImage(w, h);
    Graphics2D g2d = image.createGraphics();
    icon.paintIcon(null, g2d, 0, 0);
    Image grayImage = GrayFilter.createDisabledImage(image);
    */
    
    ImageIcon disabledIcon = 
        new ImageIcon(this.getClass().getResource(disabledImageLocation));
		
		// b.setLayout(new BorderLayout());
		// b.setIcon(icon);
		// JLabel label = new JLabel(icon);
		// b.add(label);
		b.setIcon(icon);
		b.setRolloverEnabled(true);
		b.setRolloverIcon(rolloverIcon);
		b.setDisabledIcon(disabledIcon);
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

		if (DEBUG_BORDER) {
		  b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE), b.getBorder()));
		}
		
		buttons.add(b);

		return b;
	}
	
	protected JLabel addLabel(String content, int buttonWidth, int separatorCount) {
	  JLabel label = new JLabel(content, SwingConstants.CENTER);
//	  label.setAlignmentX(Component.BOTTOM_ALIGNMENT);
//	  label.setAlignmentY(Component.);
	  label.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.WHITE));
	  label.setForeground(Color.WHITE);
	  label.setPreferredSize(new Dimension(buttonWidth + separatorCount*space1, 
	                                       label.getHeight()));
	  return label;
	}
	
	protected void showHelpMenu(final String imageLocation) {
	  SwingUtilities.invokeLater(new Runnable() {
      
      @Override
      public void run() {
        JFrame f = new JFrame("Git Manager Help");
        JPanel p = new JPanel();
        
        // TODO: Should a BufferedImage be used instead?
        ImageIcon icon = new ImageIcon(this.getClass().getResource(imageLocation));
        JLabel imLabel = new JLabel(icon);
        p.add(imLabel);
        f.add(p);
        Toolkit.setIcon(f);
        f.pack();
        f.setVisible(true);
      }
    });
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
		//renderRepoSelectionMenu(g); TODO: Uncomment for online
		//renderExpertiseLevelMenu(g); TODO: Uncomment for expertise

	}

	// ALWAYS call renderExpertiseLevelMenu() before renderRepoSelectionMenu()

	void renderExpertiseLevelMenu(Graphics g) {

		if (Platform.isLinux()) {
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
		if (Platform.isLinux())
			g.setColor(new Color(0, 0, 0));
		else
			g.setColor(new Color(255, 255, 255));

		g.drawRect(rsmX1, rsmY1, rsmX2 - rsmX1, rsmY2 - rsmY1 - 1);
		if (Platform.isLinux())
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

//		System.out.println("clicked x,y: " + x + " " + y);

		if (x > elmX1 && x < elmX2 && y > elmY1 && y < elmY2) {
			expertiseLevelMenuRender(e);
			return;
		}

		if (x > rsmX1 && x < rsmX2 && y > rsmY1 && y < rsmY2) {
			//onlineRepoMenuRender(e); TODO: Uncomment for online
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
	
	public void clearDescription() {
		buttonDescription.setText("");
	}

//	@Override
//	public boolean isRollover() {
//		return super.isRollover();
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
    String prnt = e.getComponent().getName();
//    TODO: if ()
    buttonDescription.setText(prnt);
	}

	@Override
	public void mouseExited(MouseEvent e) {
	  buttonDescription.setText("");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if (a.equals(OptionBar.ACTION_INIT)) {
		  ((JButton)e.getSource()).getModel().setRollover(false);
		  buttonDescription.setText("");
			gitops.initRepo();
		}
		else if (a.equals(OptionBar.ACTION_ADD))
			gitops.addFiles();
		else if (a.equals(OptionBar.ACTION_DIFF)) {
		  gitops.printDiffWithHead();
		}
		else if (a.equals(OptionBar.ACTION_PUSH)) {
		  ((JButton)e.getSource()).getModel().setRollover(false);
		  buttonDescription.setText("");
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
		}
		else if (a.equals(OptionBar.ACTION_REVERT)) {
		  ((JButton)e.getSource()).getModel().setRollover(false);
		  buttonDescription.setText("");
		  gitops.revertCommit();
		}
		else if (a.equals(OptionBar.ACTION_LOG))
		  gitops.printLogs();
		else if (a.equals(OptionBar.ACTION_SNAP)) {
		  ((JButton)e.getSource()).getModel().setRollover(false);
		  buttonDescription.setText("");
			gitops.addAndCommit(getMessage("Enter commit message"));
		}
		else if (a.equals(OptionBar.ACTION_RM)) {
		  ((JButton)e.getSource()).getModel().setRollover(false);
		  buttonDescription.setText("");
		  gitops.resetHard();
		}
		else if (a.equals(OptionBar.ACTION_STATUS)) {
		  gitops.displayStatus();
		}
		else if (a.equals(OptionBar.ACTION_PULL))
		 {
       System.out.println("Pulling...");
       ((JButton)e.getSource()).getModel().setRollover(false);
       buttonDescription.setText("");
        // TODO: Design a button different from the current pull and the
        // pull that displays on mouseover which remains until the pull
        // action is complete, and then changes back to original pull
        // button- almost identical to the similar todo for push
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
          @Override
          public Void doInBackground() {
            gitops.pullFromRemote();
            return null;
          }
          //
          // @Override
          // public void done() {
          // }
        };
        worker.execute();
    }
		else if (a.equals(OptionBar.ACTION_HELP)) {
		  showHelpMenu(OptionBar.HELP_SCREEN);
		}
		else if (a.equals(OptionBar.ACTION_BUG)) {
		  Platform.openURL(OptionBar.URL_BUG);
		}
    else if (a.equals(OptionBar.ACTION_SITE)) {
      Platform.openURL(OptionBar.URL_GIT_MANAGER);
    }
		disableButtons();

	}
	
	public void disableButtons() {
		for (JButton b: buttons) {
			if (b.getName().equals(OptionBar.DESCRIP_INIT)) {
				b.setEnabled(!gitops.repoExists());
			}
			else if (b.getName().equals(OptionBar.DESCRIP_SNAP)
			    || b.getName().equals(OptionBar.DESCRIP_PULL)
			    || b.getName().equals(OptionBar.DESCRIP_LOG)
			    || b.getName().equals(OptionBar.DESCRIP_STATUS)) {
				b.setEnabled(gitops.repoExists());
			}
			else if (b.getName().equals(OptionBar.DESCRIP_PUSH) 
			   || b.getName().equals(OptionBar.DESCRIP_REVERT)
			   || b.getName().equals(OptionBar.DESCRIP_RM)) {
				b.setEnabled(gitops.hasCommit());
			}
		}
	}

	public String getMessage(String dialogText) {
		return JOptionPane.showInputDialog(this, dialogText, null);
	}
}
