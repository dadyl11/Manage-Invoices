package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public class Company {

  private String name;
  private String nip;
  private String street;
  private String postalCode;
  private String city;
  private BigDecimal discount;

  public BigDecimal getDiscount() {
    return discount;
  }
}
