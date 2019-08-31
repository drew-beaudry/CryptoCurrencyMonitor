/*package crypto.platform.view.table.view;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import crypto.platform.view.table.component.TableComponent;
import crypto.platform.view.table.controller.TableController;

public class TableDialog extends TitleAreaDialog {
  private TableController controller;

  public TableDialog(Shell parentShell) {
    super(parentShell);
  }

  public void create() {
    super.create();
    setTitle("Crypo-Currency Monitor");
    // setMessage("");
  }

  // ITableLabelProvider
  protected Control createDialogArea(Composite parent) {
    parent.addDisposeListener(
        new DisposeListener() {
          @Override
          public void widgetDisposed(DisposeEvent e) {
            controller.dispose();
          }
        });
    Composite area = (Composite) super.createDialogArea(parent);
    Composite container = new Composite(area, SWT.NONE);
    controller = new TableComponent().create(container);
    controller.createColumns();
    controller.setInput();
    controller.refresh();
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
    super.okPressed();
  }

  @Override
  protected void cancelPressed() {
    setReturnCode(CANCEL);
    close();
  }
}
*/