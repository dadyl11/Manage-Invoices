package pl.coderstrust.accounting.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GDANSK_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_LESKO_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_PRZEMYSL_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_SANOK_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_SOPOT_2018;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceValidatorTest {

  @Mock
  private Invoice invoice;
  private InvoiceEntry invoiceEntry;
  private Company company;


  @InjectMocks
  private InvoiceValidator invoiceValidator;

  @Test
  public void shouldValidateTheMessageWhenIdentifierIsBlank() {
    //given
    String message = "Sale place not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_SANOK_2018);

    //then
    assertThat(actualtest, hasItem(message));
  }

  @Test
  public void shouldValidateThatIdentifierIsBlankAndProperMessageIsAdded() {
    //given
    String message = "Identifier not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_LESKO_2018);

    //then
    assertThat(actualtest, hasItem(message));
  }

  @Test
  public void shouldValidateThatEntriesListIsEmptyAndProperMessageIsAdded() {
    //given
    String message = "Entries not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_GDANSK_2018);

    //then
    assertThat(actualtest, hasItem(message));
  }

  @Test
  public void shouldValidateThatSaleDateIsNullAndProperMessageIsAdded() {
    //given
    String message = "Sale date not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_PRZEMYSL_2018);

    //then
    assertThat(actualtest, hasItem(message));
  }

  @Test
  public void shouldValidateThatBuyerIsNullAndProperMessageIsAdded() {
    //given
    String message = "Entries not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_GDANSK_2018);

    //then
    assertThat(actualtest, hasItem(message));
  }

  @Test
  public void shouldValidateThatSalePlaceNullAndProperMessageIsAdded() {
    //given
    String message = "Sale place not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_SOPOT_2018);

    //then
    assertThat(actualtest, hasItem(message));
  }

  @Test
  public void shouldValidateThatAllFieldsAreFilledUp() {
    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_GRUDZIADZ_2017);

    //then
    assertThat(actualtest, (IsEmptyCollection.empty()));
  }
}