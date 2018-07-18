package pl.coderstrust.accounting.model;

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
  //TODO - add setters and getters, when we're going to need them - will need tests to pass jacoco
}
