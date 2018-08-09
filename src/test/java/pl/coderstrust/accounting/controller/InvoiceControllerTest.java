package pl.coderstrust.accounting.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.controller.JacksonProvider.getObjectMapper;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_CITY;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_IDENTIFIER;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_RADOMSKO_2018;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDate;
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
import pl.coderstrust.accounting.model.Invoice;


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
  }

  @Test
  public void shouldCheckSaveInvoiceRequest() throws Exception {
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    int secondPostResponse = callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);

    String savedInvoice = mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/" + secondPostResponse))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    assertThat(savedInvoice, is(convertToJson(INVOICE_KRAKOW_2018)));

  }

  @Test
  public void shouldReturnErrorCausedByIncompletedInvoiceFie() throws Exception {
    mockMvc.perform(
        post(INVOICE_SERVICE_PATH)
            .content(convertToJson(INVOICE_BLANK_BUYER_CITY))
            .contentType(JSON_CONTENT_TYPE)
    )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]",
            is("Buyer city not found")));
  }

  @Test
  public void getInvoices() throws Exception {
    callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_CHELMNO_2016);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[2].id", is(2)));
  }

  @Test
  public void getInvoicesByIssueDateRange() throws Exception {
    callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_CHELMNO_2016);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    LocalDate startDate = LocalDate.of(2015, 04, 12);
    LocalDate endDate = LocalDate.of(2017, 04, 12);

    mockMvc
        .perform(
            get(INVOICE_SERVICE_PATH + "/dates?startDate=" + startDate + "&endDate=" + endDate))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].issueDate", is("2016-03-05")));
  }

  @Test
  public void getSingleInvoice() throws Exception {
    callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/0"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(0)));

  }

  @Test
  public void shouldReturnErrorCausedByNotExistingId() throws Exception {
    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/0"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void updateInvoice() throws Exception {
    int invoiceId = callRestServiceToAddInvoiceAndReturnId(INVOICE_RADOMSKO_2018);
    mockMvc
        .perform(put(INVOICE_SERVICE_PATH + "/" + invoiceId)
            .contentType(JSON_CONTENT_TYPE)
            .content(convertToJson(INVOICE_BYDGOSZCZ_2018)))
        .andDo(print())
        .andExpect(status().isOk());

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/" + invoiceId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_CONTENT_TYPE))
        .andExpect(jsonPath("$.id", is(0)))
        .andExpect(jsonPath("$.identifier", is("4/2018")));
    //.andExpect(jsonPath("$.buyer", is(equalTo(convertToJson(COMPANY_WASBUD)))));
  }

  @Test
  public void shouldReturnErrorCausedByNotExistingIdToUpdate() throws Exception {
    mockMvc
        .perform(put(INVOICE_SERVICE_PATH + "/0")
            .content(convertToJson(INVOICE_CHELMNO_2016))
            .contentType(JSON_CONTENT_TYPE))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnErrorCausedByNotValidInvoiceUpdateMethod() throws Exception {

    int invoiceId = callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);

    mockMvc
        .perform(put(INVOICE_SERVICE_PATH + "/" + invoiceId)
            .contentType(JSON_CONTENT_TYPE)
            .content(convertToJson(INVOICE_BLANK_IDENTIFIER)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]",
            is("Identifier not found")));
  }

  @Test
  public void removeInvoiceById() throws Exception {
    callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_CHELMNO_2016);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    mockMvc
        .perform(delete(INVOICE_SERVICE_PATH + "/1"))
        .andDo(print())
        .andExpect(status().isOk());

    mockMvc
        .perform(delete(INVOICE_SERVICE_PATH + "/5"))
        .andDo(print())
        .andExpect(status().isNotFound());

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andExpect(jsonPath("$", hasSize(2)));
  }

  private int callRestServiceToAddInvoiceAndReturnId(Invoice invoice) throws Exception {
    String response =
        mockMvc
            .perform(post(INVOICE_SERVICE_PATH)
                .content(convertToJson(invoice))
                .contentType(JSON_CONTENT_TYPE))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse().getContentAsString();
    return Integer.valueOf(response);
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