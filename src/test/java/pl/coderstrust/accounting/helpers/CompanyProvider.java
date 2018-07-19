package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.Company;

public class CompanyProvider {

  public static final Company company1 = new Company(
      "Transpol",
      "6752339483",
      "Grodzka",
      "32008",
      "Krakow",
      0);

  public static final Company company2 = new Company(
      "Drutex",
      "1239514823",
      "Rybna",
      "31158",
      "Rybnik",
      0.2);

  public static final Company company3 = new Company(
      "WasBud",
      "1458796325",
      "Targowa",
      "15689",
      "warszawa",
      0.1);

  public static final Company company4 = new Company(
      "DrukPol",
      "1452369135",
      "Bolesnej Meki Panskiej",
      "58963",
      "Sosnowiec",
      0.3);
}
