package crypto.model;

import java.io.Serializable;

public class Chart implements Serializable{

  private static final long serialVersionUID = 4400309766810747967L;
  private String name;
  
  public Chart(){}

  public Chart(String name) {
    this.name = name;
  }

  public String getName() {
  return name;}

  public void setName(String name) {
  this.name = name;}

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
    Chart other = (Chart) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }
  
  
  
}
