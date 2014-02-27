package git_manager.tool;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.GitCommand;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import processing.app.Editor;

public class GitOperations {

	Editor editor;
	File gitDir;
	File thisDir;
	Git git;

	public GitOperations(Editor editor) {
		this.editor = editor;
		gitDir = new File(editor.getSketch().getFolder().getAbsolutePath()
				+ "\\.git");
		thisDir = new File(editor.getSketch().getFolder().getAbsolutePath());
		try {
			git = new Git(new FileRepository(gitDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initRepo() {
		if (gitDir.exists()) {
			System.out.println("Repo already exists!");
			return;
		}

		try {
			System.out.println(gitDir.getAbsolutePath());
			Git.init().setDirectory(thisDir).setBare(false).call();
			System.out.println("New repo created.");
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Retains the details of the last editor when a new one is opened.
		// Fix this
	}

	public void initBareRepo() {
		Repository repo;
		try {
			repo = new FileRepository(thisDir);
			repo.create(true);
			System.out.println("Bare repo created.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cloneRepo(File cloneFrom, File cloneTo) {
		try {
			Git.cloneRepository().setURI(cloneFrom.getAbsolutePath())
					.setDirectory(cloneTo).setBranch("master").setBare(false)
					.setRemote("origin").setNoCheckout(false).call();
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAndCommit(String comment) {

		try {
			git.add().addFilepattern(".").call();
			git.commit().setMessage(comment).call();
			System.out.println("Snapshot complete");
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addFiles() {

		try {
			git.add().addFilepattern(".").call();
			System.out.println("All files added");
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void commitChanges(String comment) {
		try {
			git.commit().setMessage(comment).call();
			System.out.println("Commit complete");
		} catch (NoHeadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnmergedPathsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pushToRemote(String uname,String pass,String remote)
	{
	
		// Get TransportException when project is >1Mb
		// Fix this: https://groups.google.com/forum/#!topic/bitbucket-users/OUsa8sb_Ti4
		CredentialsProvider cp = new UsernamePasswordCredentialsProvider(uname,pass);

		Iterable<PushResult> pc;
		try {
			pc = git.push().setRemote(remote).setCredentialsProvider(cp).call();
			for (PushResult pushResult : pc) {
				System.out.println(pushResult.getURI());
				}
			System.out.println("Push Complete");

		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
