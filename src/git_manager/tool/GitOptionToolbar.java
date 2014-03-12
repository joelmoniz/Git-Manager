package git_manager.tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class GitOptionToolbar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	public GitOptionToolbar() {

		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 40);
		this.setVisible(true);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.black);
		populateToolBar();

	}

	private void populateToolBar() {
		// TODO Auto-generated method stub
		JButton b = new JButton();
		b.setToolTipText("Initialize repo");
		// this.add(b);
		/*
		 * ImageIcon init = null; try { init = new
		 * ImageIcon(("src\\gitinit.png")); } catch (NullPointerException n) {
		 * System.out.println("Null pointer exception"); }
		 */

	//	ImageIcon init = createImageIcon("gitinit.png", "git init Icon");

	//	Dimension d = new Dimension(init.getIconWidth(), init.getIconHeight());
//		b.setPreferredSize(d);
		// b.setSize(this.getHeight(), this.getHeight());
		// String imgLocation = File.separator + ".." + File.separator + ".."
		// + File.separator + ".." + File.separator + "data" + File.separator
		// +"gitinit"
		// + ".png";
		String imgLocation = "git_manager/tool/gitinit.png";
		// ImageIcon init = new ImageIcon(loadImage(imgLocation));

		// btnStick.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/g4p/toolStick.png")));
		
		/*
		b.setPreferredSize(d);
		b.setMinimumSize(d);
		b.setMaximumSize(d);
*/
	//	if (init != null)
	//		b.setIcon(init);
		
		//System.out.println(b.getIcon());
		
		// this.revalidate();
		// this.repaint();

		this.add(b);
		// JButton button = new JButton(new ImageIcon(
		// (init.getImage()).getScaledInstance(2*this.getHeight(),
		// 2*this.getHeight(), java.awt.Image.SCALE_SMOOTH)));

		JButton button = new JButton("a");
//		button.setPreferredSize(d);
//		button.setMinimumSize(d);
//		button.setMaximumSize(d);
		this.add(button);

		// JComponent jLabel4 = new JLabel();
		// jLabel4.setMaximumSize(new java.awt.Dimension(32000, 16));
		// jLabel4.setMaximumSize(new java.awt.Dimension(54, 23));
		// jLabel4.setMinimumSize(new java.awt.Dimension(54, 23));
		// jLabel4.setPreferredSize(new java.awt.Dimension(54, 23));
		// this.add(jLabel4);
		// textArea = new JTextArea(1, 10);
		// textArea.setEditable(false);
		// this.add(textArea);
	}

	// public PImage loadPImage(String theFilename) {
	// return new PImage(loadImage(theFilename));
	// }

	public Image loadImage(String theFilename) {
		if (theFilename.startsWith(File.separator)) {
			return new ImageIcon(theFilename).getImage();
		} else {
			URL img = this.getClass().getResource(getPath(theFilename));
			if (img == null)
				System.out.println("Danger");
			return new ImageIcon(img).getImage();
		}
	}

	public String getPath(String theFilename) {
		if (theFilename.startsWith("/")) {
			return theFilename;
		}
		return File.separator + "data" + File.separator + theFilename;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL;
		try{
		imgURL = GitOptionToolbar.class.getResource(path);
		}
		catch(NullPointerException n)
		{
			imgURL = null;
			System.out.println("null");
		}
		if (imgURL != null) {
			System.out.println("Found " + path);
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
// @SuppressWarnings("serial")
// public class GitOptionToolbar extends JavaToolbar {
//
// public GitOptionToolbar(Editor editor, Base base) {
// super(editor, base);
// // TODO Auto-generated constructor stub
// }
//
//
// }