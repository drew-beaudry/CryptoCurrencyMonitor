package crypto.platform.view.table;

import java.util.Map;
import java.util.TimerTask;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;
import crypto.model.Coin;
import crypto.model.prices.CoinPriceMap;
import crypto.platform.service.Service;

public class PriceTask extends TimerTask {

  private static final Logger log = Logger.getLogger(PriceTask.class);

  private static Service service = Activator.getService();

  private CoinPriceMap coinPrices;

  private Consumer<Object>[] consumers;

  @SafeVarargs
  public PriceTask(CoinPriceMap coinPrices, Consumer<Object>... consumers) {
    this.coinPrices = coinPrices;
    this.consumers = consumers;
  }

  @Override
  public void run() {
    Display.getDefault()
        .asyncExec(
            new Runnable() {
              public void run() {
                if (!service.getAll().isEmpty()) {
                  Map<Coin, Double> arg = service.getCurrentPrices();
                  coinPrices.updatePrices(arg);
                  for (Consumer<Object> c : consumers) c.accept(arg);
                }
              }
            });
  }
}
