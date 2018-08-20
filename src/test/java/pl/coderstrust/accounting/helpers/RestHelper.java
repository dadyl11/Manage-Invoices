package pl.coderstrust.accounting.helpers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.configuration.JacksonProvider.getObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderstrust.accounting.model.Invoice;

@Service
public class RestHelper {

  private static final String INVOICE_SERVICE_PATH = "/invoices";
  private static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;

  @Autowired
  private MockMvc mockMvc;

  public void callRestServiceToAddInvoiceAndReturnId(Invoice invoice) throws Exception {
    mockMvc
        .perform(post(INVOICE_SERVICE_PATH)
            .content(convertToJson(invoice))
            .contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isOk());
  }

  public String convertToJson(Object object) {
    try {
      return getObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException exception) {
      exception.printStackTrace();
    }
    return "";
  }

}