package git_manager.tool;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

public class GitGHOperations {

	GitHubClient client;
	private String uName;
	private String pass;
	private String remoteOwner;
	private String remoteName;

	public GitGHOperations() {
		getAuthenticationDetails();
		basicAuthentication();
		forkRepo();
	}

	public void getAuthenticationDetails() {
		// TODO: Make the JOptionPane look more organized
		JTextField uName = new JTextField(15);
		JPasswordField pass = new JPasswordField(15);
		JTextField remoteOwner = new JTextField(15);
		JTextField remoteName = new JTextField(15);

		System.out
				.println("Proof-of-concept: Only forking a repo has been implemented...");

		JPanel panel = new JPanel(new GridLayout(0, 1));
		JPanel un = new JPanel();
		JPanel un2 = new JPanel();
		JPanel un3 = new JPanel();
		JPanel un4 = new JPanel();
		un.add(new JLabel("Email:   "));
		un.add(uName);
		panel.add(un);
		un2.add(new JLabel("Password:   "));
		un2.add(pass);
		panel.add(un2);
		un3.add(new JLabel("Owner of GitHub repo to fork:"));
		un3.add(remoteOwner);
		panel.add(un3);
		un3.add(new JLabel("GitHub repo to fork:"));
		un3.add(remoteName);
		panel.add(un4);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Fork a GitHub repo", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.uName = uName.getText();
			this.pass = new String(pass.getPassword());
			this.remoteOwner = remoteOwner.getText();
			this.remoteName = remoteName.getText();
		}
	}

	// following functions implemented from readme in the page located here:
	//
	public void basicAuthentication() {
		// Basic authentication
		client = new GitHubClient();
		client.setCredentials(uName, pass);
	}

	public void forkRepo() {
		System.out.println(remoteName + " forked.");
		RepositoryService service = new RepositoryService();
		service.getClient().setCredentials(uName, pass);
		RepositoryId toBeForked = new RepositoryId(remoteOwner, remoteName);
		try {
			service.forkRepository(toBeForked);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
