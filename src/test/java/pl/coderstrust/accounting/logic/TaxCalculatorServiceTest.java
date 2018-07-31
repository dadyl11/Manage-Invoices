package pl.coderstrust.accounting.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUKPOL;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUTEX;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_TRANSPOL;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_WASBUD;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_RADOMSKO_2018;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxCalculatorServiceTest {

  @Mock
  InvoiceService invoiceService;

  @InjectMocks
  TaxCalculatorService taxCalculatorService;

  @Test
  public void shouldReturnZeroWhenNoInvoices() {
    //given
    when(invoiceService.getInvoices()).thenReturn(new ArrayList<>());

    //when
    taxCalculatorService.setCompany(COMPANY_WASBUD);
    BigDecimal actual = taxCalculatorService.getValueFromInvoices(taxCalculatorService::filterBuyer,
        taxCalculatorService::taxToBigDecimal);

    //then
    assertThat(actual, is(BigDecimal.ZERO));
  }

  @Test
  public void shouldReturnZeroWhenNoCompanySpecified() {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_GRUDZIADZ_2017);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::incomeToBigDecimal);

    //then
    assertThat(actual, is(BigDecimal.ZERO));
  }

  @Test
  public void shouldReturnIncome() {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_RADOMSKO_2018);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given
    taxCalculatorService.setCompany(COMPANY_DRUTEX);
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::incomeToBigDecimal);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }

  @Test
  public void shouldReturnCosts() {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_CHELMNO_2016);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given
    taxCalculatorService.setCompany(COMPANY_TRANSPOL);
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterBuyer,
            taxCalculatorService::incomeToBigDecimal);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }


  @Test
  public void shouldReturnVATdue() {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_RADOMSKO_2018, INVOICE_GRUDZIADZ_2017);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given
    taxCalculatorService.setCompany(COMPANY_WASBUD);
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::taxToBigDecimal);

    //then
    assertThat(actual, is(BigDecimal.valueOf(11.592)));
  }

  @Test
  public void shouldReturnVATincluded() {
    //when
    List<Invoice> invoices = Arrays
        .asList(INVOICE_RADOMSKO_2018, INVOICE_GRUDZIADZ_2017, INVOICE_CHELMNO_2016);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given
    taxCalculatorService.setCompany(COMPANY_DRUKPOL);
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterBuyer,
            taxCalculatorService::taxToBigDecimal);

    //then
    assertThat(actual, is(BigDecimal.valueOf(25.032)));
  }
}