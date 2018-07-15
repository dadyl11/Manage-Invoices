package pl.coderstrust.accounting.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;

public class InvoiceEntryTest {

  BigDecimal price = BigDecimal.valueOf(10000);
  BigDecimal quantity = BigDecimal.valueOf(2);
  VatRate vatRate = VatRate.NORMAL;
  InvoiceEntry invoiceEntry = new InvoiceEntry("Invoice Entry nr 1", price, vatRate, quantity);

  @Test
  public void shouldReturnQuantity() {
    //When
    BigDecimal actual = invoiceEntry.getQuantity();
    BigDecimal expected = BigDecimal.valueOf(2);

    //Then
    assertThat(actual, is(expected));
  }

  @Test
  public void shouldReturnDescription() {
    //When
    String actual = invoiceEntry.getDescription();
    String expected = "Invoice Entry nr 1";

    //Then
    assertThat(actual, is(expected));
  }

  @Test
  public void shouldReturnVatRate() {
    //When
    VatRate actual = invoiceEntry.getVatRate();
    VatRate expected = VatRate.NORMAL;

    //Then
    assertThat(actual, is(expected));
  }

  @Test
  public void shouldReturnPrice() {
    //When
    BigDecimal actual = invoiceEntry.getNetPrice();
    BigDecimal expected = BigDecimal.valueOf(10000);

    //Then
    assertThat(actual, is(expected));
  }
}