package crypto.platform.dnd;

import java.util.List;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

import crypto.model.Coin;
import crypto.platform.exceptions.InvalidInputException;

public class ListDropAdapter extends ViewerDropAdapter {

  public ListDropAdapter(ListViewer viewer) {
    super(viewer);
  }
  @Override
  public boolean performDrop(Object data) {
   
    Object target = getCurrentTarget();
    ListViewer viewer = (ListViewer) getViewer();

    if (!(viewer.getInput() instanceof List) || !(data instanceof Coin))
      throw new InvalidInputException();

    @SuppressWarnings("unchecked")
    List<Coin> list = (List<Coin>) viewer.getInput();

    //If Coin already exists in list, abort
    if(list.indexOf(data) >= 0)
      return false;

    int location = getCurrentLocation();
    int index = list.indexOf(target);

    switch (location) {
      case LOCATION_ON:
        list.add(index + 1, (Coin) data);
        break;
      case LOCATION_BEFORE:
        list.add(index, (Coin) data);
        break;
      case LOCATION_AFTER:
        list.add(index + 1, (Coin) data);
        break;
      case LOCATION_NONE:
        list.add((Coin) data);
        break;
    }
    viewer.refresh();
    return true;
  }

  @Override
  public boolean validateDrop(Object target, int operation, TransferData transferType) {
    return true;
  }
}
