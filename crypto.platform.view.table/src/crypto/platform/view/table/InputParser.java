/*package crypto.platform.view.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import crypto.model.Coin;
import crypto.platform.service.Service;

public class InputParser {
  private Map<Coin, Double> currentData = new HashMap<Coin, Double>();
  private Map<Coin, Double> pastData = new HashMap<Coin, Double>();
  List<String> returnData = new ArrayList<>();
  private static Service service = Activator.getService();
  private static final Logger log = Logger.getLogger(InputParser.class);
  Map<String, String> statusMap = new HashMap<String, String>();

  public Map<String, Double> getInput() {
    if (!service.getAll().isEmpty()) {
      if (!currentData.isEmpty()) {
        pastData = new HashMap<Coin,Double>(currentData);
         log.info("GetInput():Past data saved");
         log.info("GetInput():Past Data: " + pastData);
      }
      
      currentData = service.getCurrentPrices();
       log.info("GetInput():Current Data: " + currentData);
       log.info("GetInput(): IS DATA EQUAL? " + currentData.equals(pastData));

      if (!statusMap.isEmpty()) statusMap.clear();
      for (String s : currentData.keySet()) {
        if (pastData.containsKey(s)) {
          if (currentData.get(s) > pastData.get(s)) {
             log.info("UP Reached");
            statusMap.put(s, Activator.UP_ICON);
          }
          if (currentData.get(s) < pastData.get(s)) {
             log.info("DOWN Reached");
            statusMap.put(s, Activator.DOWN_ICON);
          }
        }
      }
      return currentData;
    }
    return null;
  }

  public Map<String, String> getStatus() {
    if (!statusMap.isEmpty()) statusMap.clear();
    // statusMap = new HashMap<String, String>();
    // log.info("getStatus version of currentData: " + currentData);
    // log.info("getStatus version of pastData: " + pastData);
    for (String s : currentData.keySet()) {
      if (pastData.containsKey(s)) {
        if (currentData.get(s) > pastData.get(s)) {
          // log.info("UP Reached");
          statusMap.put(s, Activator.UP_ICON);
        }
        if (currentData.get(s) < pastData.get(s)) {
          // log.info("DOWN Reached");
          statusMap.put(s, Activator.DOWN_ICON);
        }
        if (currentData.get(s).equals(pastData.get(s))) statusMap.put(s, "EQUAL");
      }
    }
    //log.info("getStatus called");
    return statusMap;
  }
}
*/