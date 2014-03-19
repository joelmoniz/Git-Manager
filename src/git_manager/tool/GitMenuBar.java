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
		JMenuItem ignore = new JMenuItem("Ignore Files");
		JMenuItem rebase = new JMenuItem("Rebase");
		JMenuItem stash = new JMenuItem("Save into Stash");
		JMenuItem stash_list = new JMenuItem("View Stash");
		JMenuItem stash_apply = new JMenuItem("Apply Stashed Changes");
		JMenuItem branch = new JMenuItem("Create Branch");
		JMenuItem checkout = new JMenuItem("Change Branch");
		JMenuItem clone = new JMenuItem("Clone a repo");
		JMenuItem readMe = new JMenuItem("Add a Read Me file");

		git.add(init);
		git.add(snapshot);
		git.add(add);
		git.add(commit);
				
		git.add(push);
		git.add(revert);
		git.add(remove);
		
		git.add(rebase);
		git.add(stash);
		git.add(stash_list);
		git.add(stash_apply);
		git.add(branch);
		git.add(checkout);
		git.add(clone);
		
		git.add(diff);
		git.add(status);
		
		git.add(ignore);
		git.add(readMe);

	}

	private void populateSettingsMenu() {
		JMenuItem config = new JMenuItem("Git config");
		JMenuItem gitToolBarSet = new JMenuItem("Git Option Toolbar");
		JMenuItem expLevMenuSet = new JMenuItem("Expertise Level Menu");
		JMenuItem repoSet = new JMenuItem("Local Repository");
		JMenuItem onlineSet = new JMenuItem("Online Repository");

		settings.add(config);

		settings.addSeparator();

		settings.add(gitToolBarSet);
		settings.add(expLevMenuSet);

		settings.addSeparator();

		settings.add(repoSet);
		settings.add(onlineSet);
	}

	private void populateOnlineMenu() {
		JMenuItem gitHub = new JMenuItem("GitHub");
		JMenuItem bitBucket = new JMenuItem("BitBucket");
		JMenuItem gCode = new JMenuItem("Google Code");

		onlineRepos.add(gitHub);
		onlineRepos.add(bitBucket);
		onlineRepos.add(gCode);
	}

	private void populateHelpMenu() {
		JMenuItem gitTutorials = new JMenuItem("New to git?");
		JMenuItem gmtTutorials = new JMenuItem("GitManager Tool Tutorials");
		JMenuItem bug = new JMenuItem("Report Bug");
		JMenuItem featureEnhan = new JMenuItem("Request a feature");
		JMenuItem repoEnhan = new JMenuItem("Request an Online Repo");
		JMenuItem about = new JMenuItem("About...");

		help.add(gitTutorials);
		help.add(gmtTutorials);

		help.addSeparator();

		help.add(bug);
		help.add(featureEnhan);
		help.add(repoEnhan);

		help.addSeparator();

		help.add(about);
	}

}
