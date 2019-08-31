package crypto.model.structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.lang.builder.CompareToBuilder;

public abstract class Pair<L, R> extends Tuple implements Comparable<Pair<?,?>> {

	public abstract L getLeft();
	public abstract R getRight();

	public abstract void setLeft(L newLeft);
	public abstract void setRight(R newRight);

	@Override
	public int size() {
		return (getLeft() == null ? 0 : 1) + (getRight() == null ? 0 : 1);
	}

	@Override
	public boolean isEmpty() {
		return getLeft() == null && getRight() == null;
	}

	@Override
	public boolean contains(Object o) {
		return getLeft() == o || getRight() == o;
	}

	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index == 0;
			}

			@Override
			public Object next() {
				if (index == 0) {
					index++;
					return getLeft();
				} else if (index == 1) {
					index++;
					return getRight();
				} else
					throw new NoSuchElementException();
			}
		};
	}

	@Override
	public Object[] toArray() {
		return new Object[] {getLeft(), getRight()};
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (c.size() != this.size())
			return false;
		else
			return c.contains(getLeft()) && c.contains(getRight());
	}

	@Override
	public int compareTo(Pair<?,?> other) {
		return new CompareToBuilder()
				.append(getLeft(), other.getLeft())
				.append(getRight(), other.getRight())
				.toComparison();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getLeft() == null) ? 0 : getLeft().hashCode());
		result = prime * result + ((getRight() == null) ? 0 : getRight().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (obj instanceof Pair) {
			Pair<?, ?> other = (Pair<?, ?>) obj;
			if (getLeft() == null) {
				if (other.getLeft() != null)
					return false;
			} else if (!getLeft().equals(other.getLeft()))
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
		if(getRight()== null) sb.append("null");
		else sb.append(getRight().toString());
		return sb.toString();
		//@formatter:on
	}

	public static class ImmutablePair<L, R> extends Pair<L, R> {

		private final L left;
		private final R right;

		public ImmutablePair(L left, R right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public L getLeft() {
			return this.left;
		}

		@Override
		public R getRight() {
			return this.right;
		}

		@Override
		public void setLeft(L newLeft) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRight(R newRight) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

	}

	public static class MutablePair<L, R> extends Pair<L, R> {

		private L left;
		private R right;

		public MutablePair() {}

		public MutablePair(L left, R right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public L getLeft() {
			return this.left;
		}

		@Override
		public R getRight() {
			return this.right;
		}

		@Override
		public void setLeft(L newLeft) {
			this.left = newLeft;
		}

		@Override
		public void setRight(R newRight) {
			this.right = newRight;
		}

		@Override
		public void clear() {
			left = null;
			right = null;
		}
	}
}
