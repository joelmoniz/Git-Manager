package github_manager.tool;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

import processing.app.Editor;

public class GitOperations {

	Editor editor;

	public GitOperations(Editor editor) {
		this.editor = editor;
	}

	public void initRepo() {
		File targetDir = new File(editor.getSketch().getFolder()
				.getAbsolutePath()
				+ "/.git");
		if (targetDir.exists()) {
			System.out.println("Repo already exists!");
			// System.out.println(targetDir.getAbsolutePath());
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

		// Retains the details of the last editor when a new one is opened.
		// Fix this
	}

}
