package pl.coderstrust.accounting.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.MockitoAnnotations.initMocks;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_CITY;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_DISCOUNT;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_NAME;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_NIP;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_POSTAL_CODE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_STREET;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_DESCRIPTION;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_NET_PRICE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_QUNTITY;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_VAT_RATE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_IDENTIFIER;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ISSUE_DATE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SALE_DATE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SALE_PLACE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_CITY;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_DISCOUNT;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_NAME;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_NIP;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_POSTAL_CODE;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_STREET;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_EMPTY_ENTRIES;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(Parameterized.class)
public class InvoiceValidatorTest {

  @Mock
  private Invoice invoice;

  @InjectMocks
  private InvoiceValidator invoiceValidator;

  @Parameterized.Parameters(name
      = "{index}: Test with invoice, message")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {INVOICE_BLANK_SALE_PLACE, "Sale place not found"},
        {INVOICE_BLANK_IDENTIFIER, "Identifier not found"},
        {INVOICE_EMPTY_ENTRIES, "Entries not found"},
        {INVOICE_BLANK_SALE_DATE, "Sale date not found"},
        {INVOICE_BLANK_ISSUE_DATE, "Issue date not found"},
        {INVOICE_BLANK_BUYER_NAME, "Buyer name not found"},
        {INVOICE_BLANK_BUYER_NIP, "Buyer nip not found"},
        {INVOICE_BLANK_BUYER_STREET, "Buyer street not found"},
        {INVOICE_BLANK_BUYER_POSTAL_CODE, "Buyer postal code not found"},
        {INVOICE_BLANK_BUYER_CITY, "Buyer city not found"},
        {INVOICE_BLANK_BUYER_DISCOUNT, "Buyer discount not found"},
        {INVOICE_BLANK_SELLER_NAME, "Seller name not found"},
        {INVOICE_BLANK_SELLER_NIP, "Seller nip not found"},
        {INVOICE_BLANK_SELLER_STREET, "Seller street not found"},
        {INVOICE_BLANK_SELLER_POSTAL_CODE, "Seller postal code not found"},
        {INVOICE_BLANK_SELLER_CITY, "Seller city not found"},
        {INVOICE_BLANK_SELLER_DISCOUNT, "Seller discount not found"},
        {INVOICE_BLANK_ENTRY_DESCRIPTION, "Entry description not found"},
        {INVOICE_BLANK_ENTRY_NET_PRICE, "Net price for entry not found"},
        {INVOICE_BLANK_ENTRY_VAT_RATE, "Vat rate for entry not found"},
        {INVOICE_BLANK_ENTRY_QUNTITY, "Quantity for entry not found"}

    });
  }

  private final Invoice invoiceToTest;
  private final String message;

  public InvoiceValidatorTest(Invoice invoiceToTest, String message) {
    this.invoiceToTest = invoiceToTest;
    this.message = message;
  }

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void testNotCompletedInvoicesWithCorrespondingMessage() {

    List<String> actualTest = invoiceValidator.validate(invoiceToTest);
    //then
    assertThat(actualTest, hasItem(message));
  }
}