package crypto.model;

import java.io.Serializable;

import org.eclipse.swt.graphics.Image;

public class Coin implements Serializable, Comparable<Coin> {

  private static final long serialVersionUID = -5181828390231924947L;
  private final String CoinName;
  private final String Symbol;
  private final int Id;
  private final String ImageUrl;
  private final String Url;
  //Do not include in serialization
  transient private Image image;

  public Coin(String name, String symbol, int id, String imgurl, String url) {
    this.CoinName = name;
    this.Symbol = symbol;
    this.Id = id;
    this.ImageUrl = imgurl;
    this.Url = url;
  }

  public String getName() {
    return CoinName;
  }

  public String getSymbol() {
    return Symbol;
  }

  public int getId() {
    return Id;
  }

  public String getImgurl() {
    return ImageUrl;
  }

  public String getUrl() {
    return Url;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }


  @Override
  public String toString() {
    return "Coin [CoinName="
        + CoinName
        + ", Symbol="
        + Symbol
        + ", Id="
        + Id
        + ", ImageUrl="
        + ImageUrl
        + ", Url="
        + Url
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((CoinName == null) ? 0 : CoinName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Coin other = (Coin) obj;
    if (CoinName == null) {
      if (other.CoinName != null) return false;
    } else if (!CoinName.equals(other.CoinName)) return false;
    return true;
  }

  @Override
  public int compareTo(Coin coin) {
    return this.getName().compareTo(coin.getName());
  }
}
