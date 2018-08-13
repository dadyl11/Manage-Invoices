package pl.coderstrust.accounting.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_RADOMSKO_2018;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxCalculatorServiceTest {

  private String drukpolNip = "1452369135";
  private String wasbudNip = "1458796325";
  private String drutexNip = "1239514823";
  private String transpolNip = "6752339483";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  InvoiceService invoiceService;

  @InjectMocks
  TaxCalculatorService taxCalculatorService;

  @Test
  public void shouldReturnZeroWhenNoInvoices() throws IOException {
    //given
    when(invoiceService.getInvoices()).thenReturn(new ArrayList<>());

    //when

    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, wasbudNip);

    //then
    assertThat(actual, is(BigDecimal.ZERO));
  }

  @Test
  public void shouldReturnIllegalArgumentExceptionWhenNipIsInvalid() {
  }

  @Test
  public void shouldReturnIncome() throws IOException {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_RADOMSKO_2018);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given

    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, drutexNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }


  @Test
  public void shouldReturnCosts() throws IOException {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_CHELMNO_2016);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given

    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, transpolNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }


  @Test
  public void shouldReturnVatDue() throws IOException {
    //when
    List<Invoice> invoices = Arrays.asList(INVOICE_RADOMSKO_2018, INVOICE_GRUDZIADZ_2017);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, wasbudNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(11.592)));
  }

  @Test
  public void shouldReturnVatIncluded() throws IOException {
    //when
    List<Invoice> invoices = Arrays
        .asList(INVOICE_RADOMSKO_2018, INVOICE_GRUDZIADZ_2017, INVOICE_CHELMNO_2016);
    when(invoiceService.getInvoices()).thenReturn(invoices);

    //given

    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, drukpolNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(25.032)));
  }
}