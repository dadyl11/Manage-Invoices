package pl.coderstrust.accounting.helpers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.configuration.JacksonProvider.getObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;
import pl.coderstrust.accounting.model.Invoice;

//@Service
public class RestHelper extends TestBaseWithMockMvc {

//  @Autowired
//  MockMvc mockMvc;

  private static final String INVOICE_SERVICE_PATH = "/invoices";
  private static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;
  InvoiceConverter invoiceConverter = new InvoiceConverter(getObjectMapper());

  public int callRestServiceToAddInvoiceAndReturnId(Invoice invoice) throws Exception {
    String response =
        mockMvc
            .perform(post(INVOICE_SERVICE_PATH)
                .content(invoiceConverter.convertInvoiceToJson(invoice))
                .contentType(JSON_CONTENT_TYPE))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    return Integer.parseInt(response);
  }

  public Invoice callRestServiceToReturnInvoiceById(int id) throws Exception {
    String invoiceJson = mockMvc.perform(get(INVOICE_SERVICE_PATH + "/" + id))
        .andReturn()
        .getResponse()
        .getContentAsString();
    return invoiceConverter.convertJsonToInvoice(invoiceJson);
  }
}