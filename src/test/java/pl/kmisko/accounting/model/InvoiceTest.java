package pl.kmisko.accounting.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static pl.kmisko.accounting.helpers.InvoiceEntryProvider.CLAMP;
import static pl.kmisko.accounting.helpers.InvoiceEntryProvider.EMPTY;
import static pl.kmisko.accounting.helpers.InvoiceEntryProvider.SPAN;
import static pl.kmisko.accounting.helpers.InvoiceEntryProvider.SUPPORT;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_TRANSPOL_SPAN_CLAMP_SUPPORT_2016;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_WASBUD_SPAN_CLAMP_2017;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import pl.kmisko.accounting.helpers.CompanyProvider;
import pl.pojo.tester.api.assertion.Method;

public class InvoiceTest {

  @Test
  public void shouldCalculateNetValue() {
    //when
    BigDecimal actual = INVOICE_WASBUD_SPAN_CLAMP_2017.getTotalNetValue();
    BigDecimal expected = BigDecimal.valueOf(50.4);

    //then
    assertThat(actual, is(expected));
  }

  @Test
  public void returnsListOfEntries() {

    //when
    List<InvoiceEntry> actual = INVOICE_TRANSPOL_SPAN_CLAMP_SUPPORT_2016.getEntries();

    //then
    assertThat(actual.size(), is(3));
    assertThat(actual, hasItem(SPAN));
    assertThat(actual, hasItem(CLAMP));
    assertThat(actual, hasItem(SUPPORT));
  }

  @Test
  public void addEntryToList() {
    //given
    Invoice invoice = new Invoice.Invoicebuilder()
        .identifier("1/2018")
        .issueDate(LocalDate.of(2018, 7, 7))
        .saleDate(LocalDate.of(2018, 7, 7))
        .salePlace("Lodz")
        .buyer(CompanyProvider.COMPANY_DRUTEX)
        .seller(CompanyProvider.COMPANY_BLANK_STREET)
        .entries(EMPTY)
        .build();

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