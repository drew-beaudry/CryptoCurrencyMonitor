package crypto.model.structures;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.builder.CompareToBuilder;

public abstract class Triple<L, M, R> extends Tuple implements Comparable<Triple<?,?,?>> {

	public abstract L getLeft();
	public abstract M getMiddle();
	public abstract R getRight();

	public abstract void setLeft(L newLeft);
	public abstract void setMiddle(M newMiddle);
	public abstract void setRight(R newRight);


	@Override
	public int size() {
		return (getLeft() == null ? 0 : 1) +(getMiddle() == null ? 0 : 1) +(getRight() == null ? 0 : 1);
	}

	@Override
	public boolean isEmpty() {
		return getLeft() == null && getMiddle() == null &&getRight() == null;
	}

	@Override
	public boolean contains(Object o) {
		return getLeft() == o || getMiddle() == o || getRight() == o;
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		return new Object[]{getLeft(), getMiddle(), getRight()};
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Object> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Triple<?, ?, ?> other) {
		return new CompareToBuilder()
				.append(getLeft(), other.getLeft())
				.append(getMiddle(), other.getMiddle())
				.append(getRight(), other.getRight())
				.toComparison();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getLeft() == null) ? 0 : getLeft().hashCode());
		result = prime * result + ((getMiddle() == null) ? 0 : getMiddle().hashCode());
		result = prime * result + ((getRight() == null) ? 0 : getRight().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (obj instanceof Triple) {
			Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
			if (getLeft() == null) {
				if (other.getLeft() != null)
					return false;
			} else if (!getLeft().equals(other.getLeft()))
				return false;
			if (getMiddle() == null) {
				if (other.getMiddle() != null)
					return false;
			} else if (!getMiddle().equals(other.getMiddle()))
				return false;
			if (getRight() == null) {
				if (other.getRight() != null)
					return false;
			} else if (!getRight().equals(other.getRight()))
				return false;
		}
		return false;
	}

	@Override
	public String toString() {
		//@formatter:off
		StringBuilder sb = new StringBuilder();
		if(getLeft()== null) sb.append("null");
		else sb.append(getLeft().toString());
		sb.append(", ");
		if(getMiddle()== null) sb.append("null");
		else sb.append(getMiddle().toString());
		sb.append(", ");
		if(getRight()== null) sb.append("null");
		else sb.append(getRight().toString());
		return sb.toString();
		//@formatter:on
	}

}
