package github_manager.tool;

import github_manager.constants.ProjectDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.app.Editor;
import processing.app.Toolkit;

public class GUIFrame extends JFrame implements ActionListener {

	Editor editor;
	GitOperations gitops;

	private static final long serialVersionUID = 1L;

	public GUIFrame(Editor e) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 200, 400, 400);
		setResizable(true);
		setTitle(ProjectDetails.NAME);
		JPanel panel = new JPanel(null);
		add(panel);
		setContentPane(panel);
		this.setVisible(true);

		JButton b = new JButton("Click to create repo");
		b.setBounds(120, 50, 180, 35);
		b.addActionListener(this);
		panel.add(b);
		b.setActionCommand("create");

		JButton b2 = new JButton("Click to \"Take Snapshot\"");
		b2.setBounds(120, 100, 180, 35);
		b2.addActionListener(this);
		panel.add(b2);
		b2.setActionCommand("snapshot");
		
		JButton b3 = new JButton("Add all files");
		b3.setBounds(120, 150, 80, 35);
		b3.addActionListener(this);
		panel.add(b3);
		b3.setActionCommand("add");
		
		JButton b4 = new JButton("Commit");
		b4.setBounds(220, 150, 80, 35);
		b4.addActionListener(this);
		panel.add(b4);
		b4.setActionCommand("commit");

		Toolkit.setIcon(this);
		editor = e;
		gitops = new GitOperations(editor);

	}

	public void actionPerformed(ActionEvent e) {
		if ("create".equals(e.getActionCommand()))
			gitops.initRepo();
		else if ("snapshot".equals(e.getActionCommand()))
			gitops.addAndCommit("Test");
		else if ("add".equals(e.getActionCommand()))
			gitops.addFiles();
		else if ("commit".equals(e.getActionCommand()))
			gitops.commitChanges("Test");
	}

}
