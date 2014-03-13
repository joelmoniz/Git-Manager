package git_manager.tool;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class GitOptionToolbar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	public GitOptionToolbar() {
		this.setName("ActionBar");
		this.setBounds(0, 0, 400, 40);
		this.setVisible(true);
		this.setFloatable(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.black);
		populateToolBar();

	}

	private void populateToolBar() {

		JButton b = new JButton();
		b.setToolTipText("Initialize repo");
		ImageIcon init = new ImageIcon(this.getClass().getResource(
				"/data/toolbar/gitinit.png"));
		b.setIcon(init);
		b.setSelected(false);
		this.add(b);
		JButton button = new JButton("a");
		this.add(button);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
