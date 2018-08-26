package pl.coderstrust.accounting.helpers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderstrust.accounting.model.Invoice;

public class InvoiceAssertion extends TestBaseWithMockMvc {

  public void assertInvoices(int returnedId, Invoice invoice, Invoice savedInvoice) {
    assertThat(savedInvoice.getId(), is(returnedId));
    assertThat(savedInvoice.getIdentifier(), is(invoice.getIdentifier()));
    assertThat(savedInvoice.getSalePlace(), is(invoice.getSalePlace()));
    assertThat(savedInvoice.getBuyer().getName(), is(invoice.getBuyer().getName()));
    assertThat(savedInvoice.getBuyer().getNip(), is(invoice.getBuyer().getNip()));
    assertThat(savedInvoice.getBuyer().getStreet(), is(invoice.getBuyer().getStreet()));
    assertThat(savedInvoice.getBuyer().getPostalCode(), is(invoice.getBuyer().getPostalCode()));
    assertThat(savedInvoice.getBuyer().getDiscount().doubleValue(), is(invoice.getBuyer().getDiscount().doubleValue()));
    assertThat(savedInvoice.getSeller().getName(), is(invoice.getSeller().getName()));
    assertThat(savedInvoice.getSeller().getNip(), is(invoice.getSeller().getNip()));
    assertThat(savedInvoice.getSeller().getStreet(), is(invoice.getSeller().getStreet()));
    assertThat(savedInvoice.getSeller().getPostalCode(), is(invoice.getSeller().getPostalCode()));
    assertThat(savedInvoice.getSeller().getDiscount().doubleValue(), is(invoice.getSeller().getDiscount().doubleValue()));
    checkEntries(invoice, savedInvoice);
  }

  private void checkEntries(Invoice invoice, Invoice savedInvoice) {
    int size = savedInvoice.getEntries().size();
    for(int i = 0; i < size; i++){
      assertThat(savedInvoice.getEntries().get(i).getDescription(), is(invoice.getEntries().get(i).getDescription()));
      assertThat(savedInvoice.getEntries().get(i).getNetPrice().intValue(), is(invoice.getEntries().get(i).getNetPrice().intValue()));
      assertThat(savedInvoice.getEntries().get(i).getVatRate().toString(), is(invoice.getEntries().get(i).getVatRate().toString()));
      assertThat(savedInvoice.getEntries().get(i).getQuantity().intValue(), is(invoice.getEntries().get(i).getQuantity().intValue()));
    }
  }
}
