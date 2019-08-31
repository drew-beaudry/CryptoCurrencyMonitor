package crypto.marshalling.JSON;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import crypto.platform.exceptions.InvalidInputException;

/** Retrieves current pricing information*/
public class PriceRetriever {
  private static final Logger log = Logger.getLogger(PriceRetriever.class);

  public PriceRetriever() {}

  @SuppressWarnings("unchecked")
  public Map<String, Double> parseJSON(String response) {

    //JSON FORMAT:
    //MAP<String, MAP<String,Number>>
    //Number can be double (most common) or sometimes integer
    
    // TreeMap auto sorts alphabetically
    Map<String, Double> priceMap = new TreeMap<String, Double>();

    try {
      // Cast JSON response string into a map
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> responseMap =
          mapper.readValue(response, new TypeReference<Map<String, Object>>() {});
      // Read the map
      for (String s : responseMap.keySet()) {
        if (!(responseMap.get(s) instanceof Map)) throw new InvalidInputException();
        Map<String, Number> tempMap = (Map<String, Number>) responseMap.get(s);
        
        //Type checking prevents ClassCastException
        if (tempMap.get("USD") instanceof Integer)
          priceMap.put(s, (double) ((Integer) tempMap.get("USD")).intValue());
        if (tempMap.get("USD") instanceof Double) 
          priceMap.put(s, (Double) (tempMap.get("USD")));
      }
    } 
    catch (InvalidInputException e){
      log.error("JSON returned ERROR");
    }
    catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return priceMap;
  }
}
