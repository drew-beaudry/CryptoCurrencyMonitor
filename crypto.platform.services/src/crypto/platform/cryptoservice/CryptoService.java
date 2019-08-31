package crypto.platform.cryptoservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import crypto.api.cryptocompare.constants.Constants;
import crypto.api.cryptocompare.request.RequestBuilder;
import crypto.api.cryptocompare.response.ResponseParser;
import crypto.marshalling.JSON.CoinListParser;
import crypto.marshalling.JSON.HistoParser;
import crypto.marshalling.JSON.PriceRetriever;
import crypto.model.Chart;
import crypto.model.Coin;
import crypto.model.CoinList;
import crypto.model.historical.HistoCoin;
import crypto.platform.service.Service;

public class CryptoService implements Service {
  private final List<Consumer<Coin>> addCoinEventListeners;
  private final List<Consumer<Coin>> disposeCoinEventListeners;
  private final List<Consumer<Coin>> currencyChangeListeners;
  private final List<Consumer<Chart>> chartChangeListeners;
  private final List<Consumer<Chart>> createChartListeners;

  private List<Coin> list;
  private Coin currentCurrency = null;
  private Chart chart;
  private CoinList coinList;

  private RequestBuilder builder;
  private ResponseParser responseParser = new ResponseParser();
  private String limit = "";

  public CryptoService() {
    addCoinEventListeners = new ArrayList<>();
    currencyChangeListeners = new ArrayList<>();
    chartChangeListeners = new ArrayList<>();
    disposeCoinEventListeners = new ArrayList<>();
    createChartListeners = new ArrayList<>();
    list = new ArrayList<>();
    coinList = retrieveCoinList();
  }

  @Override
  public List<Coin> getAll() {
    return list;
  }

  @Override
  public void addCoin(Coin coin) {
    list.add(coin);
    addCoinEventListeners.forEach(listener -> listener.accept(coin));
  }

  @Override
  public boolean removeCoin(Coin coin) {
    if (list.contains(coin)) {
      list.remove(coin);
      disposeCoinEventListeners.forEach(listener -> listener.accept(coin));

      // If the current Currency type is removed
      if (getCurrency() != null && getCurrency().equals(coin)) currentCurrency = null;
      return true;
    }
    return false;
  }

  // Get/Set Currency to draw a chart of
  @Override
  public void setCurrency(Coin coin) {
    currentCurrency = coin;
    // Currently Unused
    currencyChangeListeners.forEach(listener -> listener.accept(coin));
    createChart();
  }

  @Override
  public Coin getCurrency() {
    return currentCurrency;
  }

  // Set type of chart to draw
  @Override
  public void setChart(Chart type) {
    chart = type;
    chartChangeListeners.forEach(listener -> listener.accept(chart));
    createChart();
  }

  @Override
  public void createChart() {
    if (currentCurrency != null && chart != null)
      createChartListeners.forEach(listener -> listener.accept(chart));
  }

  // Get/Set Number of Days shown in graph
  @Override
  public void setLimit(String input) {
    limit = input;
  }

  @Override
  public String getLimit() {
    return limit;
  }

  private CoinList retrieveCoinList() {

    builder = new RequestBuilder();
    responseParser.sendRequest(builder.buildCoinListRequest());
    String response = responseParser.parseResponse();

    CoinListParser parser = new CoinListParser();
    return parser.parseJSON(response);
    //return null;
  }

  // Returns list of possible coins in the market
  @Override
  public CoinList getCoinList() {
    return coinList;
  }

  @Override
  public List<HistoCoin> getHistoData() {
    Map<String, String> parameters = new HashMap<String, String>();
    String currencyName = currentCurrency.getName();
    parameters.put(Constants.FSYM, currencyName);
    parameters.put(Constants.TSYM, "USD");
    parameters.put(Constants.LIMIT, limit);

    builder = new RequestBuilder();
    responseParser.sendRequest(builder.buildHistoDayRequest(parameters));
    String response = responseParser.parseResponse();

    HistoParser parser = new HistoParser();
    return parser.parseJSON(response, currencyName);
  }

  @Override
  public Map<Coin, Double> getCurrentPrices() {
    Map<String, String> parameters = new HashMap<String, String>();
    String fsym = "";
    for (Coin c : list) {
      fsym += c.getName() + ",";
    }
    if (fsym.length() > 0) fsym = fsym.substring(0, fsym.length() - 1);
    parameters.put(Constants.FSYMS, fsym);
    parameters.put(Constants.TSYMS, "USD");
    builder = new RequestBuilder();
    responseParser.sendRequest(builder.buildPriceMultiRequest(parameters));
    String response = responseParser.parseResponse();
    PriceRetriever priceRetriever = new PriceRetriever();
    Map<String, Double> rawPrices = priceRetriever.parseJSON(response);
    Map<Coin, Double> coinPrices = new HashMap<>();
    
    rawPrices.entrySet().forEach(entry ->{
      list.forEach(coin ->{
        if(coin.getName().equals(entry.getKey()))
          coinPrices.put(coin,  entry.getValue());
      });
    });
    
    return coinPrices;
  }

  @Override
  public void addNewCoinListener(Consumer<Coin> newCoinConsumer) {
    addCoinEventListeners.add(newCoinConsumer);
  }@Override
  public void removeNewCoinListener(Consumer<Coin> addCoinConsumer) {
    addCoinEventListeners.remove(addCoinConsumer);
  }
  
  
  @Override
  public void addDisposeCoinListener(Consumer<Coin> removeCoinConsumer) {
    disposeCoinEventListeners.add(removeCoinConsumer);
  }@Override
  public void removeDisposeCoinListener(Consumer<Coin> removeCoinConsumer) {
    disposeCoinEventListeners.remove(removeCoinConsumer);
  }

  
  @Override
  public void addCurrencyChangeListener(Consumer<Coin> coinConsumer) {
    currencyChangeListeners.add(coinConsumer);
  }@Override
  public void removeCurrencyChangeListener(Consumer<Coin> coinConsumer) {
    currencyChangeListeners.remove(coinConsumer);
  }

  
  @Override
  public void addChartChangeListener(Consumer<Chart> chartConsumer) {
    chartChangeListeners.add(chartConsumer);
  }@Override
  public void removeChartChangeListener(Consumer<Chart> chartConsumer) {
    chartChangeListeners.remove(chartConsumer);
  }

  
  @Override
  public void addCreateChartListener(Consumer<Chart> chartConsumer) {
    createChartListeners.add(chartConsumer);
  }@Override
  public void removeCreateChartListener(Consumer<Chart> chartConsumer) {
    createChartListeners.remove(chartConsumer);
  }

  @Override
  public Chart getChart() {
    return chart;
  }
}
