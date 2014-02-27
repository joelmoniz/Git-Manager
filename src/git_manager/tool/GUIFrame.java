package git_manager.tool;

import git_manager.constants.ProjectDetails;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import processing.app.Editor;
import processing.app.Toolkit;

public class GUIFrame extends JFrame implements ActionListener {

	Editor editor;
	GitOperations gitops;
	String uName, pass, remote;
	JPanel panel;

	private static final long serialVersionUID = 1L;

	public GUIFrame(Editor e) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 200, 400, 400);
		setResizable(true);
		setTitle(ProjectDetails.NAME);
		panel = new JPanel(null);
		add(panel);
		setContentPane(panel);
		this.setVisible(true);

		JButton bCreate = new JButton("Click to create repo");
		bCreate.setBounds(120, 50, 180, 35);
		bCreate.addActionListener(this);
		panel.add(bCreate);
		bCreate.setActionCommand("create");

		JButton bSnap = new JButton("Click to \"Take Snapshot\"");
		bSnap.setBounds(120, 100, 180, 35);
		bSnap.addActionListener(this);
		panel.add(bSnap);
		bSnap.setActionCommand("snapshot");

		JButton bAdd = new JButton("Add all files");
		bAdd.setBounds(120, 150, 80, 35);
		bAdd.addActionListener(this);
		panel.add(bAdd);
		bAdd.setActionCommand("add");

		JButton bCommit = new JButton("Commit");
		bCommit.setBounds(220, 150, 80, 35);
		bCommit.addActionListener(this);
		panel.add(bCommit);
		bCommit.setActionCommand("commit");

		JButton bLogin = new JButton("Push to GitHub");
		bLogin.setBounds(120, 200, 180, 35);
		bLogin.addActionListener(this);
		panel.add(bLogin);
		bLogin.setActionCommand("push");

		Toolkit.setIcon(this);
		editor = e;
		gitops = new GitOperations(editor);

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