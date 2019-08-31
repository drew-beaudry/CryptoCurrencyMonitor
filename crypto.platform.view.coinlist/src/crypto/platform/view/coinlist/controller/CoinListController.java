package crypto.platform.view.coinlist.controller;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;

import crypto.model.Coin;
import crypto.platform.dialog.view.CoinDialog;
import crypto.platform.dnd.CoinTransfer;
import crypto.platform.dnd.ListDragSourceListener;
import crypto.platform.dnd.ListDropAdapter;
import crypto.platform.ui.api.controller.ComponentController;

//Manage what component does - not creation
//  Set input
//    AddCoin()

public class CoinListController extends ComponentController {

  private final ListViewer viewer;
  private final Button add;
  private List<Coin> list;

  public CoinListController(Composite parent, ListViewer viewer, Button add) {
    super(parent);
    this.viewer = viewer;
    this.add = add;
  }

  public void addAddButtonSelectionListener(Listener listener){
    add.addListener(SWT.Selection, listener);
  }
  
  @SuppressWarnings("unchecked")
  public void setInput(Object input) {
    viewer.setInput(input);
    list = (List<Coin>) input; 
  }
  
  public void refreshViewer(){
    viewer.refresh();
  }
  
  public Viewer getViewer(){
    return this.viewer;
  }
  
  public void setMenu(IWorkbenchPartSite site){
    // Set the menu on the SWT widget
    MenuManager menuManager = new MenuManager();
    Menu menu = menuManager.createContextMenu(viewer.getList());
    viewer.getList().setMenu(menu);

    // register the menu with the framework
    site.registerContextMenu(menuManager, viewer);

    // make the viewer selection available
    site.setSelectionProvider(viewer);
  }
  
  public void addDragDrop(){

    // Add drag functionality
    int operations = DND.DROP_COPY;
    Transfer[] transferTypes = new Transfer[] {CoinTransfer.getInstance()};
    DragSourceListener listener = new ListDragSourceListener(viewer);
    viewer.addDragSupport(operations, transferTypes, listener);

    // Add drop functionality
    ListDropAdapter dropAdapter = new ListDropAdapter(viewer);
    DropTarget dropTarget = new DropTarget(viewer.getControl(), operations);
    dropTarget.setTransfer(transferTypes);
    dropTarget.addDropListener(dropAdapter);
  }
  
  public void addCoin(Object coin){
    list.add((Coin) coin);
    Collections.sort(list);
    refreshViewer();
  }
  
  public void removeCoin(Object coin){
    list.remove(coin);
    refreshViewer();
  }
  
  public void openDialog(){
    CoinDialog dialog = new CoinDialog();
    dialog.create();
    dialog.open();
  }
}
