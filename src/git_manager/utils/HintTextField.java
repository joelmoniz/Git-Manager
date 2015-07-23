package git_manager.utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * Adapted from: http://stackoverflow.com/a/1739037/2427542
 */
public class HintTextField extends JTextField implements FocusListener {

  private final String hint;
  private boolean showingHint;
  private boolean hintEnabled;

  public HintTextField(final String hint) {
    super(hint);
    this.hint = hint;
    this.showingHint = true;
    this.hintEnabled = true;
    super.addFocusListener(this);
  }
  
  public void disableHint() {
    this.hintEnabled = false;
  }

  @Override
  public void focusGained(FocusEvent e) {
    if(hintEnabled && this.getText().isEmpty()) {
      super.setText("");
      showingHint = false;
    }
  }
  @Override
  public void focusLost(FocusEvent e) {
    if(hintEnabled && this.getText().isEmpty()) {
      super.setText(hint);
      showingHint = true;
    }
  }

  @Override
  public String getText() {
    return (showingHint && hintEnabled) ? "" : super.getText();
  }
}