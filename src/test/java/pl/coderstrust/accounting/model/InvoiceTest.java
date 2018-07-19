package pl.coderstrust.accounting.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import pl.pojo.tester.api.ClassAndFieldPredicatePair;
import pl.pojo.tester.api.FieldPredicate;
import pl.pojo.tester.api.assertion.Method;

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
    invoice.setBuyer(company);
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
  public void shouldPassAllPojoTestsForGettersAndSetters() {
    // given
    final Class<?> classUnderTest = InvoiceEntry.class;

    // when

    // then
    assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
  }


  @Test
  public void shouldSetAndGetId() {
    //Given

    // when
    invoice.setId(2);
    int expected = 2;

    // then
    assertThat(invoice.getId(), is(expected));

  }

}