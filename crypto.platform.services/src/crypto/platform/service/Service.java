package crypto.platform.service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import crypto.model.Chart;
import crypto.model.Coin;
import crypto.model.CoinList;
import crypto.model.historical.HistoCoin;

public interface Service {

  /**Gets CoinList object of all possible currencies*/
  public CoinList getCoinList();
   
   /** @return List of type HistoCoin with historical data in each object*/
  public List<HistoCoin> getHistoData();

  public void addCoin(Coin coin);

  public void addNewCoinListener(Consumer<Coin> newCoinConsumer);

  /**Set the currency type for historical data charting*/
  void setCurrency(Coin coin);

  /**Gets the current currency selected by the user
   * for historical data charting*/
  public Coin getCurrency();

  /**Set the chart type for historical data charting*/
  void setChart(Chart chart);
  
  /**Gets the current chart type selected by the user
   * for historical data charting*/
  public Chart getChart();

  void addChartChangeListener(Consumer<Chart> chartConsumer);

  void addCurrencyChangeListener(Consumer<Coin> coinConsumer);

  /**Set Limit parameter for historical data requests*/
  void setLimit(String input);
  
  /**Get Limit parameter for historical data requests*/
  String getLimit();

  /**Returns List of type Coin of all user added coins*/
  List<Coin> getAll();

  Map<Coin, Double> getCurrentPrices();

  void createChart();

  boolean removeCoin(Coin coin);

  void addDisposeCoinListener(Consumer<Coin> removeCoinConsumer);

  void addCreateChartListener(Consumer<Chart> chartConsumer);

  void removeDisposeCoinListener(Consumer<Coin> removeCoinConsumer);

  public void removeNewCoinListener(Consumer<Coin> addCoinConsumer);

  void removeCurrencyChangeListener(Consumer<Coin> coinConsumer);

  void removeChartChangeListener(Consumer<Chart> chartConsumer);

  void removeCreateChartListener(Consumer<Chart> chartConsumer);
}
