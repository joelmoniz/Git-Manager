package github_manager.tool;

import github_manager.constants.ProjectDetails;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import processing.app.Toolkit;

public class GUIFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 200, 400, 140);
		setResizable(false);
		setTitle(ProjectDetails.NAME);
		JPanel panel = new JPanel(null);
		add(panel);
		setContentPane(panel);
		this.setVisible(true);

		JLabel label = new JLabel("Hello Git World!!!");
		label.setBounds(80, 40, 100, 20);
		panel.add(label);

		Toolkit.setIcon(this);
	}

}
