/**
 * you can put a one sentence description of your tool here.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

package github_manager.tool;

import github_manager.constants.ProjectDetails;

import java.awt.EventQueue;

import javax.swing.JFrame;

import processing.app.Editor;
import processing.app.tools.Tool;

public class GitHubManager implements Tool {

	Editor editor;
	GUIFrame frame;

	public String getMenuTitle() {
		return "GitHubManager";
	}

	public void init(Editor theEditor) {
		editor = theEditor;
	}

	public void run() {
		System.out
				.println("*-----*-----*-----*-----*-----*-----*-----*-----*-----*\n"
						+ "|	       "
						+ ProjectDetails.NAME
						+ " v"
						+ ProjectDetails.VERSION
						+ "    	   |\n"
						+ "*-----*-----*-----*-----*-----*-----*-----*-----*-----*\n"

				);

		try {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if (frame == null) {
							frame = new GUIFrame();
						}

					} catch (Exception e) {
						System.err
								.println("Exception at GitHubManager.run() - invokeLater - run");
						e.printStackTrace();
					}
				}
			});
		} catch (Exception eOuter) {
			System.err
					.println("Exception at GitHubManager.run() - invokeLater");
			eOuter.printStackTrace();
		}
	}

}