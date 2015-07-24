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
	private boolean isAuthValid;

	public GitGHOperations() {
		isAuthValid = false;
		getAuthenticationDetails();
		if (isAuthValid) {
			basicAuthentication();
			forkRepo();
		}
	}

	public void getAuthenticationDetails() {
		// TODO: Make the JOptionPane look more organized
		JTextField uName = new JTextField(15);
		JPasswordField pass = new JPasswordField(15);
		JTextField remoteOwner = new JTextField(15);
		JTextField remoteName = new JTextField(15);

		System.out
				.println("Proof-of-concept: Only forking a GitHub repo has been implemented...");

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
			if (this.uName.equals("") || this.pass.equals("")
					|| this.remoteOwner.equals("")
					|| this.remoteName.equals("")) {
				if (this.uName.equals(""))
					System.out.println("Please enter a valid username");

				if (this.pass.equals(""))
					System.out.println("Please enter a valid password");

				if (this.remoteName.equals(""))
					System.out
							.println("Please enter a valid online repository name");

				if (this.remoteOwner.equals(""))
					System.out
							.println("Please enter a valid repository owner name");
				this.uName = this.pass = this.remoteOwner = this.remoteName = null;
				return;
			}
			isAuthValid = true;
		} else
			System.out.println("Fork cancelled.");
	}

	// following functions implemented from readme in the page located here:
	//
	public void basicAuthentication() {
		// Basic authentication
		client = new GitHubClient();
		client.setCredentials(uName, pass);
	}

	public void forkRepo() {
		RepositoryService service = new RepositoryService();
		service.getClient().setCredentials(uName, pass);
		RepositoryId toBeForked = new RepositoryId(remoteOwner, remoteName);
		try {
			service.forkRepository(toBeForked);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(remoteName + " forked.");
		// TODO: Make uName, pass, remoteName, remoteOwner null if needed
	}
}
