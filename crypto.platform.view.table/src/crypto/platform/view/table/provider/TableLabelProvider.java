package crypto.platform.view.table.provider;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import crypto.model.Coin;
import crypto.model.CoinList;
import crypto.platform.service.Service;
import crypto.platform.view.table.Activator;
import crypto.platform.view.table.model.CoinTableWrapper;
import crypto.platform.view.table.model.CoinTableWrapper.Status;
import okhttp3.OkHttpClient;

public class TableLabelProvider implements ITableLabelProvider, IColorProvider {
  DecimalFormat dForm = new DecimalFormat();
  Map<String, String> statusMap;
  String status = "";
  private Map<String, Double> oldPrices;
  private static Service service = Activator.getService();
  CoinList coinList = service.getCoinList();
  private static final Logger log = Logger.getLogger(TableLabelProvider.class);
  private final OkHttpClient client = new OkHttpClient();

  @Override
  public void addListener(ILabelProviderListener listener) {}

  @Override
  public void dispose() {}

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {}

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    if (columnIndex == 0) {
      ImageHandler imageHandler = new ImageHandler();
      Coin coin = coinList.findCoinByName(((CoinTableWrapper) element).getCoin().getSymbol());
      return imageHandler.getImage(coin);
    }
    /*    if (columnIndex == 2) {
      if (element instanceof CoinTableWrapper) {
        return Activator.getDefault()
            .getImageRegistry()
            .get(((CoinTableWrapper) element).getStatus().getImgKey());
      }
      return null;

    }*/ else return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getColumnText(Object element, int columnIndex) {
    dForm.setMinimumFractionDigits(2);
    dForm.setMaximumFractionDigits(10);

    if (element instanceof Entry) {
      Entry<String, Double> entry = (Entry<String, Double>) element;
      switch (columnIndex) {
        case 0:
          return entry.getKey();
        case 1:
          Double value = entry.getValue();
          return dForm.format(value);
        case 2:
          /*
           * statusMap = parser.getStatus(); status = statusMap.get(entry.getKey()); return status
           */
          return "";
      }
    } else if (element instanceof CoinTableWrapper) {
      CoinTableWrapper wrapper = (CoinTableWrapper) element;
      switch (columnIndex) {
        case 0:
          return wrapper.getCoin().getName();
        case 1:
          if (wrapper.getCurrentPrice().isPresent()) {
            Double value = wrapper.getCurrentPrice().get();
            return dForm.format(value);
          } else return "Waiting for Price Data...";
        case 2:
          if (((CoinTableWrapper) element).getStatus() == Status.UP) return "UP";
          if (((CoinTableWrapper) element).getStatus() == Status.DOWN) return "DOWN";
          return "";
      }
    }

    return element.toString();
  }

  @Override
  public Color getForeground(Object element) {
    if (element instanceof CoinTableWrapper) {
      if (((CoinTableWrapper) element).getStatus() == Status.UP)
        return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
      if (((CoinTableWrapper) element).getStatus() == Status.DOWN)
        return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
    }

    return null;
  }

  // @SuppressWarnings("unchecked")
  @Override
  public Color getBackground(Object element) {
    /*InputParser parser = new InputParser();
    String status = "";
    Entry<String, Double> entry;

    if (element instanceof Entry) {
      entry = (Entry<String, Double>) element;
    } else throw new InvalidInputException();

    Map<String, String> statusMap = parser.getStatus();
    if (statusMap.containsKey(entry.getKey())) {
      status = statusMap.get(entry.getKey());
      if (status.equals(Constants.UP)) return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
      else if (status.equals(Constants.DOWN))
        return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
      // else if (status.equals("EQUAL")) return
      // Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
    }*/
    return null;
  }
}
