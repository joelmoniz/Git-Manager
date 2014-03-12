package git_manager.tool;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class GitMenuBar extends JMenuBar {

	private JMenu git, onlineRepos, settings, help;

	public GitMenuBar() {
		git = new JMenu("Git");
		onlineRepos = new JMenu("Online Repos");
		settings = new JMenu("Settings");
		help = new JMenu("Help");
		
		congiureMenus();

	}

	private void congiureMenus() {

		populateGitMenu();
		populateOnlineMenu();
		populateSettingsMenu();
		populateHelpMenu();

		this.add(git);
		this.add(onlineRepos);
		this.add(settings);
		this.add(help);
	}

	private void populateHelpMenu() {
		JMenuItem init = new JMenuItem("Initialize repo");
		JMenuItem add = new JMenuItem("Add");
		JMenuItem commit = new JMenuItem("Commit");
		JMenuItem snapshot = new JMenuItem("Snapshot");
		JMenuItem diff = new JMenuItem("Difference");
		JMenuItem push = new JMenuItem("Push");
		JMenuItem revert = new JMenuItem("Revert");
		JMenuItem remove = new JMenuItem("Remove");
		JMenuItem status = new JMenuItem("Status");

		git.add(init);
		git.add(snapshot);
		git.add(add);
		git.add(commit);
		git.add(diff);
		git.add(status);
		git.add(push);
		git.add(revert);
		git.add(remove);

	}

	private void populateSettingsMenu() {

	}

	private void populateOnlineMenu() {

	}

	private void populateGitMenu() {

	}

}