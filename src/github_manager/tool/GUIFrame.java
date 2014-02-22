package github_manager.tool;

import github_manager.constants.ProjectDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

import processing.app.Editor;
import processing.app.Toolkit;

public class GUIFrame extends JFrame implements ActionListener {

	Editor editor;
	
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

	}

	public void initRepo() {
		File targetDir = new File(editor.getSketch().getFolder().getAbsolutePath()
				+ "/.git");
		if (targetDir.exists())
			{
			System.out.println("Repo already exists!");
			//System.out.println(targetDir.getAbsolutePath());
			return;
			}
		Repository repo;
		try {
			repo = new FileRepository(targetDir);
			repo.create(true);
			System.out.println("New repo created.");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		initRepo();
	} 

	
	//Retains the details of the last editor when a new one is opened.
	// Fix this
}
