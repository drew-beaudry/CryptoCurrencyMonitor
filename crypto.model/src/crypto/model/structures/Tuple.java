package crypto.model.structures;

import java.util.Collection;

public abstract class Tuple implements Collection<Object>{

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(Object e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}


}
