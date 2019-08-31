package crypto.model;

import java.util.Map;

public class HistoFile {
	int time;
	double close;
	double high;
	double low;
	double open;
	double volumefrom;
	double volumeto;
	Map<String, String> parameters;

	public HistoFile(int time, double close, double high, double low, double open, double volumefrom, double volumeto) {
		this.time = time;
		this.close = close;
		this.high = high;
		this.low = low;
		this.open = open;
		this.volumefrom = volumefrom;
		this.volumeto = volumeto;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getVolumeFrom() {
		return volumefrom;
	}

	public void setVolumeFrom(double volumeFrom) {
		this.volumefrom = volumeFrom;
	}

	public double getVolumeTo() {
		return volumeto;
	}

	public void setVolumeTo(double volumeTo) {
		this.volumeto = volumeTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(close);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(high);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(low);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(open);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + time;
		temp = Double.doubleToLongBits(volumefrom);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volumeto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoFile other = (HistoFile) obj;
		if (Double.doubleToLongBits(close) != Double.doubleToLongBits(other.close))
			return false;
		if (Double.doubleToLongBits(high) != Double.doubleToLongBits(other.high))
			return false;
		if (Double.doubleToLongBits(low) != Double.doubleToLongBits(other.low))
			return false;
		if (Double.doubleToLongBits(open) != Double.doubleToLongBits(other.open))
			return false;
		if (time != other.time)
			return false;
		if (Double.doubleToLongBits(volumefrom) != Double.doubleToLongBits(other.volumefrom))
			return false;
		if (Double.doubleToLongBits(volumeto) != Double.doubleToLongBits(other.volumeto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "histFile [time=" + time + ", close=" + close + ", high=" + high + ", low=" + low + ", open=" + open
				+ ", volumefrom=" + volumefrom + ", volumeto=" + volumeto + "]";
	}
}
