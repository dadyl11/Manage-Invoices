package pl.coderstrust.accounting.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_SANOK_2018;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceValidatorTest {

  @Mock
  private Invoice invoice;
  private InvoiceEntry invoiceEntry;

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
  public void shouldValidateThatAllFieldsAreFilledUp() {
    //given
    String message = "Sale place not found";

    //when
    List<String> actualtest = invoiceValidator.validate(INVOICE_GRUDZIADZ_2017);

    //then
    assertThat(actualtest, (IsEmptyCollection.empty()));
  }
}