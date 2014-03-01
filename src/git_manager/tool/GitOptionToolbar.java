package git_manager.tool;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import processing.app.Base;
import processing.app.Editor;
import processing.mode.java.JavaToolbar;


//public class GitOptionToolbar extends JToolBar implements ActionListener {
//
//	private static final long serialVersionUID = 1L;
//	
//	public GitOptionToolbar() {
//		
//		this.setName("ActionBar");
//		this.setBounds(0, 0, 400, 40);
//		this.setVisible(true);
//		this.setFloatable(false);
//		populateToolBar();
//		
//	}
//	
//	
//
//	private void populateToolBar() {
//		// TODO Auto-generated method stub
//		JButton b = new JButton("Test");
//		this.add(b);
//	}
//
//
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//}


 @SuppressWarnings("serial")
 public class GitOptionToolbar extends JavaToolbar {

 public GitOptionToolbar(Editor editor, Base base) {
 super(editor, base);
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 40);
		this.setVisible(true);
//		this.setFloatable(false);
		populateToolBar();
 }

	private void populateToolBar() {
	// TODO Auto-generated method stub
	JButton b = new JButton("Test");
	this.add(b);
}
	
	 @Override
	    public void handlePressed(MouseEvent e, int idx) {

	    }

 }