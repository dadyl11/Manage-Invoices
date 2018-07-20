package pl.coderstrust.accounting.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import pl.coderstrust.accounting.helpers.InvoiceEntryProvider;
import pl.coderstrust.accounting.helpers.InvoiceProvider;
import pl.pojo.tester.api.assertion.Method;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTest {

  @Test
  public void shouldCalculateNetValue() {
    //when
    InvoiceProvider.invoice2.addInvoiceEntry(InvoiceEntryProvider.InvoiceEntry1);
    InvoiceProvider.invoice2.addInvoiceEntry(InvoiceEntryProvider.InvoiceEntry2);
    BigDecimal actual = InvoiceProvider.invoice2.getNetValue();
    BigDecimal expected = BigDecimal.valueOf(50.4);

    //then
    assertThat(actual, is(expected));
  }

  @Test
  public void returnsListOfEntries() {
    //given
    InvoiceProvider.invoice3.addInvoiceEntry(InvoiceEntryProvider.InvoiceEntry1);
    List<InvoiceEntry> expected = new ArrayList<>();
    expected.add(InvoiceEntryProvider.InvoiceEntry1);

    //when
    List<InvoiceEntry> actual = InvoiceProvider.invoice3.getEntries();

    //then
    assertThat(actual, is(expected));
  }

  @Test
  public void addEntryToList() {
    //when
    InvoiceProvider.invoice1.addInvoiceEntry(InvoiceEntryProvider.InvoiceEntry3);

    //then
    assertThat(InvoiceProvider.invoice1.getEntries().get(0),
        is(InvoiceEntryProvider.InvoiceEntry3));
  }

  @Test
  public void invoiceEntryShouldPassAllPojoTestsForGettersAndSetters() {
    // given
    final Class<?> classUnderTest = InvoiceEntry.class;

    // when

    // then
    assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
  }

  @Test
  public void invoiceShouldPassAllPojoTestsForGettersAndSetters() {
    // given
    final Class<?> classUnderTest = Invoice.class;

    // when

    // then
    assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
  }
}