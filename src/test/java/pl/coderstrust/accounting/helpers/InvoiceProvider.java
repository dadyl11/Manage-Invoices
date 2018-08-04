package pl.coderstrust.accounting.helpers;

import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.ONE_LINK;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.SPAN_CLAMP;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.SPAN_CLAMP_SUPPORT;

import java.time.LocalDate;
import pl.coderstrust.accounting.model.Invoice;

public class InvoiceProvider {

  public static final Invoice INVOICE_KRAKOW_2018 = new Invoice(
      1,
      "1/2018",
      LocalDate.of(2018, 05, 12),
      LocalDate.of(2018, 05, 12),
      "Krakow",
      CompanyProvider.COMPANY_TRANSPOL,
      CompanyProvider.COMPANY_DRUTEX,
      SPAN_CLAMP_SUPPORT);

  public static final Invoice INVOICE_GRUDZIADZ_2017 = new Invoice(
      2,
      "2/2018",
      LocalDate.of(2017, 03, 03),
      LocalDate.of(2017, 03, 02),
      "Grudziadz",
      CompanyProvider.COMPANY_DRUKPOL,
      CompanyProvider.COMPANY_WASBUD,
      SPAN_CLAMP);

  public static final Invoice INVOICE_CHELMNO_2016 = new Invoice(
      3,
      "3/2018",
      LocalDate.of(2016, 03, 05),
      LocalDate.of(2016, 02, 04),
      "Chelmno",
      CompanyProvider.COMPANY_DRUKPOL,
      CompanyProvider.COMPANY_TRANSPOL,
      SPAN_CLAMP_SUPPORT);

  public static final Invoice INVOICE_BYDGOSZCZ_2018 = new Invoice(
      1,
      "4/2018",
      LocalDate.of(2018, 06, 13),
      LocalDate.of(2018, 05, 12),
      "Bydgoszcz",
      CompanyProvider.COMPANY_WASBUD,
      CompanyProvider.COMPANY_DRUTEX,
      ONE_LINK);
}
