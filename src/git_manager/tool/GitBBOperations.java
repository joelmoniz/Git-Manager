package git_manager.tool;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GitBBOperations {

	public GitBBOperations() {
		System.out
				.println("Proof-of-concept: Only viewing issues in a BitBucket repo has been implemented...");
		viewIssueList();
	}

	public void viewIssueList() {
		JTextField accName = new JTextField(15);
		JTextField repoSlug = new JTextField(15);
		
		String acc = null, repo = null;

		JPanel panel = new JPanel(new GridLayout(0, 1));
		JPanel un = new JPanel();
		JPanel un3 = new JPanel();
		
		un.add(new JLabel("Owner's Account Name:   "));
		un.add(accName);
		panel.add(un);

		un3.add(new JLabel("Repo Name:"));
		un3.add(repoSlug);
		panel.add(un3);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"View BitBucket repository's issue list", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			acc = accName.getText();
			repo = repoSlug.getText();
		}
		
		if (acc == null || repo == null)
		{	System.out.println("View Issue List Cancelled");
		return;
		}
		
		if (acc.equals("") || repo.equals(""))
		{	if (acc.equals(""))
			System.out.println("Please enter an account name.");
		if (repo.equals(""))
				System.out.println("Please enter an repository name.");
		acc = repo = null;
		return;
		}

	}
}
