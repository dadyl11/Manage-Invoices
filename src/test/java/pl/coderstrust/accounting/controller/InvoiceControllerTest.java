package pl.coderstrust.accounting.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_BUYER_CITY;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BLANK_IDENTIFIER;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_RADOMSKO_2018;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderstrust.accounting.helpers.InvoiceAssertion;
import pl.coderstrust.accounting.helpers.RestHelper;
import pl.coderstrust.accounting.logic.InvoiceService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvoiceControllerTest {

  private static final String INVOICE_SERVICE_PATH = "/invoices";
  private static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private InvoiceController invoiceController;

  @Autowired
  private InvoiceService invoiceService;

  @Autowired
  private RestHelper restHelper;

  @Autowired
  private InvoiceAssertion invoiceAssertion;

  @Before
  public void beforeMethod() {
    invoiceService.clearDatabase();
  }

  @Test
  public void contexLoads() {
    assertThat(invoiceController, is(notNullValue()));
  }

  @Test
  public void shouldCheckSaveInvoiceRequest() throws Exception {
    // TODO if not needed why do you add?
    int idResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    invoiceAssertion.assertSingleInvoice(idResponse, INVOICE_BYDGOSZCZ_2018);
  }

  @Test
  public void shouldReturnErrorMessageCorrespondingToIncorrectInvoiceField() throws Exception {
    mockMvc.perform(
        post(INVOICE_SERVICE_PATH)
            .content(restHelper.convertToJson(INVOICE_BLANK_BUYER_CITY))
            .contentType(JSON_CONTENT_TYPE)
    )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]", is("Buyer city not found")));
  }

  @Test
  public void getInvoices() throws Exception {
    int firstResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018); // TODO if not needed why do you add?
    int secondResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_CHELMNO_2016); // TODO if not needed why do you add?
    int thirdResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3))); // TODO add assertions to all 3 :) HINT - you can use helper method with parameter

    invoiceAssertion.assertInvoiceFromList(firstResponse, INVOICE_KRAKOW_2018, "0");
    invoiceAssertion.assertInvoiceFromList(secondResponse, INVOICE_CHELMNO_2016, "1");
    invoiceAssertion.assertInvoiceFromList(secondResponse, INVOICE_BYDGOSZCZ_2018, "2");
  }

  @Test
  public void getInvoicesByIssueDateRange() throws Exception {
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018); // TODO if not needed why do you assign to varialble?
    int idResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_CHELMNO_2016);
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018); // TODO if not needed why do you assign to varialble?

    LocalDate startDate = LocalDate.of(2015, 4, 12);
    LocalDate endDate = LocalDate.of(2017, 4, 12);

    String url = String
        .format("/dates?startDate=%1$s&endDate=%2$s", startDate, endDate); // TODO what is "$s" doing? - without this symbols it doesn't work

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1))); // TODO those assertions are duplicates in each test -
    // write helper method taking id and Invoice to assert
    invoiceAssertion.assertSingleInvoice(idResponse, INVOICE_CHELMNO_2016);
  }

  @Test
  public void getSingleInvoice() throws Exception {
    int idResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);

    invoiceAssertion.assertSingleInvoice(idResponse, INVOICE_KRAKOW_2018);

  }

  @Test
  public void shouldReturnErrorCausedByNotExistingId() throws Exception {
    mockMvc
        .perform(get(INVOICE_SERVICE_PATH + "/0"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void updateInvoice() throws Exception {
    int idResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_RADOMSKO_2018);

    mockMvc
        .perform(put(INVOICE_SERVICE_PATH + "/" + idResponse)
            .contentType(JSON_CONTENT_TYPE)
            .content(restHelper.convertToJson(INVOICE_BYDGOSZCZ_2018)))
        .andExpect(status().isOk());

    invoiceAssertion.assertSingleInvoice(idResponse, INVOICE_BYDGOSZCZ_2018);

  }

  @Test
  public void shouldReturnErrorCausedByNotExistingIdPassedToUpdate() throws Exception {
    mockMvc
        .perform(put(INVOICE_SERVICE_PATH + "/0")
            .content(restHelper.convertToJson(INVOICE_CHELMNO_2016))
            .contentType(JSON_CONTENT_TYPE))
        .andExpect(status().isNotFound());
  } // TODO - no such test for delete? :)

  @Test // TODO test name says that error is because update method is not valid - is that true? :)
  public void shouldReturnErrorCausedByNotValidInvoiceIdentifier() throws Exception {
    int invoiceId = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);

    mockMvc
        .perform(put(INVOICE_SERVICE_PATH + "/" + invoiceId)
            .contentType(JSON_CONTENT_TYPE)
            .content(restHelper.convertToJson(INVOICE_BLANK_IDENTIFIER)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]", is("Identifier not found")));
  }

  @Test
  public void removeInvoiceById() throws Exception {
    int firstResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    int secondResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_CHELMNO_2016); // TODO why do you assign to variable if not used?
    int thirdResponse = restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018); // TODO why do you assign to variable if not used?

    mockMvc
        .perform(delete(INVOICE_SERVICE_PATH + "/" + firstResponse))
        .andExpect(status().isOk());

    mockMvc
        .perform(get(INVOICE_SERVICE_PATH))
        .andExpect(jsonPath("$", hasSize(2)));

    invoiceAssertion.assertSingleInvoice(secondResponse, INVOICE_CHELMNO_2016);
    invoiceAssertion.assertSingleInvoice(thirdResponse, INVOICE_BYDGOSZCZ_2018);
  }

  @Test
  public void shouldReturnErrorCausedByNotExistingIdPassedToDeleteRequest() throws Exception {
    mockMvc
        .perform(delete(INVOICE_SERVICE_PATH + "/0"))
        .andExpect(status().isNotFound());
  }
}