package crypto.model;

import java.util.ArrayList;
import java.util.List;

/** List of Coins containing all possible currencies*/
public class CoinList {
	private final List<Coin> list;

	public CoinList(List<Coin> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "CoinList [list=" + list + "]";
	}

  /**Returns list of every symbol within the CoinList
   * 
   * @return List<String>
   */
	public List<String> getSymbolList() {
    List<String> nameList = new ArrayList<>();
    for(Coin c: list)
      nameList.add(c.getSymbol());
    
    return nameList;
  }
	
	public Coin findCoinByName(String symbol){
	  for(Coin c: list)
	    if (c.getSymbol().equals(symbol))
	    return c;
	  return null;
	}
}
