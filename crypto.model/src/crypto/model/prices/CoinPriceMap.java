package crypto.model.prices;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import crypto.model.Coin;

public abstract class CoinPriceMap {

	public abstract Map<Coin, ? extends Collection<?>> getMap();

	public Set<Coin> getAllCoins() {
		return getMap().keySet();
	}

	public abstract void addCoin(Coin coin);

	public abstract void addAllCoin(Collection<Coin> coins);

	public abstract void removeCoin(Coin coin);

	public abstract void updatePrices(Map<Coin, Double> newPrices);

	public Optional<Double> getCurrentPrice(Coin coin){

		Iterator<?> itr = getMap().get(coin).iterator();

		if(itr.hasNext()){
			Object firstElement = itr.next();
			if(firstElement == null){
			  return Optional.empty();
			}
			else if(firstElement instanceof Double){
				return Optional.ofNullable((Double)firstElement);
			}
			else
				throw new ClassCastException("Invalid price map created somewhere.");
		}
		return Optional.empty();
	}
}
