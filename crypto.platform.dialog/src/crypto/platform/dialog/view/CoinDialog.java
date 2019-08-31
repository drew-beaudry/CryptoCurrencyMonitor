package crypto.platform.dialog.view;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import crypto.platform.dialog.component.DialogComponent;
import crypto.platform.dialog.controller.DialogController;

/**
 * TitleAreaDialog that opens when user presses the 'Add' button 
 * to add a coin from the main perspective
 */
public class CoinDialog extends TitleAreaDialog {
  private Object input;
  private DialogController controller;

  public CoinDialog() {
    super(Display.getCurrent().getActiveShell());
  }

  // Create dialog window
  @Override
  public void create() {
    super.create();
    setTitle("Add CryptoCurrency");
    setMessage("Add a Currency Type");
    setBlockOnOpen(false);
  }

  // Create dialog area
  @Override
  protected Control createDialogArea(Composite parent) {
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    controller = new DialogComponent().create(container);
    controller.addInputFieldListener(
        e -> {
          controller.validateInput(getButton(IDialogConstants.OK_ID));
        });
    return area;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  // Prevents loss of data upon window closing
  private void saveInput() {
    input = controller.getText();
  }

  public Object getInput() {
    return input;
  }

  // Calls saveInput() upon pressing of the OK button
  @Override
  protected void okPressed() {
    // If the user presses OK and there is text in the field
    // add a coin
    if (!controller.getText().isEmpty()) controller.addCoin();
    saveInput();
    super.okPressed();
  }

  // No current use
  @Override
  protected void cancelPressed() {
    setReturnCode(CANCEL);
    close();
  }
}
