package crypto.platform.view.table.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import crypto.model.prices.CoinPreviousPriceMap;
import crypto.platform.view.table.model.CoinTableWrapper;

public class TableContentProvider implements IStructuredContentProvider {



	public TableContentProvider() {}

	@Override
	public void dispose() {}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Map) {
			return ((Map) inputElement).entrySet().toArray();
		} else if (inputElement instanceof CoinPreviousPriceMap) {
			CoinPreviousPriceMap priceMap = (CoinPreviousPriceMap) inputElement;

			List<CoinTableWrapper> elements = new ArrayList<>();
			priceMap.getMap().entrySet().forEach(entry -> {
				elements.add(new CoinTableWrapper(entry.getKey(), entry.getValue(), priceMap::getCurrentPrice, priceMap::getPreviousPrice));
			});

			return elements.toArray();
		}
		return null;
	}
}
