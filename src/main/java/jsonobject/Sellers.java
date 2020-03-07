package jsonobject;

import com.google.common.base.Objects;
import java.util.List;

/**
 * @author Yizhou
 *     <p>Sellers.json JSON Object according to iab lab specification
 */
public class Sellers {
  private String contact_email = "";
  private String contact_address = "";
  private String version = "";
  private List<Identifier> identifiers = null;
  private List<Seller> sellers = null;

  public Sellers() {}

  @Override
  public String toString() {
    return "Sellers{"
        + "contact_email='"
        + contact_email
        + '\''
        + ", contact_address='"
        + contact_address
        + '\''
        + ", version='"
        + version
        + '\''
        + ", identifiers="
        + identifiers
        + ", sellers="
        + sellers
        + '}';
  }

  public String getContact_email() {
    return contact_email;
  }

  public void setContact_email(String contact_email) {
    this.contact_email = contact_email;
  }

  public String getContact_address() {
    return contact_address;
  }

  public void setContact_address(String contact_address) {
    this.contact_address = contact_address;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  public List<Seller> getSellers() {
    return sellers;
  }

  public void setSellers(List<Seller> sellers) {
    this.sellers = sellers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Sellers)) {
      return false;
    }
    Sellers sellers1 = (Sellers) o;
    return Objects.equal(contact_email, sellers1.contact_email)
        && Objects.equal(contact_address, sellers1.contact_address)
        && Objects.equal(version, sellers1.version)
        && Objects.equal(identifiers, sellers1.identifiers)
        && Objects.equal(sellers, sellers1.sellers);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(contact_email, contact_address, version, identifiers, sellers);
  }
}
