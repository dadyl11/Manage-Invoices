package pl.coderstrust.accounting.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTest {

  Company company = mock(Company.class);
  InvoiceEntry invoiceEntry = mock(InvoiceEntry.class);
  Invoice invoice = new Invoice(1, company);


  @Test
  public void shouldCalculateNetValue() {
    //When
    when(invoiceEntry.getNetPrice()).thenReturn(BigDecimal.TEN);
    when(invoiceEntry.getQuantity()).thenReturn(BigDecimal.TEN);
    when(company.getDiscount()).thenReturn(0.2);
    invoice.addsInvoiceEntry(invoiceEntry);
    BigDecimal expected = BigDecimal.valueOf(80.0);
    BigDecimal actual = invoice.getNetValue();

    //Then
    assertThat(actual, is(expected));
  }

  @Test
  public void returnsListOfEntries() {
    //When
    List<InvoiceEntry> actual = invoice.getEntries();
    List<InvoiceEntry> expected = new ArrayList<>();

    //Then
    assertThat(actual, is(expected));
  }

  @Test
  public void addEntryToList() {
    //When
    invoice.addsInvoiceEntry(invoiceEntry);

    //Then
    assertTrue(!invoice.getEntries().isEmpty());
  }

  @Test
  public void returnsId() {
    //When
    int actual = invoice.getId();

    assertThat(actual, is(1));

  }
}