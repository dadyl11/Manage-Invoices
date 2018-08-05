package pl.coderstrust.accounting.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.controller.JacksonProvider.getObjectMapper;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class InvoiceControllerTest {

  private static final String INVOICE_SERVICE_PATH = "/invoices";
  private static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private InvoiceController invoiceController;

  @Test
  public void contextLoads() throws Exception {
    assertNotNull(invoiceController);
    //assertThat(invoiceController).isNotNull();
  }

  @Test
  public void shouldCheckSaveInvoiceRequest() throws Exception {
    String postResponse = mockMvc.perform(
        post(INVOICE_SERVICE_PATH)
            .content(convertToJson(INVOICE_KRAKOW_2018))
            .contentType(JSON_CONTENT_TYPE)
    )
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(Integer.valueOf(postResponse))))
        .andExpect(jsonPath("$[0].identifier", is("1/2018")));

  }

  @Test
  public void getInvoices() {
  }

  @Test
  public void getInvoicesByIssueDateRange() {
  }

  @Test
  public void getSingleInvoice() throws Exception {
    String postResponse = mockMvc.perform(
        post(INVOICE_SERVICE_PATH)
            .content(convertToJson(INVOICE_KRAKOW_2018))
            .contentType(JSON_CONTENT_TYPE)
    )
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/0"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void updateInvoice() {
  }

  @Test
  public void removeInvoiceById() {
  }

  private String convertToJson(Object object) {
    try {
      return getObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}