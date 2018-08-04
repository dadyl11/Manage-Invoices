package pl.coderstrust.accounting.model;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.CLAMP;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.SPAN;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.SUPPORT;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

public class InvoiceTest {

  @Test
  public void shouldCalculateNetValue() {
    //when
    BigDecimal actual = INVOICE_GRUDZIADZ_2017.getNetValue();
    BigDecimal expected = BigDecimal.valueOf(50.4);

    //then
    assertThat(actual, is(expected));
  }

  @Test
  public void returnsListOfEntries() {

    //when
    List<InvoiceEntry> actual = INVOICE_CHELMNO_2016.getEntries();

    //then
    assertThat(actual, hasItem(SPAN));
    assertThat(actual, hasItem(CLAMP));
    assertThat(actual, hasItem(SUPPORT));
    assertThat(actual.size(), is(3));

  }

  @Test
  public void addEntryToList() {
    //given
    Invoice invoice = new Invoice();

    //when
    invoice.addInvoiceEntry(CLAMP);

    //then
    assertThat(invoice.getEntries(), hasItem(CLAMP));
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