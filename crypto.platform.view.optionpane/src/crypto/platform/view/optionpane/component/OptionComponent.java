package crypto.platform.view.optionpane.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import crypto.platform.service.Service;
import crypto.platform.ui.api.component.IComponent;
import crypto.platform.view.optionpane.Activator;
import crypto.platform.view.optionpane.controller.OptionController;

public class OptionComponent implements IComponent {
  private Service service = Activator.getService();

  @Override
  public OptionController create(Composite composite) {
    composite.setLayout(new GridLayout(4, false));
    Text currency = new Text(composite, SWT.CENTER | SWT.BORDER);
    Text chart = new Text(composite, SWT.CENTER | SWT.BORDER);
    Label label = new Label(composite, SWT.NONE);
    Spinner limitSpinner = new Spinner(composite, SWT.BORDER);
    GridData dataInputField = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);

    label.setText("Days: ");
    limitSpinner.setMinimum(5);
    limitSpinner.setMaximum(60);
    limitSpinner.setSelection(15);
    limitSpinner.setIncrement(1);
    service.setLimit("14");

    currency.setText("Currency");
    chart.setText("Graph Type");
    currency.setEditable(false);
    chart.setEditable(false);
    currency.setLayoutData(dataInputField);
    chart.setLayoutData(dataInputField);

    return new OptionController(
        composite, currency, chart, label, limitSpinner);
  }
}
