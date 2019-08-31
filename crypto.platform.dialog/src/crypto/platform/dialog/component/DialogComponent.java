package crypto.platform.dialog.component;

import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import crypto.model.CoinList;
import crypto.platform.dialog.DialogActivator;
import crypto.platform.dialog.controller.DialogController;
import crypto.platform.service.Service;
import crypto.platform.ui.api.component.IComponent;

public class DialogComponent implements IComponent {

  private static Service service = DialogActivator.getService();

  @Override
  public DialogController create(Composite container) {
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);
    container.setLayout(new GridLayout(3, false));

    Label label = new Label(container, SWT.NONE);
    label.setText("Input: ");

    GridData dataInputField = new GridData();
    dataInputField.grabExcessHorizontalSpace = true;
    dataInputField.horizontalAlignment = GridData.FILL;

    CoinList coinList = service.getCoinList();

    Text inputField = new Text(container, SWT.BORDER);
    new AutoCompleteField(
        inputField, new TextContentAdapter(), coinList.getSymbolList().toArray(new String[0]));
    inputField.setLayoutData(dataInputField);

    Button add = new Button(container, SWT.PUSH);
    add.setText("Add");

    return new DialogController(container, inputField, add);
  }
}
