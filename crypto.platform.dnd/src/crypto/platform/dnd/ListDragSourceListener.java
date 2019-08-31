package crypto.platform.dnd;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

public class ListDragSourceListener implements DragSourceListener {
  private Object selection;

  private ListViewer listViewer;

  public ListDragSourceListener(ListViewer listViewer) {
    this.listViewer = listViewer;
  }

  public void dragStart(DragSourceEvent event) {
    StructuredSelection selectionList = (StructuredSelection) listViewer.getSelection();
    selection = selectionList.getFirstElement();

    if (selection == null) {
      event.doit = false;
      event.detail = DND.DROP_NONE;
    }
  }

  public void dragSetData(DragSourceEvent event) {
    StructuredSelection selectionList = (StructuredSelection) listViewer.getSelection();
    selection = selectionList.getFirstElement();

    // Provide the data of the requested type.
    if (CoinTransfer.getInstance().isSupportedType(event.dataType)) {
      event.data = selection;
    } else if (ChartTransfer.getInstance().isSupportedType(event.dataType)) {
      event.data = selection;
    }
  }

  public void dragFinished(DragSourceEvent event) {}
}
