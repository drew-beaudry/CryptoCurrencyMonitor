package crypto.platform.view.chartlist.handler;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import crypto.model.Chart;
import crypto.platform.service.Service;
import crypto.platform.view.chartlist.ChartListActivator;

public class DoubleClickListener implements IDoubleClickListener {
  private static Service service = ChartListActivator.getService();

  @Override
  public void doubleClick(DoubleClickEvent event) {
    StructuredSelection selectionList = (StructuredSelection) event.getSelection();
    if (selectionList != null && selectionList instanceof IStructuredSelection) {
      Object obj = selectionList.getFirstElement();
      if (obj instanceof Chart) {
        service.setChart((Chart) obj);
      }
    }
  }
}
