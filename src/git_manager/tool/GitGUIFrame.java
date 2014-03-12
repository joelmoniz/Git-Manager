package git_manager.tool;

import git_manager.constants.ProjectDetails;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import processing.app.Editor;
import processing.app.Toolkit;

public class GitGUIFrame extends JFrame implements ActionListener {

	Editor editor;
	GitOperations gitops;
	String uName, pass, remote;
	JPanel panel;
	GitMenuBar menu;

	private static final long serialVersionUID = 1L;

	public GitGUIFrame(Editor e) {
		// TODO Git Tool can be opened only once per processing sketch. Rectify.

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//setBounds(200, 200, 400, 400);
		
		setPreferredSize(new Dimension(650, 530));
		setResizable(true);
		setTitle(ProjectDetails.NAME);
		setVisible(true);

		// GitOptionToolbar tool = new
		// GitOptionToolbar(editor,editor.getBase());

		menu = new GitMenuBar();
		setJMenuBar(menu);
		
		GitOptionToolbar tool = new GitOptionToolbar();

		panel = new JPanel(new GridBagLayout());
		add(panel);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.weighty = 1.0;
		c.weightx=1;
		c.insets = new Insets(0, 0, 5, 0);
		
		panel.add(tool, c);
		setContentPane(panel);
		this.setVisible(true);
				
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 3, 0);
		c.weighty = 0;
		
		JButton bCreate = new JButton("Click to create repo");
		//bCreate.setBounds(120, 50, 180, 35);
		bCreate.addActionListener(this);
		panel.add(bCreate,c);
		bCreate.setActionCommand("create");

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 3, 0);
		
		JButton bSnap = new JButton("Click to \"Take Snapshot\"");
		//bSnap.setBounds(120, 100, 180, 35);
		bSnap.addActionListener(this);
		panel.add(bSnap,c);
		bSnap.setActionCommand("snapshot");

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 3, 0);
		
		JButton bAdd = new JButton("Add all files");
		//bAdd.setBounds(120, 150, 80, 35);
		bAdd.addActionListener(this);
		panel.add(bAdd,c);
		bAdd.setActionCommand("add");

		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 3, 0);
		
		JButton bCommit = new JButton("Commit");
	//	bCommit.setBounds(220, 150, 80, 35);
		bCommit.addActionListener(this);
		panel.add(bCommit,c);
		bCommit.setActionCommand("commit");

		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 3, 0);
		
		JButton bLogin = new JButton("Push to GitHub");
//		bLogin.setBounds(120, 200, 180, 35);
		bLogin.addActionListener(this);
		panel.add(bLogin,c);
		bLogin.setActionCommand("push");

		Toolkit.setIcon(this);
		editor = e;
		gitops = new GitOperations(editor);

		 this.addWindowListener(new WindowAdapter() {
		 // Invoked when a window is in the process of being closed.
		 public void windowClosing(WindowEvent e) {
		 dispose();
		 GitManager.frame = null;
		 }
		 });
this.pack();
	}

	void getUnameandPass() {
		JTextField uName = new JTextField(15);
		JPasswordField pass = new JPasswordField(15);
		JTextField remote = new JTextField(15);

		JPanel panel = new JPanel(new GridLayout(0, 1));
		JPanel un = new JPanel();
		JPanel un2 = new JPanel();
		JPanel un3 = new JPanel();
		un.add(new JLabel("Username:   "));
		un.add(uName);
		panel.add(un);
		un2.add(new JLabel("Password:   "));
		un2.add(pass);
		panel.add(un2);
		un3.add(new JLabel("GitHub repo:"));
		un3.add(remote);
		panel.add(un3);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Login to GitHub", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.uName = uName.getText();
			this.pass = new String(pass.getPassword());
			this.remote = remote.getText();
		}
	}

	public String getMessage(String dialogText) {
		return JOptionPane.showInputDialog(panel, dialogText, null);
	}

	public void actionPerformed(ActionEvent e) {
		if ("create".equals(e.getActionCommand()))
			gitops.initRepo();
		else if ("snapshot".equals(e.getActionCommand()))
			gitops.addAndCommit(getMessage("Enter commit message"));
		else if ("add".equals(e.getActionCommand()))
			gitops.addFiles();
		else if ("commit".equals(e.getActionCommand()))
			gitops.commitChanges(getMessage("Enter commit message"));
		else if ("push".equals(e.getActionCommand())) {
			getUnameandPass();
			gitops.pushToRemote(uName, pass, remote);
		}
	}

}
