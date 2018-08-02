package pl.coderstrust.accounting.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.controller.JacksonProvider.getObjectMapper;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUKPOL;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUTEX;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_WASBUD;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
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
import org.springframework.test.web.servlet.MvcResult;
import pl.coderstrust.accounting.model.Invoice;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TaxCalculatorControllerTest {

  private static final String TAXCALCULATOR_SERVICE_PATH = "/taxcalculator";
  private static final String INVOICE_SERVICE_PATH = "/invoices";
  private static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_UTF8;

  @Before
  public void before() {

  }

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TaxCalculatorController taxCalculatorController;

  @Test
  public void contexLoads() throws Exception {
    assertThat(taxCalculatorController, is(notNullValue()));
  }

  @Test
  public void shouldSetCompany() throws Exception {
    this.mockMvc.perform(post(TAXCALCULATOR_SERVICE_PATH + "/setCompany")
        .contentType(JSON_CONTENT_TYPE)
        .content(convertToJson(COMPANY_DRUTEX)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldGetIncome() throws Exception {
    //given
    callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    taxCalculatorController.setCompany(COMPANY_DRUTEX);

    //when
    MvcResult result = mockMvc
        .perform(get(TAXCALCULATOR_SERVICE_PATH + "/getIncome"))
        .andExpect(status().isOk())
        .andReturn();
    BigDecimal resultInt = BigDecimal
        .valueOf(Double.parseDouble(result.getResponse().getContentAsString()));

    //then
    assertThat(resultInt, is(BigDecimal.valueOf(138.0)));
  }

  @Test
  public void shouldGetTaxDue() throws Exception {
    //given
    callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    taxCalculatorController.setCompany(COMPANY_DRUTEX);

    //when
    MvcResult result = mockMvc
        .perform(get(TAXCALCULATOR_SERVICE_PATH + "/getTaxDue"))
        .andExpect(status().isOk())
        .andReturn();
    BigDecimal resultInt = BigDecimal
        .valueOf(Double.parseDouble(result.getResponse().getContentAsString()));

    // then
    assertThat(resultInt, is(BigDecimal.valueOf(19.2)));
  }

  @Test
  public void shouldGetTaxIncluded() throws Exception {
    //given
    callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    taxCalculatorController.setCompany(COMPANY_DRUKPOL);

    //when
    MvcResult result = mockMvc
        .perform(get(TAXCALCULATOR_SERVICE_PATH + "/getTaxIncluded"))
        .andExpect(status().isOk())
        .andReturn();
    BigDecimal resultInt = BigDecimal
        .valueOf(Double.parseDouble(result.getResponse().getContentAsString()));

    // then
    assertThat(resultInt, is(BigDecimal.valueOf(11.592)));
  }

  @Test
  public void shouldGetCosts() throws Exception {
    //given
    callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    taxCalculatorController.setCompany(COMPANY_WASBUD);

    //when
    MvcResult result = mockMvc
        .perform(get(TAXCALCULATOR_SERVICE_PATH + "/getCosts"))
        .andExpect(status().isOk())
        .andReturn();
    BigDecimal resultInt = BigDecimal
        .valueOf(Double.parseDouble(result.getResponse().getContentAsString()));

    // then
    assertThat(resultInt, is(BigDecimal.valueOf(70.2)));
  }

  @Test
  public void shouldGetProfit() throws Exception {
    //given
    callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    taxCalculatorController.setCompany(COMPANY_WASBUD);

    //when
    MvcResult result = mockMvc
        .perform(get(TAXCALCULATOR_SERVICE_PATH + "/getProfit"))
        .andExpect(status().isOk())
        .andReturn();
    BigDecimal resultInt = BigDecimal
        .valueOf(Double.parseDouble(result.getResponse().getContentAsString()));

    // then
    assertThat(resultInt, is(BigDecimal.valueOf(-19.8)));
  }

  @Test
  public void shouldGetVatPayable() throws Exception {
    //given
    callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);
    taxCalculatorController.setCompany(COMPANY_WASBUD);

    //when
    MvcResult result = mockMvc
        .perform(get(TAXCALCULATOR_SERVICE_PATH + "/getVatPayable"))
        .andExpect(status().isOk())
        .andReturn();
    BigDecimal resultInt = BigDecimal
        .valueOf(Double.parseDouble(result.getResponse().getContentAsString()));

    // then
    assertThat(resultInt, is(BigDecimal.valueOf(11.592)));
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
    return Integer.parseInt(response);
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