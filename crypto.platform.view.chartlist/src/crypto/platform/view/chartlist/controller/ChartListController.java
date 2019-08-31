package crypto.platform.view.chartlist.controller;

import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;

import crypto.model.Chart;
import crypto.platform.dnd.ChartTransfer;
import crypto.platform.dnd.ListDragSourceListener;
import crypto.platform.dnd.ListDropAdapter;
import crypto.platform.ui.api.controller.ComponentController;

public class ChartListController extends ComponentController {

  private ListViewer viewer;
  private List<Chart> list;

  public ChartListController(Composite parent, ListViewer viewer) {
    super(parent);
    this.viewer = viewer;
  }

  @SuppressWarnings("unchecked")
  public void setInput(Object input) {
    viewer.setInput(input);
    list = (List<Chart>) input;
  }

  public void refreshViewer() {
    viewer.refresh();
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
    Transfer[] transferTypes = new Transfer[] {ChartTransfer.getInstance()};
    DragSourceListener listener = new ListDragSourceListener(viewer);
    viewer.addDragSupport(operations, transferTypes, listener);

    // Add drop functionality
    ListDropAdapter dropAdapter = new ListDropAdapter(viewer);
    DropTarget dropTarget = new DropTarget(viewer.getControl(), operations);
    dropTarget.setTransfer(transferTypes);
    dropTarget.addDropListener(dropAdapter);
  }

  public Viewer getViewer() {
   return viewer;
  }
}
