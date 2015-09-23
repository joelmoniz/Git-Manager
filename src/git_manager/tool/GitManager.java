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

import git_manager.constants.ProjectDetails;

import java.awt.EventQueue;

import processing.app.Base;
import processing.app.tools.Tool;
import processing.app.ui.Editor;

public class GitManager implements Tool {

	Editor editor;
	static GitGUIFrame frame;

	public String getMenuTitle() {
		return "Git Manager";
	}

//	public void init(Editor theEditor) {
//		editor = theEditor;
//	}

	public void run() {
		try {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if (frame == null) {
							System.out
									.println("*-----*-----*-----*-----*-----*-----*-----*-----*-----*\n"
											+ "|	       "
											+ ProjectDetails.NAME
											+ " v"
											+ ProjectDetails.VERSION
											+ "    	             |\n"
											+ "| 	   by "
											+ ProjectDetails.AUTHOR
											+ "	             |\n|           "
											+ ProjectDetails.WEBSITE
											+ "         |\n"
											+ "*-----*-----*-----*-----*-----*-----*-----*-----*-----*"

									);
							frame = new GitGUIFrame(editor);
						}
					} catch (Exception e) {
						System.err
								.println("Exception at GitManager.run() - invokeLater - run");
						e.printStackTrace();
					}
				}
			});
		} catch (Exception eOuter) {
			System.err.println("Exception at GitManager.run() - invokeLater");
			eOuter.printStackTrace();
		}
	}

	// TODO: Use base instead of Editor, to keep track of active Editor
//  @Override
  public void init(Base base) {
    editor = base.getActiveEditor();    
  }
  
  // HACK: This is kept in to keep things compatible with
  // processing 3.0 b 4-6
  public void init(Editor editor) {
    this.editor = editor;    
  }

}
