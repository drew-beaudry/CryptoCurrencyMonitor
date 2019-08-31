package crypto.platform.view.optionpane.controller;

import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import crypto.platform.dnd.ChartDropAdapter;
import crypto.platform.dnd.ChartTransfer;
import crypto.platform.dnd.CoinTransfer;
import crypto.platform.dnd.CurrencyDropAdapter;
import crypto.platform.service.Service;
import crypto.platform.ui.api.controller.ComponentController;
import crypto.platform.view.optionpane.Activator;
import crypto.platform.view.optionpane.CreateChartTask;

public class OptionController extends ComponentController {
  private Composite composite;
  private Text currency, chart;
  private Label label;
  private Spinner limitSpinner;
  Timer timer = new Timer();
  private Service service = Activator.getService();

  public OptionController(
      Composite parent,
      Text currency,
      Text chart,
      Label label,
      Spinner limitSpinner) {
    super(parent);
    this.composite = parent;
    this.currency = currency;
    this.chart = chart;
    this.label = label;
    this.limitSpinner = limitSpinner;
  }

  public void addDrop() {
    // Add drop functionality
    int operations = DND.DROP_COPY;
    Transfer[] coinTransferType = new Transfer[] {CoinTransfer.getInstance()};
    Transfer[] chartTransferType = new Transfer[] {ChartTransfer.getInstance()};

    CurrencyDropAdapter currencyAdapter = new CurrencyDropAdapter(currency);
    ChartDropAdapter chartAdapter = new ChartDropAdapter(chart);

    DropTarget currencyTarget = new DropTarget(currency, operations);
    DropTarget chartTarget = new DropTarget(chart, operations);

    currencyTarget.setTransfer(coinTransferType);
    chartTarget.setTransfer(chartTransferType);
    currencyTarget.addDropListener(currencyAdapter);
    chartTarget.addDropListener(chartAdapter);
  }

  public void addSpinnerListener() {
    limitSpinner.addListener(
        SWT.Modify,
        e -> {
          try {
            int value = Integer.parseInt(limitSpinner.getText());
            if (value > limitSpinner.getMaximum() || value < limitSpinner.getMinimum()) {
              limitSpinner.setSelection(15);
            }
          } catch (NumberFormatException ex) {
            limitSpinner.setSelection(15);
          }
          // Add a modification timer - create the chart after
          // the user hasn't modified the spinner for two seconds
          timer.cancel();
          service.setLimit(Integer.toString(limitSpinner.getSelection() - 1));
          timer = new Timer();
          timer.schedule(new CreateChartTask(), 2000);
        });
  }

  public void addServiceListeners() {
    service.addCurrencyChangeListener(
        (c) -> {
          currency.setText(c.getName());
        });
    service.addChartChangeListener(
        (c) -> {
          chart.setText(c.getName());
        });
    service.addDisposeCoinListener(
        (c) -> {
          //If the active currency was removed from the user's list
          //Refresh the Currency text box
          if (service.getCurrency() != null && service.getCurrency().equals(c)) currency.setText("Currency");
        });
  }
}
