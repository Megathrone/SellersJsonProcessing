package jsonobject;

import com.google.common.base.Objects;

public class Seller {
  private String seller_id;
  private String name;
  private String domain;
  private String seller_type;
  private String directness;
  private String comment;
  private Integer is_passthrough = 0;
  private Integer is_confidential = 0;

  public Seller() {}

  public String getSeller_id() {
    return seller_id;
  }

  public void setSeller_id(String seller_id) {
    this.seller_id = seller_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getSeller_type() {
    return seller_type;
  }

  public void setSeller_type(String seller_type) {
    this.seller_type = seller_type;
  }

  public String getDirectness() {
    return directness;
  }

  public void setDirectness(String directness) {
    this.directness = directness;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Integer getIs_passthrough() {
    return is_passthrough;
  }

  public void setIs_passthrough(Integer is_passthrough) {
    this.is_passthrough = is_passthrough;
  }

  public Integer getIs_confidential() {
    return is_confidential;
  }

  public void setIs_confidential(Integer is_confidential) {
    this.is_confidential = is_confidential;
  }

  @Override
  public String toString() {
    return "Seller{"
        + "seller_id='"
        + seller_id
        + '\''
        + ", name='"
        + name
        + '\''
        + ", domain='"
        + domain
        + '\''
        + ", seller_type='"
        + seller_type
        + '\''
        + ", directness='"
        + directness
        + '\''
        + ", comment='"
        + comment
        + '\''
        + ", is_passthrough="
        + is_passthrough
        + ", is_confidential="
        + is_confidential
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Seller)) {
      return false;
    }
    Seller seller = (Seller) o;
    return Objects.equal(seller_id, seller.seller_id)
        && Objects.equal(name, seller.name)
        && Objects.equal(domain, seller.domain)
        && Objects.equal(seller_type, seller.seller_type)
        && Objects.equal(directness, seller.directness)
        && Objects.equal(comment, seller.comment)
        && Objects.equal(is_passthrough, seller.is_passthrough)
        && Objects.equal(is_confidential, seller.is_confidential);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        seller_id, name, domain, seller_type, directness, comment, is_passthrough, is_confidential);
  }
}
