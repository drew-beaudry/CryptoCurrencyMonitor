package crypto.platform.view.optionpane.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import crypto.platform.view.optionpane.component.OptionComponent;
import crypto.platform.view.optionpane.controller.OptionController;

/**
 * View where user can set type of currency and type of chart to draw, as well as historical data
 * limit parameter
 */
public class OptionPaneView extends ViewPart {

  public static final String ID = "crypto.platform.view.optionpane.view";

  @Override
  public void createPartControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    OptionController controller = new OptionComponent().create(composite);
    controller.addDrop();
    controller.addSpinnerListener();
    controller.addServiceListeners();
  }

  @Override
  public void setFocus() {}
}
