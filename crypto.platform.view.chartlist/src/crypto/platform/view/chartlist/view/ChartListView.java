package crypto.platform.view.chartlist.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import crypto.api.cryptocompare.constants.Constants;
import crypto.model.Chart;
import crypto.platform.view.chartlist.component.ChartListComponent;
import crypto.platform.view.chartlist.controller.ChartListController;

/** ListViewer which displays the types of possible charts to the user */
public class ChartListView extends ViewPart {

  public static final String ID = "crypto.platform.view.chartlist.view";
  ChartListController controller;

  @Override
  public void createPartControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    List<Chart> list = new ArrayList<>();
    list.add(new Chart(Constants.BARGRAPH));
    list.add(new Chart(Constants.LINEGRAPH));
    list.add(new Chart(Constants.CANDLESTICKCHART));
    controller = new ChartListComponent().create(composite);

    controller.setInput(list);
    controller.addDragDrop();
    controller.setMenu(getSite());
  }

  @Override
  public void setFocus() {}

  public Viewer getViewer() {
    return controller.getViewer();
  }
}
