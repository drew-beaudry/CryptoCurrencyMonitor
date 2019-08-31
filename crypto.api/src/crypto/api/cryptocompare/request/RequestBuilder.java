package crypto.api.cryptocompare.request;

import java.util.Map;

import crypto.api.cryptocompare.constants.Constants;
import okhttp3.Request;

/** Builds okhttp3 Request objects for use with the CryptoCompare API server */
public class RequestBuilder {

  public RequestBuilder() {}

  /**
   * Historical Day Request
   * Use Limit parameter to set number of days returned
   * Limit is zero indexed: A limit of "14" will return 15 days of information
   * @param url parameters
   * @return okhttp3.Request
   */
  public Request buildHistoDayRequest(Map<String, String> parameters) {
    String url = Constants.HISTODAYURL;
    url += Constants.FSYM + "=" + parameters.get(Constants.FSYM);
    url += "&" + Constants.TSYM + "=" + parameters.get(Constants.TSYM);

    if (parameters.containsKey(Constants.EXCHANGE))
      url += "&" + Constants.EXCHANGE + "=" + parameters.get(Constants.EXCHANGE);
    if (parameters.containsKey(Constants.EXTRAPARAMS))
      url += "&" + Constants.EXTRAPARAMS + "=" + parameters.get(Constants.EXTRAPARAMS);
    if (parameters.containsKey(Constants.SIGN))
      url += "&" + Constants.SIGN + "=" + parameters.get(Constants.SIGN);
    if (parameters.containsKey(Constants.TRYCONVERSION))
      url += "&" + Constants.TRYCONVERSION + "=" + parameters.get(Constants.TRYCONVERSION);
    if (parameters.containsKey(Constants.AGGREGATE))
      url += "&" + Constants.AGGREGATE + "=" + parameters.get(Constants.AGGREGATE);
    if (parameters.containsKey(Constants.LIMIT))
      url += "&" + Constants.LIMIT + "=" + parameters.get(Constants.LIMIT);
    if (parameters.containsKey(Constants.TOTS)) url += Constants.TOTS;

    return new Request.Builder().url(url).build();
  }
  /**
   * Price Request: Handles one FromSymbol and many ToSymbols
   *
   * @param url parameters
   * @return okhttp3.Request
   */
  public Request buildPriceRequest(Map<String, String> parameters) {
    String url = Constants.PRICEURL;
    url += Constants.FSYM + "=" + parameters.get(Constants.FSYM);
    url += "&" + Constants.TSYMS + "=" + parameters.get(Constants.TSYM);
    url += "&" + Constants.EXCHANGE + "=" + parameters.get(Constants.EXCHANGE);

    if (parameters.containsKey(Constants.EXTRAPARAMS))
      url += "&" + Constants.EXTRAPARAMS + "=" + parameters.get(Constants.EXTRAPARAMS);
    if (parameters.containsKey(Constants.SIGN))
      url += "&" + Constants.SIGN + "=" + parameters.get(Constants.SIGN);
    if (parameters.containsKey(Constants.TRYCONVERSION))
      url += "&" + Constants.TRYCONVERSION + "=" + parameters.get(Constants.TRYCONVERSION);

    return new Request.Builder().url(url).build();
  }
  /**
   * Price Request: Handles many FromSymbols and many ToSymbols
   *
   * @param url parameters
   * @return okhttp3.Request
   */
  public Request buildPriceMultiRequest(Map<String, String> parameters) {
    String url = Constants.PRICEMULTIURL;
    url += Constants.FSYMS + "=" + parameters.get(Constants.FSYMS);
    url += "&" + Constants.TSYMS + "=" + parameters.get(Constants.TSYMS);

    if (parameters.containsKey(Constants.EXCHANGE))
      url += "&" + Constants.EXCHANGE + "=" + parameters.get(Constants.EXCHANGE);
    if (parameters.containsKey(Constants.EXTRAPARAMS))
      url += "&" + Constants.EXTRAPARAMS + "=" + parameters.get(Constants.EXTRAPARAMS);
    if (parameters.containsKey(Constants.SIGN))
      url += "&" + Constants.SIGN + "=" + parameters.get(Constants.SIGN);
    if (parameters.containsKey(Constants.TRYCONVERSION))
      url += "&" + Constants.TRYCONVERSION + "=" + parameters.get(Constants.TRYCONVERSION);
    return new Request.Builder().url(url).build();
  }
  /**
   * Returns the list of all cryptocurrencies
   *
   * @return okhttp3.Request
   */
  public Request buildCoinListRequest() {
    String url = Constants.COINLISTURL;
    return new Request.Builder().url(url).build();
  }
}
