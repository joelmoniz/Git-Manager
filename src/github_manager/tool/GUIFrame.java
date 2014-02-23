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
		setBounds(200, 200, 400, 140);
		setResizable(false);
		setTitle(ProjectDetails.NAME);
		JPanel panel = new JPanel(null);
		add(panel);
		setContentPane(panel);
		this.setVisible(true);

		JButton b = new JButton("Click to create repo");
		b.setBounds(120, 50, 180, 20);
		b.addActionListener(this);
		panel.add(b);

		Toolkit.setIcon(this);
		editor = e;
		gitops = new GitOperations(editor);

	}


	
	public void actionPerformed(ActionEvent e) {
		gitops.initRepo();
	} 

}
