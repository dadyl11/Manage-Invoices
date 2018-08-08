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
import pl.coderstrust.accounting.controller.NipValidator;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxCalculatorServiceTest {

  private String drukpolNip = "5311688030";
  private String wasbudNip = "6271206366";
  private String drutexNip = "8421622720";
  private String transpolNip = "5621760000";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Mock
  InvoiceService invoiceService;

  @Mock
  NipValidator nipValidator;

  @InjectMocks
  TaxCalculatorService taxCalculatorService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void shouldReturnZeroWhenNoInvoices() throws IOException {
    //given
    when(invoiceService.getInvoices()).thenReturn(new ArrayList<>());
    when(nipValidator.isValid(wasbudNip)).thenReturn(true);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, wasbudNip);

    //then
    assertThat(actual, is(BigDecimal.ZERO));
  }

  @Test
  public void shouldReturnIllegalArgumentExceptionWhenNipIsInvalid() throws IOException {
    //given
    String incorrectNip = "1234567890";
    when(nipValidator.isValid(incorrectNip)).thenReturn(false);

    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Nip is incorrect");
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, incorrectNip);
  }

  @Test
  public void shouldReturnIncome() throws IOException {
    //given
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_RADOMSKO_2018);
    when(invoiceService.getInvoices()).thenReturn(invoices);
    when(nipValidator.isValid(drutexNip)).thenReturn(true);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, drutexNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }


  @Test
  public void shouldReturnCosts() throws IOException {
    //given
    List<Invoice> invoices = Arrays.asList(INVOICE_KRAKOW_2018, INVOICE_CHELMNO_2016);
    when(invoiceService.getInvoices()).thenReturn(invoices);
    when(nipValidator.isValid(transpolNip)).thenReturn(true);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, transpolNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }


  @Test
  public void shouldReturnVatDue() throws IOException {
    //giben
    List<Invoice> invoices = Arrays.asList(INVOICE_RADOMSKO_2018, INVOICE_GRUDZIADZ_2017);
    when(invoiceService.getInvoices()).thenReturn(invoices);
    when(nipValidator.isValid(wasbudNip)).thenReturn(true);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, wasbudNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(11.592)));
  }

  @Test
  public void shouldReturnVatIncluded() throws IOException {
    //given
    List<Invoice> invoices = Arrays
        .asList(INVOICE_RADOMSKO_2018, INVOICE_GRUDZIADZ_2017, INVOICE_CHELMNO_2016);
    when(invoiceService.getInvoices()).thenReturn(invoices);
    when(nipValidator.isValid(drukpolNip)).thenReturn(true);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, drukpolNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(25.032)));
  }
}