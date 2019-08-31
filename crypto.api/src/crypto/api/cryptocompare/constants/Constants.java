package crypto.api.cryptocompare.constants;

import org.eclipse.swt.graphics.Point;

public class Constants {
  
  public static final Point APPLICATIONSIZE = new Point(1000,900);
  public static final String APPLICATIONNAME = "Crypto-Compare";
  
  public static final String UP = "UP";
  public static final String DOWN = "DOWN";
  public static final String BASEURL = "https://www.cryptocompare.com"; 
  public static final String BASEAPIURL = "https://min-api.cryptocompare.com/data/";
  public static final String HISTODAYURL = BASEAPIURL + "histoday?";
  public static final String PRICEURL = BASEAPIURL + "price?";
  public static final String PRICEMULTIURL = BASEAPIURL + "pricemulti?";
  public static final String COINLISTURL = BASEAPIURL + "all/coinlist";
  
  public static final String FSYM = "fsym";
  public static final String FSYMS = "fsyms";
  public static final String TSYM = "tsym";
  public static final String TSYMS = "tsyms";
  public static final String EXCHANGE = "e";
  public static final String EXTRAPARAMS = "extraParams";
  public static final String SIGN = "sign";
  public static final String TRYCONVERSION = "tryConversion";
  public static final String AGGREGATE = "aggregate";
  public static final String LIMIT = "limit";
  public static final String TOTS = "toTs";
  
  public static final String BARGRAPH = "Bar Graph";
  public static final String LINEGRAPH = "Line Graph";
  public static final String PIECHART = "Pie Chart";
  public static final String CANDLESTICKCHART = "Candlestick Chart";
  
  public static final float COINICONSIZE = 0.08f;
}
