package crypto.platform.view.table.view;

import java.util.Timer;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;

import crypto.model.Coin;
import crypto.model.prices.CoinPreviousPriceMap;
import crypto.platform.service.Service;
import crypto.platform.view.table.Activator;
import crypto.platform.view.table.PriceTask;
import crypto.platform.view.table.component.TableComponent;
import crypto.platform.view.table.controller.TableController;

/**View where live pricing data is shown, in LivePerspective*/
public class TableView extends ViewPart {
  private TableController controller;
  private static Service service = Activator.getService();
  private static final Logger log = Logger.getLogger(TableView.class);
  private Timer timer;
  private Consumer<Coin> removeCoinConsumer;
  private Consumer<Coin> addCoinConsumer;

  @Override
  public void createPartControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NONE);
    controller = new TableComponent().create(container);

    CoinPreviousPriceMap coinPrices = new CoinPreviousPriceMap();
    coinPrices.addAllCoin(service.getAll());
    
    removeCoinConsumer = (c) -> {
      c.getImage().dispose();
      c.setImage(null);
      coinPrices.removeCoin(c);
      controller.refresh();
    };
    
    addCoinConsumer = (c) -> {
      coinPrices.addCoin(c);
      controller.refresh();
    };
        
    service.addDisposeCoinListener(removeCoinConsumer);
    service.addNewCoinListener(addCoinConsumer);
    controller.setInput(coinPrices);

    timer = new Timer();
    timer.schedule(new PriceTask(coinPrices, this::refresh), 0, 10000);
    refresh();
  }

  public void refresh(Object...args){
    controller.refresh();
  }

  @Override
  public void setFocus() {
  }

  @Override
  public void dispose() {
    timer.cancel();
    controller.dispose();
    service.removeDisposeCoinListener(removeCoinConsumer);
    service.removeNewCoinListener(addCoinConsumer);
  }
}
