package jsonobject;

import com.google.common.base.Objects;

public class Identifier {
  private String name;

  private String value;

  public Identifier() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Identifier{" + "name='" + name + '\'' + ", value='" + value + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Identifier)) {
      return false;
    }
    Identifier that = (Identifier) o;
    return Objects.equal(name, that.name) &&
        Objects.equal(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, value);
  }
}
