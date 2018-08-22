package pl.coderstrust.accounting.helpers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderstrust.accounting.model.Invoice;

public class InvoiceAssertion extends TestBaseWithMockMvc {

  private static final String INVOICE_SERVICE_PATH = "/invoices";


  public void assertSingleInvoice(int id, Invoice invoice) throws Exception {
    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(id)))
        .andExpect(jsonPath("$.identifier", is(invoice.getIdentifier())))
        .andExpect(jsonPath("$.salePlace", is(invoice.getSalePlace())))
        .andExpect(jsonPath("$.buyer.name", is(invoice.getBuyer().getName())))
        .andExpect(jsonPath("$.buyer.nip", is(invoice.getBuyer().getNip())))
        .andExpect(jsonPath("$.buyer.street", is(invoice.getBuyer().getStreet())))
        .andExpect(jsonPath("$.buyer.postalCode", is(invoice.getBuyer().getPostalCode())))
        .andExpect(jsonPath("$.buyer.discount", is(invoice.getBuyer().getDiscount().doubleValue())))
        .andExpect(jsonPath("$.seller.name", is(invoice.getSeller().getName())))
        .andExpect(jsonPath("$.seller.nip", is(invoice.getSeller().getNip())))
        .andExpect(jsonPath("$.seller.street", is(invoice.getSeller().getStreet())))
        .andExpect(jsonPath("$.seller.postalCode", is(invoice.getSeller().getPostalCode())))
        .andExpect(jsonPath("$.seller.discount", is(invoice.getSeller().getDiscount().doubleValue())))
        .andExpect(jsonPath("$.entries[0].description", is(invoice.getEntries().get(0).getDescription())))
        .andExpect(jsonPath("$.entries[0].netPrice", is(invoice.getEntries().get(0).getNetPrice().intValue())))
        .andExpect(jsonPath("$.entries[0].vatRate", is(invoice.getEntries().get(0).getVatRate().toString())))
        .andExpect(jsonPath("$.entries[0].quantity", is(invoice.getEntries().get(0).getQuantity().intValue())));
  }

  public void assertInvoiceFromList(int id, Invoice invoice, String index) throws Exception {
    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[" + index + "].id", is(id)))
        .andExpect(jsonPath("$[" + index + "].identifier", is(invoice.getIdentifier())))
        .andExpect(jsonPath("$[" + index + "].salePlace", is(invoice.getSalePlace())))
        .andExpect(jsonPath("$[" + index + "].buyer.name", is(invoice.getBuyer().getName())))
        .andExpect(jsonPath("$[" + index + "].buyer.nip", is(invoice.getBuyer().getNip())))
        .andExpect(jsonPath("$[" + index + "].buyer.street", is(invoice.getBuyer().getStreet())))
        .andExpect(jsonPath("$[" + index + "].buyer.postalCode", is(invoice.getBuyer().getPostalCode())))
        .andExpect(jsonPath("$[" + index + "].buyer.discount", is(invoice.getBuyer().getDiscount().doubleValue())))
        .andExpect(jsonPath("$[" + index + "].seller.name", is(invoice.getSeller().getName())))
        .andExpect(jsonPath("$[" + index + "].seller.nip", is(invoice.getSeller().getNip())))
        .andExpect(jsonPath("$[" + index + "].seller.street", is(invoice.getSeller().getStreet())))
        .andExpect(jsonPath("$[" + index + "].seller.postalCode", is(invoice.getSeller().getPostalCode())))
        .andExpect(jsonPath("$[" + index + "].seller.discount", is(invoice.getSeller().getDiscount().doubleValue())))
        .andExpect(jsonPath("$[" + index + "].entries[0].description", is(invoice.getEntries().get(0).getDescription())))
        .andExpect(jsonPath("$[" + index + "].entries[0].netPrice", is(invoice.getEntries().get(0).getNetPrice().intValue())))
        .andExpect(jsonPath("$[" + index + "].entries[0].vatRate", is(invoice.getEntries().get(0).getVatRate().toString())))
        .andExpect(jsonPath("$[" + index + "].entries[0].quantity", is(invoice.getEntries().get(0).getQuantity().intValue())));
  }
}