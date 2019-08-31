package crypto.marshalling.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import crypto.api.cryptocompare.response.ResponseParser;
import crypto.model.Coin;
import crypto.model.CoinList;
import crypto.platform.exceptions.InvalidInputException;

/** Parses list of currencies from API */
public class CoinListParser {
  ResponseParser responseParser = new ResponseParser();
  private final Moshi moshi = new Moshi.Builder().build();
  private final JsonAdapter<JSON> jsonAdapter = moshi.adapter(JSON.class);
  private List<Coin> list;

  public CoinListParser() {}

  /** Parses response string and creates a CoinList object
   * 
   * @param response
   * @return CoinList
   */
  public CoinList parseJSON(String response) {
    String name;
    String symbol;
    int id;
    String imgurl;
    String url;

    list = new ArrayList<>();
    CoinList coinList = new CoinList(list);
    try {
      JSON json = jsonAdapter.fromJson(response);

      if (!(json.Data instanceof Map)) throw new InvalidInputException();
      @SuppressWarnings("unchecked")
      Map<String, Object> input = (Map<String, Object>) json.Data;
      List<String> coinNameList = new ArrayList<>();

      for (String key : input.keySet()) {
        @SuppressWarnings("unchecked")
        Map<String, Object> input2 = (Map<String, Object>) input.get(key);
        
        name = (String) input2.get("Name");
        symbol = (String) input2.get("Symbol");
        id = Integer.parseInt((String) input2.get("Id"));
        imgurl = (String) input2.get("ImageUrl");
        url = (String) input2.get("Url");
        
        list.add(new Coin(name, symbol, id, imgurl, url));
      }
      Collections.sort(coinNameList);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return coinList;
  }

  static class JSON {
    Object Data;
  }
}
