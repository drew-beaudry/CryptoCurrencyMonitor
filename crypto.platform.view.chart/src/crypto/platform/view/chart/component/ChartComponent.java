package crypto.platform.view.chart.component;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import crypto.platform.ui.api.component.IComponent;
import crypto.platform.view.chart.controller.ChartController;

public class ChartComponent implements IComponent {

  @Override
  public ChartController create(Composite composite) {
    composite.setLayout(new GridLayout(1,false));
    return new ChartController(composite);
  }}
