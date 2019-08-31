package crypto.platform.view.optionpane;

import java.util.TimerTask;

import org.eclipse.swt.widgets.Display;

import crypto.platform.service.Service;

/**Helps handle the delaying of creating the chart until the user has finished modifying
 * the Limit Spinner Widget*/
public class CreateChartTask extends TimerTask {
  private Service service = Activator.getService();

  @Override
  /**Creates the chart asynchronously*/
  public void run() {
    Display.getDefault()
        .asyncExec(
            new Runnable() {
              public void run() {
                service.createChart();
              }
            });
  }
}
