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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GitBBOperations {

	public GitBBOperations() {
		System.out
				.println("Proof-of-concept: Only viewing number of issues in a BitBucket repo has been implemented...");
		viewIssueNumber();
	}

	// TODO: The viewIssueNumber() takes ridiculously long (~15minutes?) to
	// update when a
	// new issue is added, as well as when an issue is
	// deleted, though the JSON data takes no time at all to update in either
	// case. Find
	// out whats wrong when adding an issue on bitbucket.

	public void viewIssueNumber() {
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
				"View BitBucket repository's issue list",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			acc = accName.getText();
			repo = repoSlug.getText();
		}

		if (acc == null || repo == null) {
			System.out.println("View Issue List Cancelled");
			return;
		}

		if (acc.equals("") || repo.equals("")) {
			if (acc.equals(""))
				System.out.println("Please enter an account name.");
			if (repo.equals(""))
				System.out.println("Please enter an repository name.");
			acc = repo = null;
			return;
		}

		URL url;
		try {
			url = new URL("https://bitbucket.org/api/1.0/repositories/" + acc
					+ "/" + repo + "/issues");

			BufferedReader reader = null;

			try {
				reader = new BufferedReader(new InputStreamReader(
						url.openStream(), "UTF-8"));
				String bitBucketData = "";
				for (String line; (line = reader.readLine()) != null;) {
					System.out.println(line);
					bitBucketData += line;
				}
				// To get the number of issues in the repo

				JsonParser parser = new JsonParser();
				JsonObject obj = parser.parse(bitBucketData).getAsJsonObject();
				int issueNum = obj.get("count").getAsInt();
				System.out.println("There are " + issueNum + " issues in the "
						+ repo + " repository");
				issueNum = 0;
				bitBucketData = null;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null)
					try {
						reader.close();
					} catch (IOException ignore) {
					}
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

}
