package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public class Company {

  private String name;
  private String nip;
  private String street;
  private String postalCode;
  private String city;
  private double discount;

  public double getDiscount() {
    return discount;
  }
}
