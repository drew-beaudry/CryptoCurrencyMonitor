package crypto.model.prices;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import crypto.model.Coin;
import crypto.model.structures.Pair;

public class CoinPreviousPriceMap extends CoinPriceMap {

	private final Map<Coin, Pair<Double, Double>> priceMap = new HashMap<>();

	@Override
	public Map<Coin, Pair<Double, Double>> getMap() {
		return this.priceMap;
	}

	@Override
	public void addCoin(Coin coin) {
		priceMap.put(coin, new Pair.MutablePair<Double, Double>());
	}

	@Override
	public void addAllCoin(Collection<Coin> list){
		list.forEach(c -> {
			addCoin(c);
		});
	}

	@Override
	public void removeCoin(Coin coin) {
		priceMap.remove(coin);
	}

	@Override
	public void updatePrices(Map<Coin, Double> newPrices) {
		for (Entry<Coin, Double> entry : newPrices.entrySet()) {
			Pair<Double, Double> prices = priceMap.get(entry.getKey());
			if (prices == null) {
				addCoin(entry.getKey());
				prices = priceMap.get(entry.getKey());
			}
			prices.setRight(prices.getLeft() == null ? null : new Double(prices.getLeft()));
			prices.setLeft(entry.getValue());
		}
	}

	public Optional<Double> getPreviousPrice(Coin coin){
		return Optional.ofNullable(priceMap.get(coin).getRight());
	}
}
