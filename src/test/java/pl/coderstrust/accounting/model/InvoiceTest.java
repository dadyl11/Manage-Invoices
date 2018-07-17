package pl.coderstrust.accounting.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTest {

  Company company = mock(Company.class);
  InvoiceEntry invoiceEntry = mock(InvoiceEntry.class);
  Invoice invoice = new Invoice();


  @Test
  public void shouldCalculateNetValue() {
    //When
    when(invoiceEntry.getNetPrice()).thenReturn(BigDecimal.TEN);
    when(invoiceEntry.getQuantity()).thenReturn(BigDecimal.TEN);
    when(company.getDiscount()).thenReturn(0.2);
    invoice.addInvoiceEntry(invoiceEntry);
    BigDecimal expected = BigDecimal.valueOf(80.0);
    invoice.setBuyer(new Company());
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
    invoice.addInvoiceEntry(invoiceEntry);

    //Then
    assertTrue(!invoice.getEntries().isEmpty());
  }

  @Test
  public void returnsId() {
    //When
    invoice.setId(1);
    int actual = invoice.getId();

    assertThat(actual, is(1));

  }
}