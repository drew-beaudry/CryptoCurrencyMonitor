package crypto.platform.view.coinlist.handler;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import crypto.model.Coin;
import crypto.platform.service.Service;
import crypto.platform.view.coinlist.CoinListActivator;

public class DoubleClickListener implements IDoubleClickListener {
  private static Service service = CoinListActivator.getService();

  @Override
  public void doubleClick(DoubleClickEvent event) {
    StructuredSelection selectionList = (StructuredSelection) event.getSelection();
    if (selectionList != null && selectionList instanceof IStructuredSelection) {
      Object obj = selectionList.getFirstElement();
      if (obj instanceof Coin) {
        service.setCurrency((Coin) obj);
      }
    }
  }
}
