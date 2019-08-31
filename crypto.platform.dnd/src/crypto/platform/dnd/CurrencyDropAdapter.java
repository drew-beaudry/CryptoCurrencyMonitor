package crypto.platform.dnd;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import crypto.model.Coin;
import crypto.platform.service.Service;

/**Handles dropping Coin objects into OptionPaneView*/
public class CurrencyDropAdapter extends DropTargetAdapter {

  private final Text text;
  private Display display = Display.getCurrent();
  private static Service service = Activator.getService();

  public CurrencyDropAdapter(Text text) {
    this.text = text;
  }
  
  @Override
  public void dragEnter(DropTargetEvent event) {
    super.dragEnter(event);
  }

  @Override
  public void drop(DropTargetEvent event) {
    text.setText(((Coin) event.data).getName());
    text.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    service.setCurrency((Coin)event.data);
    super.drop(event);
  }

  @Override
  public void dragLeave(DropTargetEvent event) {
    text.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
    super.dragLeave(event);
  }

  @Override
  public void dragOperationChanged(DropTargetEvent event) {
    super.dragOperationChanged(event);
  }

  @Override
  public void dragOver(DropTargetEvent event) {
    event.detail = DND.DROP_COPY;
    super.dragOver(event);
    text.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
  }

  @Override
  public void dropAccept(DropTargetEvent event) {
    super.dropAccept(event);
  }
}
