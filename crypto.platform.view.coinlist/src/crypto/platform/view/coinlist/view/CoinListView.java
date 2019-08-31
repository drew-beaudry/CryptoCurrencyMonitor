package crypto.platform.view.coinlist.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.part.ViewPart;

import crypto.model.Coin;
import crypto.platform.service.Service;
import crypto.platform.view.coinlist.CoinListActivator;
import crypto.platform.view.coinlist.component.CoinListComponent;
import crypto.platform.view.coinlist.controller.CoinListController;

/**ListViewer where list of user added currencies is shown*/
public class CoinListView extends ViewPart {
  public static final String ID = "crypto.platform.view.coinlist.view";
  private static Service service = CoinListActivator.getService();
  CoinListController controller;

  @Override
  public void createPartControl(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);

    List<Coin> list = new ArrayList<>();

    controller = new CoinListComponent().create(composite);
    controller.setInput(list);
    controller.addDragDrop();
    controller.addAddButtonSelectionListener(
        e -> {
          controller.openDialog();
        });
    service.addNewCoinListener(
        (c) -> {
          controller.addCoin(c);
        });
    service.addDisposeCoinListener(
        (c) -> {
          controller.removeCoin(c);
        });
    controller.setMenu(getSite());
    
    IWorkbench workbench = PlatformUI.getWorkbench();
    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
     
    //Create second perspective
    try {
      workbench.showPerspective("crypto.application.liveperspective", window);
      workbench.showPerspective("crypto.application.perspective", window);
    } catch (WorkbenchException e1) {
      e1.printStackTrace();
    }
  }

  public Viewer getViewer(){
    return controller.getViewer();
  }
  
  @Override
  public void setFocus() {}
}
