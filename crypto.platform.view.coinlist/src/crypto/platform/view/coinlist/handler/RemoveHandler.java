package crypto.platform.view.coinlist.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.handlers.HandlerUtil;

import crypto.model.Coin;
import crypto.platform.service.Service;
import crypto.platform.view.coinlist.CoinListActivator;
import crypto.platform.view.coinlist.view.CoinListView;

/**Handles right click -> Remove events*/
public class RemoveHandler extends AbstractHandler {

  private static Service service= CoinListActivator.getService();

  @Override
  public Object execute(ExecutionEvent event) throws org.eclipse.core.commands.ExecutionException {
    // Retrieve Viewer
    Viewer viewer =
        ((CoinListView) HandlerUtil.getActiveSite(event).getPage().findView(CoinListView.ID)).getViewer();

    // Gets Selection - Removes each
    StructuredSelection selectionList = (StructuredSelection) viewer.getSelection();
    if (selectionList != null && selectionList instanceof IStructuredSelection)
      for (Object obj : ((IStructuredSelection) selectionList).toArray()) {
        if (obj instanceof Coin) {
          service.removeCoin((Coin) obj);
        }
      }
    viewer.refresh();
    return null;
  }
}
