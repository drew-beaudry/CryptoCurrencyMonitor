package crypto.marshalling.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import crypto.model.HistoFile;
import crypto.model.historical.HistoCoin;

/**Parses historical data responses*/
public class HistoParser {
  private final Moshi moshi = new Moshi.Builder().build();
  private final JsonAdapter<JSON> jsonAdapter = moshi.adapter(JSON.class);
  private List<HistoCoin> list;
  
  public HistoParser() {}

  public List<HistoCoin> parseJSON(String response, String currencyName) {


    list = new ArrayList<>();
    try {    
      JSON json = jsonAdapter.fromJson(response);
      for (HistoFile data : json.Data) {
        // list.add(new HistoFile(data.time,data.close, data.high, data.low, data.open,
        // data.volumefrom, data.volumeto));
        list.add(
            new HistoCoin(
                currencyName,
                data.getTime(),
                data.getClose(),
                data.getHigh(),
                data.getLow(),
                data.getOpen(),
                data.getVolumeFrom(),
                data.getVolumeTo()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  static class JSON {
    HistoFile[] Data;
  }
}
