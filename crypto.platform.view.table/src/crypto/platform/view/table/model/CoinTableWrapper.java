package crypto.platform.view.table.model;

import java.util.Optional;
import java.util.function.Function;
import crypto.model.Coin;
import crypto.model.structures.Pair;
import crypto.platform.view.table.Activator;

public class CoinTableWrapper {

	private final Coin coin;
	private final Function<Coin, Optional<Double>>  currentPriceFunction;
	private final Function<Coin, Optional<Double>>  previousPriceFunction;

	public CoinTableWrapper(Coin key, Pair<Double, Double> value, Function<Coin, Optional<Double>> currentPriceFunction, Function<Coin, Optional<Double>> previousPriceFunction) {
		this.coin = key;
		this.currentPriceFunction = currentPriceFunction;
		this.previousPriceFunction = previousPriceFunction;
	}

	public Coin getCoin(){
		return this.coin;
	}

	public Optional<Double> getCurrentPrice(){
		return currentPriceFunction.apply(this.coin);
	}

	public Status getStatus(){
		Status status = Status.EQUAL;

		Optional<Double> previousPrice = previousPriceFunction.apply(this.coin);
		if(previousPrice.isPresent()){
			Double oldPrice = previousPrice.get();
			Double currentPrice = getCurrentPrice().orElse(new Double(0));

			if (oldPrice - currentPrice < 0)
				status = Status.UP;
			else if (oldPrice - currentPrice > 0)
				status = Status.DOWN;
		}
		return status;
	}

	public enum Status {
		UP(Activator.UP_ICON),
		DOWN(Activator.DOWN_ICON),
		EQUAL("");

		private String imgKey;

		private Status(String imgKey) {
			this.imgKey = imgKey;
		}

		public String getImgKey(){
			return imgKey;
		}
	}

}
