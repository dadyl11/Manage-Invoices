package pl.kmisko.accounting.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BAD_DISCOUNT_VALUE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_CITY;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_NAME;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_NIP;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_POSTAL_CODE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_STREET;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_DESCRIPTION;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_NET_PRICE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_QUNTITY;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ENTRY_VAT_RATE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_IDENTIFIER;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_ISSUE_DATE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SALE_DATE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SALE_PLACE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_CITY;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_NAME;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_NIP;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_POSTAL_CODE;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_BLANK_SELLER_STREET;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_EMPTY_ENTRIES;
import static pl.kmisko.accounting.helpers.InvoiceProvider.INVOICE_INCORRECT_VAT_RATE;

import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.kmisko.accounting.model.Invoice;

@RunWith(JUnitParamsRunner.class)
public class InvoiceValidatorTest {
  
  private NipValidator nipValidator = new NipValidator();
  private CompanyValidator companyValidator = new CompanyValidator(nipValidator);
  private InvoiceEntryValidator invoiceEntryValidator = new InvoiceEntryValidator();
  private InvoiceValidator invoiceValidator = new InvoiceValidator(companyValidator, invoiceEntryValidator);

  @Parameters(method = "invoicesWithEmptyFields")
  @Test
  public void shouldCheckIfReturnedValidationMessageCorrespondsToIncompleteInvoices(Invoice invoice, String message) {
    //when
    List<String> actualTest = invoiceValidator.validate(invoice);

    //then
    assertThat(actualTest, hasItem(message));
  }

  private Object[] invoicesWithEmptyFields() {
    return new Object[]{
        new Object[]{INVOICE_BLANK_SALE_PLACE, "Sale place not found"},
        new Object[]{INVOICE_BLANK_IDENTIFIER, "Identifier not found"},
        new Object[]{INVOICE_EMPTY_ENTRIES, "Entries not found"},
        new Object[]{INVOICE_BLANK_SALE_DATE, "Sale date not found"},
        new Object[]{INVOICE_BLANK_ISSUE_DATE, "Issue date not found"},
        new Object[]{INVOICE_BLANK_BUYER_NAME, "Buyer name not found"},
        new Object[]{INVOICE_BLANK_BUYER_NIP, "Buyer nip not found"},
        new Object[]{INVOICE_BLANK_BUYER_STREET, "Buyer street not found"},
        new Object[]{INVOICE_BLANK_BUYER_POSTAL_CODE, "Buyer postal code not found"},
        new Object[]{INVOICE_BLANK_BUYER_CITY, "Buyer city not found"},
        new Object[]{INVOICE_BLANK_SELLER_NAME, "Seller name not found"},
        new Object[]{INVOICE_BLANK_SELLER_NIP, "Seller nip not found"},
        new Object[]{INVOICE_BLANK_SELLER_STREET, "Seller street not found"},
        new Object[]{INVOICE_BLANK_SELLER_POSTAL_CODE, "Seller postal code not found"},
        new Object[]{INVOICE_BLANK_SELLER_CITY, "Seller city not found"},
        new Object[]{INVOICE_BLANK_ENTRY_DESCRIPTION, "Entry description not found"},
        new Object[]{INVOICE_BLANK_ENTRY_NET_PRICE, "Net price for entry not found"},
        new Object[]{INVOICE_BLANK_ENTRY_VAT_RATE, "Vat rate for entry not found"},
        new Object[]{INVOICE_BLANK_ENTRY_QUNTITY, "Quantity for entry not found"},
        new Object[]{INVOICE_BAD_DISCOUNT_VALUE, "Bad value of discount"},
        new Object[]{INVOICE_INCORRECT_VAT_RATE, "Vat rate is not in accordance to current tariffs"},
    };
  }
}
