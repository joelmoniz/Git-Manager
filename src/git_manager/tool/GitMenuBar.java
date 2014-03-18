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

	// TODO: Fill this up, and give options proper names
	// This is only a list as and when it comes into my mind for now, not
	// necessarily complete nor necessarily sufficiently meaningful
	private void populateGitMenu() {
		JMenuItem init = new JMenuItem("Initialize repo");
		JMenuItem add = new JMenuItem("Add");
		JMenuItem commit = new JMenuItem("Commit");
		JMenuItem snapshot = new JMenuItem("Snapshot");
		JMenuItem diff = new JMenuItem("Difference");
		JMenuItem push = new JMenuItem("Push");
		JMenuItem revert = new JMenuItem("Revert");
		JMenuItem remove = new JMenuItem("Remove");
		JMenuItem status = new JMenuItem("Status");
		JMenuItem ignore = new JMenuItem("Git Ignore");
		JMenuItem rebase = new JMenuItem("Rebase");
		JMenuItem stash = new JMenuItem("Save into Stash");
		JMenuItem stash_list = new JMenuItem("View Stash");
		JMenuItem stash_apply = new JMenuItem("Apply Stashed Changes");
		JMenuItem branch = new JMenuItem("Create Branch");
		JMenuItem checkout = new JMenuItem("Change Branch");
		JMenuItem igore = new JMenuItem("Ignore Files");

		git.add(init);
		git.add(snapshot);
		git.add(add);
		git.add(commit);
		git.add(diff);
		git.add(status);
		git.add(push);
		git.add(revert);
		git.add(remove);
		git.add(ignore);
		git.add(rebase);
		git.add(stash);
		git.add(stash_list);
		git.add(stash_apply);
		git.add(branch);
		git.add(checkout);
		git.add(ignore);
	}

	private void populateSettingsMenu() {

	}

	private void populateOnlineMenu() {

	}

	private void populateHelpMenu() {

	}

}
