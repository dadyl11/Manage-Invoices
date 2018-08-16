package restassured.actualisationOfDataAndTax.invoices;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.JsonConfig.jsonConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import java.math.BigDecimal;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.coderstrust.accounting.controller.JacksonProvider;
import restassured.Data;

public class PostMethodTest implements Data {

  @BeforeClass
  public static void prepareInvoicesToTest() throws JsonProcessingException {

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceRA)).when().post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceBY)).when().post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceKR)).when().post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceGR)).when().post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceCH)).when().post(invoicesUrl);
  }

  @Test
  public void verifyIfContainsIdentifierAndNip() {

    given().when().get(invoicesUrl).then()
        .body(containsString("4/2018"))
        .body(containsString("1239514823"));
  }

  @Test
  public void verifyIfContainsNipAndSalePlace() {
    given().when().get(invoicesUrl).then()
        .body(containsString(invoiceCH.getBuyer().getNip()))
        .body(containsString(invoiceBY.getSalePlace()));
  }

  @Test
  public void verifyIdentifiers() {
    given().contentType(ContentType.JSON).when().get(invoicesUrl).then()
        .body("identifier", hasItems(invoiceKR.getIdentifier(), invoiceCH.getIdentifier(),
            invoiceGR.getIdentifier(), invoiceBY.getIdentifier(), invoiceRA.getIdentifier()));
  }

  @Test
  public void verifySalePlace() {
    given().when().get(invoicesUrl).then()
        .body("salePlace", hasItem(invoiceGR.getSalePlace()));
  }

  @Test
  public void verifySalePlaces() {
    given().when().get(invoicesUrl).then()
        .body("salePlace",
            hasItems(invoiceGR.getSalePlace(), invoiceBY.getSalePlace(), invoiceRA.getSalePlace(),
                invoiceCH.getSalePlace(), invoiceKR.getSalePlace()));
  }

  @Test
  public void verifyAllFields() {

    RestAssured.config =
        newConfig().jsonConfig(jsonConfig().numberReturnType(NumberReturnType.BIG_DECIMAL));

    int id = invoiceKR.getId();
    String identifier = invoiceKR.getIdentifier();
    System.out.println(identifier);
    String issueDate = invoiceKR.getIssueDate().toString();
    String saleDate = invoiceKR.getSaleDate().toString();
    String salePlace = invoiceKR.getSalePlace();

    String buyerName = invoiceKR.getBuyer().getName();
    String buyerNip = invoiceKR.getBuyer().getNip();
    String buyerStreet = invoiceKR.getBuyer().getStreet();
    String buyerPostalCode = invoiceKR.getBuyer().getPostalCode();
    String buyerCity = invoiceKR.getBuyer().getCity();
    BigDecimal buyerDiscount = invoiceKR.getBuyer().getDiscount();

    String sellerName = invoiceKR.getSeller().getName();
    String sellerNip = invoiceKR.getSeller().getNip();
    String sellerStreet = invoiceKR.getSeller().getStreet();
    String sellerPostalCode = invoiceKR.getSeller().getPostalCode();
    String sellerCity = invoiceKR.getSeller().getCity();
    BigDecimal sellerDiscount = invoiceKR.getSeller().getDiscount();

    String description0 = invoiceKR.getEntries().get(0).getDescription();
    String description1 = invoiceKR.getEntries().get(1).getDescription();
    String description2 = invoiceKR.getEntries().get(2).getDescription();

    BigDecimal netPrice0 = invoiceKR.getEntries().get(0).getNetPrice();
    BigDecimal netPrice1 = invoiceKR.getEntries().get(1).getNetPrice();
    BigDecimal netPrice2 = invoiceKR.getEntries().get(2).getNetPrice();

    String vatRate0 = invoiceKR.getEntries().get(0).getVatRate().toString();
    String vatRate1 = invoiceKR.getEntries().get(1).getVatRate().toString();
    String vatRate2 = invoiceKR.getEntries().get(2).getVatRate().toString();

    BigDecimal quantity0 = invoiceKR.getEntries().get(0).getQuantity();
    BigDecimal quantity1 = invoiceKR.getEntries().get(1).getQuantity();
    BigDecimal quantity2 = invoiceKR.getEntries().get(2).getQuantity();

    given().when().get(invoicesUrl).then()

        .body("[2].id", is(2))
        .body("[2].identifier", is(identifier))
        .body("[2].issueDate", is(issueDate))
        .body("[2].saleDate", is(saleDate))
        .body("[2].salePlace", is(salePlace))

        .body("[2].buyer.name", is(buyerName))
        .body("[2].buyer.nip", is(buyerNip))
        .body("[2].buyer.street", is(buyerStreet))
        .body("[2].buyer.postalCode", is(buyerPostalCode))
        .body("[2].buyer.city", is(buyerCity))
        .body("[2].buyer.discount", is(buyerDiscount))

        .body("[2].seller.name", is(sellerName))
        .body("[2].seller.nip", is(sellerNip))
        .body("[2].seller.street", is(sellerStreet))
        .body("[2].seller.postalCode", is(sellerPostalCode))
        .body("[2].seller.city", is(sellerCity))
        .body("[2].seller.discount", is(sellerDiscount))

        .body("[2].entries.size()", is(3))

        .body("[2].entries[0].description", is(description0))
        .body("[2].entries[1].description", is(description1))
        .body("[2].entries[2].description", is(description2))

        .body("[2].entries[0].netPrice", is(netPrice0))
        .body("[2].entries[1].netPrice", is(netPrice1))
        .body("[2].entries[2].netPrice", is(netPrice2))

        .body("[2].entries[0].vatRate", is(vatRate0))
        .body("[2].entries[1].vatRate", is(vatRate1))
        .body("[2].entries[2].vatRate", is(vatRate2))

        .body("[2].entries[0].quantity", is(quantity0))
        .body("[2].entries[1].quantity", is(quantity1))
        .body("[2].entries[2].quantity", is(quantity2));
  }
}



