package pl.coderstrust.accounting.model;

import java.util.Objects;

public class Company {

  private String name;
  private String nip;
  private String street;
  private String postalCode;
  private String city;
  private double discount;

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

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public Company(String name, String nip, String street, String postalCode, String city,
      double discount) {
    this.name = name;
    this.nip = nip;
    this.street = street;
    this.postalCode = postalCode;
    this.city = city;
    this.discount = discount;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Company company = (Company) object;
    return Objects.equals(getNip(), company.getNip());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getNip());
  }
}
