package git_manager.tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;


public class GitOptionToolbar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public GitOptionToolbar() {
		
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 40);
		this.setVisible(true);
		this.setFloatable(false);
		populateToolBar();
		
	}
	
	

	private void populateToolBar() {
		// TODO Auto-generated method stub
		JButton b = new JButton("Test");
		this.add(b);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}


// @SuppressWarnings("serial")
// public class GitOptionToolbar extends JavaToolbar {
//
// public GitOptionToolbar(Editor editor, Base base) {
// super(editor, base);
// // TODO Auto-generated constructor stub
// }
//
//
// }