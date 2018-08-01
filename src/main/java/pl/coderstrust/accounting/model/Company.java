package pl.coderstrust.accounting.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

public class Company {

  private String name;
  private String nip;
  private String street;
  private String postalCode;
  private String city;
  private BigDecimal discount;

  public Company() {
  }

  public Company(String name, String nip, String street, String postalCode, String city,
      BigDecimal discount) {
    this.name = name;
    this.nip = nip;
    this.street = street;
    this.postalCode = postalCode;
    this.city = city;
    this.discount = discount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNip() {
    return nip;
  }

  public void setNip(String nip) {
    this.nip = nip;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Company company = (Company) obj;

    return new EqualsBuilder()
        .append(discount, company.discount)
        .append(name, company.name)
        .append(nip, company.nip)
        .append(street, company.street)
        .append(postalCode, company.postalCode)
        .append(city, company.city)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(name)
        .append(nip)
        .append(street)
        .append(postalCode)
        .append(city)
        .append(discount)
        .toHashCode();
  }
}
