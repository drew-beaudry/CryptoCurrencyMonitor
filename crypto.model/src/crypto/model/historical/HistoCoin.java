package crypto.model.historical;

import java.io.Serializable;

/**Coin Object used for storing historical data. Used in creation of charts*/
public class HistoCoin implements Serializable, Comparable<HistoCoin> {

  private static final long serialVersionUID = -7788465895346475625L;
  private String name;

  long time;
  double close;
  double high;
  double low;
  double open;
  double volumefrom;
  double volumeto;

  public HistoCoin(String name) {
    this.name = name;
  }

  public HistoCoin(
      String name,
      long time,
      double close,
      double high,
      double low,
      double open,
      double volumefrom,
      double volumeto) {
    this.name = name;
    this.time = time;
    this.close = close;
    this.high = high;
    this.low = low;
    this.open = open;
    this.volumefrom = volumefrom;
    this.volumeto = volumeto;
  }

  public HistoCoin() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getTime() {
    return time;
  }

  public double getClose() {
    return close;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public double getOpen() {
    return open;
  }

  public double getVolumefrom() {
    return volumefrom;
  }

  public double getVolumeto() {
    return volumeto;
  }

  public HistoCoin copy(HistoCoin addCoin) {
    return new HistoCoin(addCoin.getName());
  }

  @Override
  public String toString() {
    return "Coin [name="
        + name
        + ", time="
        + time
        + ", close="
        + close
        + ", high="
        + high
        + ", low="
        + low
        + ", open="
        + open
        + ", volumefrom="
        + volumefrom
        + ", volumeto="
        + volumeto
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    HistoCoin other = (HistoCoin) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }

  @Override
  public int compareTo(HistoCoin coin) {
    return this.getName().compareTo(coin.getName());
  }
}
