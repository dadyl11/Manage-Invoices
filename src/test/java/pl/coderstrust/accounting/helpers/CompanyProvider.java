package pl.coderstrust.accounting.helpers;

import java.math.BigDecimal;
import pl.coderstrust.accounting.model.Company;

public class CompanyProvider {

  public static final Company COMPANY_TRANSPOL = new Company(
      "Transpol",
      "6752339483",
      "Grodzka",
      "32008",
      "Krakow",
      BigDecimal.ZERO);

  public static final Company COMPANY_DRUTEX = new Company(
      "Drutex",
      "1239514823",
      "Rybna",
      "31158",
      "Rybnik",
      BigDecimal.valueOf(0.2));

  public static final Company COMPANY_WASBUD = new Company(
      "WasBud",
      "1458796325",
      "Targowa",
      "15689",
      "warszawa",
      BigDecimal.valueOf(0.1));

  public static final Company COMPANY_DRUKPOL = new Company(
      "DrukPol",
      "1452369135",
      "Bolesnej Meki Panskiej",
      "58963",
      "Sosnowiec",
      BigDecimal.valueOf(0.3));
}
