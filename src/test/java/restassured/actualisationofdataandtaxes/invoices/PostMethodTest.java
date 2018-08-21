package restassured.actualisationofdataandtaxes.invoices;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.coderstrust.accounting.configuration.JacksonProvider;
import restassured.Data;

public class PostMethodTest implements Data {

  @BeforeClass
  public static void prepareInvoicesToTest() throws JsonProcessingException {

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceRadomsko)).post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceBydgoszcz)).post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceKrakow)).post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceGudziadz)).post(invoicesUrl);

    given().contentType(ContentType.JSON)
        .body(JacksonProvider.getObjectMapper().writeValueAsString(
            invoiceChelmno)).post(invoicesUrl);
  }

  @Test
  public void verifyIfContainsIdentifierAndNip() {

    given().when().get(invoicesUrl).then()
        .body(containsString("4/2018"))
        .body(containsString("8421622720"));
  }

  @Test
  public void verifyIfContainsNipAndSalePlace() {
    given().when().get(invoicesUrl).then()
        .body(containsString(invoiceChelmno.getBuyer().getNip()))
        .body(containsString(invoiceBydgoszcz.getSalePlace()));
  }

  @Test
  public void verifyIdentifiers() {
    given().contentType(ContentType.JSON).when().get(invoicesUrl).then()
        .body("identifier", hasItems(invoiceKrakow.getIdentifier(), invoiceChelmno.getIdentifier(),
            invoiceGudziadz.getIdentifier(), invoiceBydgoszcz.getIdentifier(),
            invoiceRadomsko.getIdentifier()));
  }

  @Test
  public void verifySalePlace() {
    given().when().get(invoicesUrl).then()
        .body("salePlace", hasItem(invoiceGudziadz.getSalePlace()));
  }

  @Test
  public void verifySalePlaces() {
    given().when().get(invoicesUrl).then()
        .body("salePlace",
            hasItems(invoiceGudziadz.getSalePlace(), invoiceBydgoszcz.getSalePlace(),
                invoiceRadomsko.getSalePlace(), invoiceChelmno.getSalePlace(),
                invoiceKrakow.getSalePlace()));
  }

  @Test
  public void verifyAllFields() {

    int id = invoiceKrakow.getId();
    String identifier = invoiceKrakow.getIdentifier();
    String issueDate = invoiceKrakow.getIssueDate().toString();
    String saleDate = invoiceKrakow.getSaleDate().toString();
    String salePlace = invoiceKrakow.getSalePlace();

    String buyerName = invoiceKrakow.getBuyer().getName();
    String buyerNip = invoiceKrakow.getBuyer().getNip();
    String buyerStreet = invoiceKrakow.getBuyer().getStreet();
    String buyerPostalCode = invoiceKrakow.getBuyer().getPostalCode();
    String buyerCity = invoiceKrakow.getBuyer().getCity();
    String buyerDiscount = invoiceKrakow.getBuyer().getDiscount().toString();

    String sellerName = invoiceKrakow.getSeller().getName();
    String sellerNip = invoiceKrakow.getSeller().getNip();
    String sellerStreet = invoiceKrakow.getSeller().getStreet();
    String sellerPostalCode = invoiceKrakow.getSeller().getPostalCode();
    String sellerCity = invoiceKrakow.getSeller().getCity();
    String sellerDiscount = invoiceKrakow.getSeller().getDiscount().toString();

    String description0 = invoiceKrakow.getEntries().get(0).getDescription();
    String description1 = invoiceKrakow.getEntries().get(1).getDescription();
    String description2 = invoiceKrakow.getEntries().get(2).getDescription();

    String netPrice0 = invoiceKrakow.getEntries().get(0).getNetPrice().toString();
    String netPrice1 = invoiceKrakow.getEntries().get(1).getNetPrice().toString();
    String netPrice2 = invoiceKrakow.getEntries().get(2).getNetPrice().toString();

    String vatRate0 = invoiceKrakow.getEntries().get(0).getVatRate().toString();
    String vatRate1 = invoiceKrakow.getEntries().get(1).getVatRate().toString();
    String vatRate2 = invoiceKrakow.getEntries().get(2).getVatRate().toString();

    String quantity0 = invoiceKrakow.getEntries().get(0).getQuantity().toString();
    String quantity1 = invoiceKrakow.getEntries().get(1).getQuantity().toString();
    String quantity2 = invoiceKrakow.getEntries().get(2).getQuantity().toString();

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



