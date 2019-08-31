package crypto.platform.view.chartlist.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.handlers.HandlerUtil;

import crypto.model.Chart;
import crypto.platform.service.Service;
import crypto.platform.view.chartlist.ChartListActivator;
import crypto.platform.view.chartlist.view.ChartListView;

public class SetActiveChart extends AbstractHandler {
  private static Service service = ChartListActivator.getService();
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // Retrieve Viewer
    Viewer viewer =
        ((ChartListView) HandlerUtil.getActiveSite(event).getPage().findView(ChartListView.ID))
            .getViewer();

    // Gets Selection - Removes each
    StructuredSelection selectionList = (StructuredSelection) viewer.getSelection();
    if (selectionList != null && selectionList instanceof IStructuredSelection) {
      Object obj = selectionList.getFirstElement();
      if (obj instanceof Chart) {
        service.setChart((Chart) obj);
      }
    }
    viewer.refresh();
    return null;
  }
}
