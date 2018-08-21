package pl.coderstrust.accounting.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUKPOL;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUTEX;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_TRANSPOL;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_WASBUD;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_RADOMSKO_2018;

import java.math.BigDecimal;
import org.junit.Test;
import pl.coderstrust.accounting.controller.NipValidator;
import pl.coderstrust.accounting.database.impl.memory.InMemoryDatabase;
import pl.coderstrust.accounting.helpers.CompanyProvider;

public class TaxCalculatorServiceTest {

  private InvoiceService invoiceService = new InvoiceService(new InMemoryDatabase());
  private TaxCalculatorService taxCalculatorService = new TaxCalculatorService(invoiceService, new NipValidator());

  @Test
  public void shouldReturnZeroWhenNoInvoices() {
    //given

    //when
    BigDecimal actual = taxCalculatorService.getTaxDue(CompanyProvider.COMPANY_WASBUD.getNip());

    //then
    assertThat(actual, is(BigDecimal.ZERO));
  }

  @Test
  public void shouldReturnIncome() {
    //given
    invoiceService.saveInvoice(INVOICE_KRAKOW_2018);
    invoiceService.saveInvoice(INVOICE_RADOMSKO_2018);

    //when
    BigDecimal actual = taxCalculatorService.getIncome(COMPANY_DRUTEX.getNip());

    //then
    assertThat(actual, is(BigDecimal.valueOf(138.0)));
  }

  @Test
  public void shouldReturnCosts() {
    //given
    invoiceService.saveInvoice(INVOICE_KRAKOW_2018);
    invoiceService.saveInvoice(INVOICE_CHELMNO_2016);

    //when
    BigDecimal actual = taxCalculatorService.getCosts(COMPANY_TRANSPOL.getNip());

    //then
    assertThat(actual, is(BigDecimal.valueOf(138.0)));
  }

  @Test
  public void shouldReturnVatDue() {
    //given
    invoiceService.saveInvoice(INVOICE_RADOMSKO_2018);
    invoiceService.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    BigDecimal actual = taxCalculatorService.getTaxDue(COMPANY_WASBUD.getNip());

    //then
    assertThat(actual, is(BigDecimal.valueOf(14.088)));
  }

  @Test
  public void shouldReturnVatIncluded() {
    //given
    invoiceService.saveInvoice(INVOICE_RADOMSKO_2018);
    invoiceService.saveInvoice(INVOICE_GRUDZIADZ_2017);
    invoiceService.saveInvoice(INVOICE_CHELMNO_2016);

    // when
    BigDecimal actual = taxCalculatorService
        .getTaxIncluded(COMPANY_DRUKPOL.getNip());

    // then
    assertThat(actual, is(BigDecimal.valueOf(25.032)));
  }
}