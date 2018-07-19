package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;

public class InvoiceProvider {

  public static final Invoice invoice1 = new Invoice(
      1,
      "1/2018",
      LocalDate.of(2018, 05, 12),
      LocalDate.of(2018, 05, 12),
      "Krakow",
      CompanyProvider.company1,
      CompanyProvider.company2);

  public static final Invoice invoice2 = new Invoice(
      2,
      "2/2018",
      LocalDate.of(2017, 03, 03),
      LocalDate.of(2017, 03, 02),
      "Grudziadz",
      CompanyProvider.company4,
      CompanyProvider.company3);

  public static final Invoice invoice3 = new Invoice(
      3,
      "3/2018",
      LocalDate.of(2016, 03, 05),
      LocalDate.of(2016, 02, 04),
      "Chelmno",
      CompanyProvider.company4,
      CompanyProvider.company1);

  public static final Invoice invoice4 = new Invoice(
      1,
      "4/2018",
      LocalDate.of(2018, 06, 13),
      LocalDate.of(2018, 05, 12),
      "Bydgoszcz",
      CompanyProvider.company3,
      CompanyProvider.company2);
}
