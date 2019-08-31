package crypto.platform.view.coinlist.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.handlers.HandlerUtil;

import crypto.model.Coin;
import crypto.platform.service.Service;
import crypto.platform.view.coinlist.CoinListActivator;
import crypto.platform.view.coinlist.view.CoinListView;

/**Handles right click -> Set Currency events*/
public class SetCurrencyHandler extends AbstractHandler {
  private static Service service = CoinListActivator.getService();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // Retrieve Viewer
    Viewer viewer =
        ((CoinListView) HandlerUtil.getActiveSite(event).getPage().findView(CoinListView.ID))
            .getViewer();

    // Gets Selection - Removes each
    StructuredSelection selectionList = (StructuredSelection) viewer.getSelection();
    if (selectionList != null && selectionList instanceof IStructuredSelection) {
      Object obj = selectionList.getFirstElement();
      if (obj instanceof Coin) {
        service.setCurrency((Coin) obj);
      }
    }
    viewer.refresh();
    return null;
  }
}
