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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.controller.NipValidator;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(SpringRunner.class)
@SpringBootTest
//TODO Swap DirtiesContext with cleardatabase() method after merging master with inFilenotdeleting invoices branch
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TaxCalculatorServiceTest {

  private String drukpolNip = "5311688030";
  private String wasbudNip = "6271206366";
  private String drutexNip = "8421622720";
  private String transpolNip = "5621760000";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Autowired
  private InvoiceService invoiceService;

  @Autowired
  TaxCalculatorService taxCalculatorService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void shouldReturnZeroWhenNoInvoices() throws IOException {
    //given

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

    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Nip does not match specified pattern");
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, incorrectNip);
  }

  @Test
  public void shouldReturnIncome() throws IOException {
    //given
    invoiceService.saveInvoice(INVOICE_KRAKOW_2018);
    invoiceService.saveInvoice(INVOICE_RADOMSKO_2018);

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
    invoiceService.saveInvoice(INVOICE_KRAKOW_2018);
    invoiceService.saveInvoice(INVOICE_CHELMNO_2016);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, transpolNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(138)));
  }


  @Test
  public void shouldReturnVatDue() throws IOException {
    //given
    invoiceService.saveInvoice(INVOICE_RADOMSKO_2018);
    invoiceService.saveInvoice(INVOICE_GRUDZIADZ_2017);

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
    invoiceService.saveInvoice(INVOICE_RADOMSKO_2018);
    invoiceService.saveInvoice(INVOICE_GRUDZIADZ_2017);
    invoiceService.saveInvoice(INVOICE_CHELMNO_2016);

    //when
    BigDecimal actual = taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, drukpolNip);

    //then
    assertThat(actual, is(BigDecimal.valueOf(25.032)));
  }
}