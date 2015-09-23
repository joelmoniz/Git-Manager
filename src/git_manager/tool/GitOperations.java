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

import git_manager.utils.HintTextField;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.RevertCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import processing.app.Messages;
import processing.app.ui.Editor;

public class GitOperations {

  public static String GITIGNORE_LOCATION = "/data/code/sample_gitignore.txt";
	Editor editor;
	File gitDir;
	File thisDir;
	Git git;
	String uName, pass, remote;

	public GitOperations(Editor editor) {
		this.editor = editor;
//		if (Base.isLinux())
//			gitDir = new File(editor.getSketch().getFolder().getAbsolutePath()
//					+ "/.git");
//		else
		gitDir = new File(editor.getSketch().getFolder().getAbsolutePath(), ".git");
		thisDir = new File(editor.getSketch().getFolder().getAbsolutePath());
		try {
			git = new Git(new FileRepository(gitDir));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initRepo() {
		if (repoExists()) {
			System.out.println("Repo already exists!");
			return;
		}

		try {
//			System.out.println(gitDir.getAbsolutePath());
			Git.init().setDirectory(thisDir).setBare(false).call();
			getConfigParameters();
			createGitIgnore();
      System.out.println("New repo created.");
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	
	public boolean repoExists() {
	  return gitDir.exists();
	}
  
  public boolean hasCommit() {
    if (!repoExists())
      return false;
    Iterable<RevCommit> commits;
    try {
      commits = git.log().call();
      for( RevCommit commit : commits ) {
        return true;
      }
    } catch (NoHeadException e) {
      // This error keeps popping up when there are no commits
      // Don't wanna freak out/annoy the user
//      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public int getCommitCount() {
    if (!repoExists())
      return -1;
    Iterable<RevCommit> commits;
    int count = -1;
    try {
      commits = git.log().call();
      count = 0;
      for( RevCommit commit : commits ) {
        count++;
      }
    } catch (NoHeadException e) {
      // This error keeps popping up when there are no commits
      // Don't wanna freak out/annoy the user
      count = 0;
//      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return count;
  }
	
	// TODO: Ensure that repo name == sketchname
	public void createGitIgnore() {
	  InputStream sourceGitignore = this.getClass().getResourceAsStream(GITIGNORE_LOCATION);
	  File destGitignore = new File(thisDir.getAbsolutePath() + "\\.gitignore");
	  try {
	    if (!destGitignore.exists()) {
	      Files.copy(sourceGitignore, destGitignore.toPath());
	    }
    } catch (IOException e) {
      e.printStackTrace();
    }
	}
	
	public void getConfigParameters() {
	  StoredConfig config = git.getRepository().getConfig();
	  String name = config.getString("user", null, "name");
	  String email = config.getString("user", null, "email");
	  if (name != null && email != null) {
      Object[] options = { "Yes", "No" };
      // TODO: Probably get this on the EDT instead
      int n = JOptionPane.showOptionDialog(new JFrame(), "The following global "
          + "user details have been detected:\n    Name: " 
          + name + "\n    Email: " + email + "\n\nContinue with these details?",
          "Who's there?",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE, null,
          options, options[1]);
      if (n==JOptionPane.YES_OPTION) {
        return;
      }
	  }
	  
	  // TODO: Definitely make this nicer- better parsing and error handling, etc.
	  // TODO: On the EDT with this too!
	  // Adapted from http://www.edu4java.com/en/swing/swing3.html
	  name = "";
	  email = "";
	  while (name.isEmpty() || email.isEmpty()) {
      JPanel configPanel = new JPanel();
      configPanel.setLayout(null);
      configPanel.setPreferredSize(new Dimension(300, 80));
  
      JLabel nameLabel = new JLabel("Name");
      nameLabel.setBounds(10, 10, 80, 25);
      configPanel.add(nameLabel);
  
      JTextField nameText = new JTextField(20);
      nameText.setBounds(100, 10, 160, 25);
      configPanel.add(nameText);
      nameText.setText(name);
  
      JLabel emailLabel = new JLabel("Email");
      emailLabel.setBounds(10, 40, 80, 25);
      configPanel.add(emailLabel);
  
      JTextField emailText = new JTextField(20);
      emailText.setBounds(100, 40, 160, 25);
      configPanel.add(emailText);
      emailText.setText(email);
  
      JOptionPane.showMessageDialog(new JFrame(), 
                                    configPanel, "User details",
                                    JOptionPane.PLAIN_MESSAGE, null);
      
      name = nameText.getText();
      email = emailText.getText();
      
      if (name.isEmpty() || email.isEmpty()) {
        JOptionPane.showMessageDialog(new JFrame(), 
                                      "Neither the name nor the email address can be left blank", "Nopes",
                                      JOptionPane.ERROR_MESSAGE, null);   
      }
	  }
	  
    config.setString("user", null, "name", name);
    config.setString("user", null, "email", email);
    try {
      config.save();
    } catch (IOException e) {
      e.printStackTrace();
    }
	}

	public void initBareRepo() {
		Repository repo;
		try {
			repo = new FileRepository(thisDir);
			repo.create(true);
			System.out.println("Bare repo created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cloneRepo(File cloneFrom, File cloneTo) {
		try {
			Git.cloneRepository().setURI(cloneFrom.getAbsolutePath())
					.setDirectory(cloneTo).setBranch("master").setBare(false)
					.setRemote("origin").setNoCheckout(false).call();
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public void addAndCommit(String comment) {

		if (comment == null) {
			System.out.println("Commit Cancelled");
			return;
		}
		if (comment.equals("")) {
			int result = JOptionPane.showConfirmDialog(null,
					"You have not entered a commit message. Proceed anyway?",
					"No commit message warning", JOptionPane.OK_CANCEL_OPTION);

			if (result != JOptionPane.OK_OPTION) {
				System.out.println("Commit Cancelled");
				return;
			}
		}

		try {
			git.add().addFilepattern(".").call();
			git.commit().setMessage(comment).call();
			System.out.println("Snapshot complete");
		} catch (NoFilepatternException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public void addFiles() {

		try {
			git.add().addFilepattern(".").call();
			System.out.println("All files added");
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public void commitChanges(String comment) {

		if (comment == null) {
			System.out.println("Commit Cancelled");
			return;
		}
		if (comment.equals("")) {
			int result = JOptionPane.showConfirmDialog(null,
					"You have not entered a commit message. Proceed anyway?",
					"No commit message warning", JOptionPane.OK_CANCEL_OPTION);

			if (result != JOptionPane.OK_OPTION) {
				System.out.println("Commit Cancelled");
				return;
			}
		}
		try {
			git.commit().setMessage(comment).call();
			System.out.println("Commit complete");
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (NoMessageException e) {
			e.printStackTrace();
		} catch (UnmergedPathsException e) {
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public void pushToRemote() {

		if (uName == null || pass == null || remote == null) {
			System.out.println("Push cancelled");
			return;
		}
		if (uName.equals("") || pass.equals("") || remote.equals("")) {
			if (uName.equals(""))
				System.out.println("Please enter a valid username");

			if (pass.equals(""))
				System.out.println("Please enter a valid password");

			if (remote.equals(""))
				System.out
						.println("Please enter a valid online repository address");
			uName = pass = remote = null;
			return;
		}
		
		if (!remote.endsWith(".git")) {
		  remote = remote + ".git";
		}
		
		storeRemote();

		// Get TransportException when project is >1Mb
		// TODO: Fix this:
		// https://groups.google.com/forum/#!topic/bitbucket-users/OUsa8sb_Ti4
		CredentialsProvider cp = new UsernamePasswordCredentialsProvider(uName,
				pass);

		Iterable<PushResult> pc;
		try {
			pc = git.push().setRemote(remote).setCredentialsProvider(cp).call();
			for (PushResult pushResult : pc) {
				System.out.println(pushResult.getURI());
			}
			System.out.println("Push Complete");

		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			// System.out
			// .println("Please use a project of size <1MB when pushing (will be resolved soon)...");
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		} finally {
			uName = null;
			remote = null;
			pass = null;
		}

	}
	
  void storeRemote() {
    StoredConfig cofig = git.getRepository().getConfig();
    String configRemote = cofig.getString("remote", "origin", "url");
//	    String configUname = cofig.getString("remote", "origin", "url");

    if (configRemote == null || !configRemote.equals(remote)) {
      cofig.setString("remote", "origin", "url", remote);
      cofig.setString("remote", "origin", "fetch", "+refs/heads/*:refs/remotes/origin/*");
      try {
        cofig.save();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  void storeMergeBranch() {
    StoredConfig cofig = git.getRepository().getConfig();

    cofig.setString("branch", "master", "remote", "origin");
    cofig.setString("branch", "master", "merge", "refs/heads/master");
    try {
      cofig.save();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

	void getUnameandPass() {
		JTextField uName = new JTextField(15);
		JPasswordField pass = new JPasswordField(15);
		HintTextField remote = new HintTextField("https://github.com/uname/repo.git");

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
		un3.add(new JLabel("Repo:"));
		un3.add(remote);
		panel.add(un3);
		
		StoredConfig cofig = git.getRepository().getConfig();
		String remt = cofig.getString("remote", "origin", "url");
		
		if (remt != null && !remt.isEmpty()) {
		  remote.setText(remt);
		}

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Login Credentials", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.uName = uName.getText();
			this.pass = new String(pass.getPassword());
			this.remote = remote.getText();
		}
	}
	
	 boolean getRemote() {
	     HintTextField remote = new HintTextField("https://github.com/uname/repo.git");

	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    JPanel un3 = new JPanel();
	    un3.add(new JLabel("Repo:"));
	    un3.add(remote);
	    panel.add(un3);
	    
	    StoredConfig cofig = git.getRepository().getConfig();
	    String remt = cofig.getString("remote", "origin", "url");
	    
	    if (remt != null && !remt.isEmpty()) {
	      remote.setText(remt);
	      remote.disableHint();
	    }

	    int result = JOptionPane.showConfirmDialog(null, panel,
	        "Remote address", JOptionPane.OK_CANCEL_OPTION,
	        JOptionPane.PLAIN_MESSAGE);
	    if (result == JOptionPane.OK_OPTION) {
	      this.remote = remote.getText();
	    }
	    return (result == JOptionPane.OK_OPTION);
	  }
	
	 public void pullFromRemote() {
	   if (!getRemote()) {
	     System.out.println("Pull cancelled.");
	     return;
	   }
	   storeRemote();
	   storeMergeBranch();
	    PullResult result;
	    try {
	      result = git.pull().call();
	      FetchResult fetchResult = result.getFetchResult();
	      MergeResult mergeResult = result.getMergeResult();
	      // TODO: Handle merge conflicts :O
	      // TODO: Handle NoHeadException (repo not initialized)
	      // TODO: Handle InvalidConfigurationException (no pushes made till now- 
	      //       configure remote manually)
	      System.out.println(mergeResult.getMergeStatus());
//	      System.out.println("fetch status: " + fetchResult.getMessages());
	    } catch (InvalidRemoteException e) {
	      e.printStackTrace();
	    } catch (TransportException e) {
	      // System.out
	      // .println("Please use a project of size <1MB when pushing (will be resolved soon)...");
	      e.printStackTrace();
	    } catch (GitAPIException e) {
	      e.printStackTrace();
	    }
	  }
	 
	 public void displayStatus() {
	   Status status;
    try {
      status = git.status().call();
      if (status.isClean()) {
        System.out.println("No changes made in sketch since last commit");
      }
      else {
        Set<String> conflicts = status.getConflicting();
        Set<String> untracked = status.getUntracked();
        Set<String> added = status.getAdded();
        Set<String> changed = status.getChanged();
        Set<String> deleted = status.getRemoved();
//        deleted.addAll(status.getMissing());
        Set<String> missing = status.getMissing();
        Set<String> modified = status.getModified();
        
        // TODO: Explanations not technically sound- intended for first time users not fully familiar with git
        printFileStatus("Added", "Files not present in the last commit/snapshot, but will be added in the next commit", added);
        printFileStatus("Changed", "Files present in the last commit/snapshot whose modifications will added in the next commit", changed);
        printFileStatus("Deleted", "File whose deletion will be recorded in the next commit", deleted);
        printFileStatus("Missing", "File whose deletion will be not recorded in the next commit", missing);
        printFileStatus("Modified", "File present in the last snapshot/commit, subsequently modified, whose modifications will not yet be added in the next commit", modified);
        printFileStatus("Untracked", "New files not yet present in any commits", untracked);
        
        if (!conflicts.isEmpty()) {
          System.out.println("Warning: Conflicts detected in the following files");
          printFileStatus("", "", conflicts);
        }
      }
    } catch (NoWorkTreeException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
	   
	 }
	 
	 private void printFileStatus(String type, String description, Set<String> files) {
	   if (!files.isEmpty()) {
	     if (type != null && !type.isEmpty()) {
	       System.out.println("\n" + type + " Files (" + description + "):");
	     }
	     for (String f : files) {
	       System.out.println("\t" + f);
	     }
	   }
	 }
	 
	 public void resetHard() {
	   ResetCommand reset = git.reset();
	   reset.setRef(Constants.HEAD);
//	   reset.addPath(".");
	   reset.setMode(ResetType.HARD);
	   try {
      // Though this should actually have GitManager.frame as the parent, I think 
      // having editor as the parent is far more convenient, since I observe that I generally 
      // tend to keep tool window at the corner and the processing IDE in the centre, 
      // and prefer the dialog box displaying at the center... I think...
      int x = Messages.showYesNoQuestion(editor, "Reset sketch to previous commit?", "Are you sure you want to reset the entire skecthbook to<br>the exact state it was in the previous commit?", "All changes made since then will be permanently lost.");
      if (x == JOptionPane.YES_OPTION) {
        x = Messages.showYesNoQuestion(editor,   "Really reset sketch to previous commit?", "Are you absolutely, positively sure you want to reset the entire<br>skecthbook to the exact state it was in the previous commit?", "All changes made since then will be permanently lost, and even<br>git can't recover them.");
        if (x == JOptionPane.YES_OPTION) {
          reset.call();
          System.out.println("Hard reset completed. Sketch is now exactly like the last snapshot/commit.");
        }
      }
    } catch (CheckoutConflictException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
	 }
	
	// TODO: Finish off a proper diff function
  void printDiffWithHead() {
    Repository repository = git.getRepository();
    ObjectId oldHead;
    try {
      oldHead = repository.resolve("HEAD~1^{tree}");
      ObjectId head = repository.resolve("HEAD^{tree}");

      System.out.println("Printing diff between tree: " + oldHead + " and "
          + head);

      // prepare the two iterators to compute the diff between
      ObjectReader reader = repository.newObjectReader();
      CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
      oldTreeIter.reset(reader, oldHead);
      CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
      newTreeIter.reset(reader, head);

      // finally get the list of changed files
      List<DiffEntry> diffs = git.diff().setNewTree(newTreeIter)
          .setOldTree(oldTreeIter).call();
      for (DiffEntry entry : diffs) {
        System.out.println("Entry: " + entry);
      }
      System.out.println("----------------------");
      
      DiffFormatter df = new DiffFormatter( new ByteArrayOutputStream() );
      df.setRepository( git.getRepository() );
      List<DiffEntry> entries = df.scan( oldTreeIter, newTreeIter );

      for( DiffEntry entry : entries ) {
        System.out.println( entry );
      }
      
      System.out.println("Done");

      repository.close();
    } catch (RevisionSyntaxException e) {
      e.printStackTrace();
    } catch (AmbiguousObjectException e) {
      e.printStackTrace();
    } catch (IncorrectObjectTypeException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
  }
  
  // Source: https://github.com/centic9/jgit-cookbook/blob/master/src/main/java/org/dstadler/jgit/porcelain/ShowLog.java
  // Refer source for things like log for specific file, specific branch, etc.
  public void printLogs() {
    if (!hasCommit()) {
      System.out.println("\nNo commits exist yet. No logs to show.");
      return;
    }
    Iterable<RevCommit> logs = getLogs();
    
    if (logs != null) {
      int count = 0;
      System.out.println("\n");
      for (RevCommit rev : logs) {
          System.out.println("Commit ID: " + rev.getId().getName() + "\n" + 
                             "Author: " + rev.getAuthorIdent().toExternalString() + "\n" + 
                             "Date:   " + new Date(rev.getCommitTime()) + "\n\n" +
                             rev.getFullMessage() + "\n" + 
                             "----------------------------------------------------------");
          count++;
      }
      System.out.println("\nTotal " + count + " commits overall on current branch");
    }
  }
  
  public void revertCommit() {
    RevertCommand revert = git.revert();
    Iterable<RevCommit> logs = getLogs();
    
    if (logs != null) {
//      revert.include(logs.iterator().next());
      CommitCheckBox commitCheck = new CommitCheckBox(logs);
      if (commitCheck.logs.isEmpty()) {
        System.out.println("No commits selected to be undone");
      }
      else {
        try {
          System.out.println("The following commits will be undone:");
          for (RevCommit rc: commitCheck.logs) {
            revert.include(rc);
            System.out.println("  " + rc.getShortMessage());
          }
          revert.call();
          System.out.println("Done.");
        } catch (NoMessageException e) {
          e.printStackTrace();
        } catch (UnmergedPathsException e) {
          e.printStackTrace();
        } catch (ConcurrentRefUpdateException e) {
          e.printStackTrace();
        } catch (WrongRepositoryStateException e) {
          e.printStackTrace();
        } catch (GitAPIException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  class CommitCheckBox {
    
    ArrayList<RevCommit> logs;
    ArrayList<JCheckBox> checkboxList;
    JFrame frame;
    JPanel cbPanel;
    JScrollPane scrollPane;
    
    CommitCheckBox(Iterable<RevCommit> logsIterable) {
      Iterator<RevCommit> logs = logsIterable.iterator();
      this.logs = new ArrayList<>();
      this.checkboxList = new ArrayList<>();
      ArrayList<RevCommit> loglist = new ArrayList<>();
      frame = new JFrame();
      cbPanel = new JPanel();
      Dimension d = new Dimension(300, 400);
//      cbPanel.setPreferredSize(d);
//      cbPanel.setMaximumSize(d);
//      cbPanel.setMinimumSize(d);
      cbPanel.setLayout(new BoxLayout(cbPanel, BoxLayout.Y_AXIS));
      
      while (logs.hasNext()) {
        RevCommit log = logs.next();
        loglist.add(log);
        
        JCheckBox cb = new JCheckBox(log.getShortMessage());
        this.checkboxList.add(cb);
        cbPanel.add(cb);
      }
      
      scrollPane = new JScrollPane(cbPanel);
      scrollPane.setPreferredSize(d);
      scrollPane.setMaximumSize(d);
      scrollPane.setMinimumSize(d);
      scrollPane.getVerticalScrollBar().setUnitIncrement(16);
      Object[] options = { "Revert", "Cancel" };
      // TODO: Probably get this on the EDT instead
      int n = JOptionPane.showOptionDialog(new JFrame(), scrollPane, "Undo Commits",
                                           JOptionPane.YES_NO_OPTION,
                                           JOptionPane.QUESTION_MESSAGE, null,
                                           options, options[1]);
      if (n == JOptionPane.YES_OPTION) { // Afirmative
        //.... 
//        System.out.println("Yes!");
        // TODO: Is this a little hacky?
        int i = 0;
        for (JCheckBox c : checkboxList) {
          if (c.isSelected()) {
            this.logs.add(loglist.get(i));
          }
          i++;
        }
      }
      /*
      Do Nothing
      else if (n == JOptionPane.NO_OPTION) { // negative
        System.out.println("No!");
      }
      else if (n == JOptionPane.CLOSED_OPTION) { // closed the dialog
        System.out.println("Closed!");
      }
      */
    }
  }
  
  private Iterable<RevCommit> getLogs() {
    Iterable<RevCommit> logs = null;
    try {
        logs = git.log().call();
    } catch (NoHeadException e) {
      e.printStackTrace();
    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return logs;
  }
}
