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
    InvoiceProvider.INVOICE_GRUDZIADZ_2017.addInvoiceEntry(InvoiceEntryProvider.SPAN);
    InvoiceProvider.INVOICE_GRUDZIADZ_2017.addInvoiceEntry(InvoiceEntryProvider.CLAMP);
    BigDecimal actual = InvoiceProvider.INVOICE_GRUDZIADZ_2017.getNetValue();
    BigDecimal expected = BigDecimal.valueOf(50.4);

    //then
    assertThat(actual, is(expected));
  }

  @Test
  public void returnsListOfEntries() {
    //given
    InvoiceProvider.INVOICE_CHELMNO_2016.addInvoiceEntry(InvoiceEntryProvider.SPAN);
    List<InvoiceEntry> expected = new ArrayList<>();
    expected.add(InvoiceEntryProvider.SPAN);

    //when
    List<InvoiceEntry> actual = InvoiceProvider.INVOICE_CHELMNO_2016.getEntries();

    //then
    assertThat(actual, is(expected));
  }

  @Test
  public void addEntryToList() {
    //when
    InvoiceProvider.INVOICE_KRAKOW_2018.addInvoiceEntry(InvoiceEntryProvider.SUPPORT);

    //then
    assertThat(InvoiceProvider.INVOICE_KRAKOW_2018.getEntries().get(0),
        is(InvoiceEntryProvider.SUPPORT));
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
    assertPojoMethodsFor(classUnderTest)
        .testing(Method.GETTER, Method.SETTER)
        .areWellImplemented();
  }
}