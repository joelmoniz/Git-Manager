/*
  Part of the Git Manager for Processing- http://joelmoniz.com/git-manager/ 
    
  A tool for the Processing - http://processing.org
  
  Copyright (c) 2015 Joel Moniz
  
  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 2
  of the License, or (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
  USA.
 */
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
		//this.add(onlineRepos); TODO: Uncomment  for online
		this.add(settings);
		this.add(help);
	}

	// TODO: Fill this up, and give options proper names
	// This is only a list as and when various useful options come into my mind
	// for now, not
	// necessarily complete (nor necessarily sufficiently meaningful)

	// TODO: Add option to work with remote

	// TODO: Provide a fetch option?

	// TODO: Provide tag-related options
	private void populateGitMenu() {

		JMenu submenuCommit = new JMenu("Edit Commits");
		JMenu submenuStash = new JMenu("Stash");
		JMenu submenuBranch = new JMenu("Branch");

		JMenuItem init = new JMenuItem("Initialize repo");
		JMenuItem add = new JMenuItem("Add");
		JMenuItem unstage = new JMenuItem("Unstage an added file");
		JMenuItem commit = new JMenuItem("Commit");
		JMenuItem amend = new JMenuItem("Modify last commit");
		JMenuItem checkoutFile = new JMenuItem("Unmodify a file");
		JMenuItem snapshot = new JMenuItem("Snapshot");
		JMenuItem diff = new JMenuItem("Difference");
		JMenuItem commitHistory = new JMenuItem("Commit History");
		JMenuItem push = new JMenuItem("Push");
		JMenuItem revert = new JMenuItem("Undo a commit");
		JMenuItem remove = new JMenuItem("Remove");
		JMenuItem status = new JMenuItem("Status");
		JMenuItem ignore = new JMenuItem("Ignore Files");

		JMenuItem rebaseSplit = new JMenuItem("Split a Commit");
		JMenuItem rebaseSquash = new JMenuItem("Squash Commits");
		JMenuItem rebaseChangeMessage = new JMenuItem("Change Commit Messages");
		JMenuItem rebaseReorder = new JMenuItem("Reorder Commits");

		JMenuItem stash = new JMenuItem("Save into Stash");
		JMenuItem stash_list = new JMenuItem("View Stash");
		JMenuItem stash_apply = new JMenuItem("Apply Stashed Changes");
		JMenuItem branch = new JMenuItem("Create Branch");
		JMenuItem checkoutBranch = new JMenuItem("Change Branch");
		JMenuItem mergeBranch = new JMenuItem("Merge Branch");
		JMenuItem deleteBranch = new JMenuItem("Delete Branch");
		JMenuItem clone = new JMenuItem("Clone a repo");
		JMenuItem readMe = new JMenuItem("Add a Read Me file");

		git.add(init);
		git.add(snapshot);
		git.add(add);
		git.add(commit);

		git.addSeparator();

		git.add(clone);
		git.add(push);

		git.addSeparator();

		git.add(checkoutFile);
		git.add(unstage);
		git.add(remove);

		git.addSeparator();

		submenuCommit.add(amend);
		submenuCommit.add(amend);
		submenuCommit.add(revert);
		submenuCommit.add(rebaseChangeMessage);
		submenuCommit.add(rebaseSplit);
		submenuCommit.add(rebaseSquash);
		submenuCommit.add(rebaseReorder);
		git.add(submenuCommit);

		git.addSeparator();

		submenuStash.add(stash);
		submenuStash.add(stash_list);
		submenuStash.add(stash_apply);
		git.add(submenuStash);

		git.addSeparator();

		submenuBranch.add(branch);
		submenuBranch.add(checkoutBranch);
		submenuBranch.add(mergeBranch);
		submenuBranch.add(deleteBranch);
		git.add(submenuBranch);

		git.addSeparator();

		git.add(diff);
		git.add(status);
		git.add(commitHistory);

		git.addSeparator();

		git.add(ignore);
		git.add(readMe);

	}

	private void populateSettingsMenu() {
		JMenuItem config = new JMenuItem("Specify Git Config Settings");
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
