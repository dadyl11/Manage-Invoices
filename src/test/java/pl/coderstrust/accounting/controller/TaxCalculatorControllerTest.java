package pl.coderstrust.accounting.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUKPOL;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_DRUTEX;
import static pl.coderstrust.accounting.helpers.CompanyProvider.COMPANY_WASBUD;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import pl.coderstrust.accounting.helpers.RestHelper;
import pl.coderstrust.accounting.helpers.TestBaseWithMockMvc;
import pl.coderstrust.accounting.logic.InvoiceService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class TaxCalculatorControllerTest extends TestBaseWithMockMvc {

  private static final String TAX_CALCULATOR_SERVICE_PATH = "/taxcalculator";

  @Autowired
  private InvoiceService invoiceService;

  @Autowired
  private TaxCalculatorController taxCalculatorController;

  private RestHelper restHelper = new RestHelper();

  @Before
  public void beforeMethod() {
    invoiceService.clearDatabase();
  }

  @Test
  public void contexLoads() {
    assertThat(taxCalculatorController, is(notNullValue()));
  }

  @Test
  public void shouldGetIncome() throws Exception {
    //given
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);

    //when

    //then
    MvcResult result = mockMvc
        .perform(get(TAX_CALCULATOR_SERVICE_PATH + "/income/" + COMPANY_DRUTEX.getNip()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(138.0)))
        .andReturn();
  }

  @Test
  public void shouldGetTaxDue() throws Exception {
    //given
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_KRAKOW_2018);
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    //when

    //then
    MvcResult result = mockMvc
        .perform(get(TAX_CALCULATOR_SERVICE_PATH + "/TaxDue/" + COMPANY_DRUTEX.getNip()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(22.008)))
        .andReturn();
  }

  @Test
  public void shouldGetTaxIncluded() throws Exception {
    //given
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    //when

    //then
    MvcResult result = mockMvc
        .perform(get(TAX_CALCULATOR_SERVICE_PATH + "/TaxIncluded/" + COMPANY_DRUKPOL.getNip()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(11.592)))
        .andReturn();
  }

  @Test
  public void shouldGetCosts() throws Exception {
    //given
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    //when

    //then
    MvcResult result = mockMvc
        .perform(get(TAX_CALCULATOR_SERVICE_PATH + "/Costs/" + COMPANY_WASBUD.getNip()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(70.2)))
        .andReturn();
  }

  @Test
  public void shouldGetProfit() throws Exception {
    //given
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    //when

    //then
    MvcResult result = mockMvc
        .perform(get(TAX_CALCULATOR_SERVICE_PATH + "/Profit/" + COMPANY_WASBUD.getNip()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(-19.8)))
        .andReturn();
  }

  @Test
  public void shouldGetVatPayable() throws Exception {
    //given
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_GRUDZIADZ_2017);
    restHelper.callRestServiceToAddInvoiceAndReturnId(INVOICE_BYDGOSZCZ_2018);

    //when

    //then
    MvcResult result = mockMvc
        .perform(get(TAX_CALCULATOR_SERVICE_PATH + "/VatPayable/" + COMPANY_WASBUD.getNip()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(8.784)))
        .andReturn();
  }
}