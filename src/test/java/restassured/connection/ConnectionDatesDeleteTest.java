package restassured.connection;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

import com.jayway.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import restassured.Data;

public class ConnectionDatesDeleteTest implements Data {

  @Test
  public void whenRequestGetInvoicesThen200() {
    given().when().get("http://localhost:8080/invoices/").then().statusCode(200);
  }

  @Test
  public void whenRequestGetSimpleInvoiceWithCorrectId_Then200() {
    given().pathParam("id", 4).when().get("http://localhost:8080/invoices/{id}").then()
        .statusCode(200);
  }

  @Test
  public void whenRequestGetSimpleInvoiceWithBadId_Then404() {
    given().pathParam("id", 100).when().get("http://localhost:8080/invoices/{id}").then()
        .statusCode(404);
  }

  @Test
  public void whenRequestGetTaxcalculatorGetIncome_Then200() {
    given().when().get("http://localhost:8080/taxcalculator/getIncome?nip=").then().statusCode(200);
  }

  @Test
  public void whenRequestGetaxcalculator_getTaxDueThen200() {
    given().when().get("http://localhost:8080/taxcalculator/getTaxDue?nip=").then().statusCode(200);
  }

  @Test
  public void whenRequestGetTaxcalculatorGetCosts_Then200() {
    given().when().get("http://localhost:8080/taxcalculator/getCosts?nip=").then().statusCode(200);
  }

  @Test
  public void whenRequestGetTaxcalculatorGetVatPayable_Then200() {
    given().when().get("http://localhost:8080/taxcalculator/getVatPayable?nip=").then()
        .statusCode(200);
  }

  @Test
  public void whenRequestGetTaxcalculatorGetprofit_Then200() {
    given().when().get("http://localhost:8080/taxcalculator/getProfit?nip=").then().statusCode(200);
  }

  @Test
  public void whenRequestPutInvoiceWithCorrectID_Then200() {
    given().contentType(ContentType.JSON).body(invoiceKrakow).pathParam("id", 2)
        .when().put("http://localhost:8080/invoices/{id}")
        .then().statusCode(200);
  }

  @Test
  public void whenRequestDeleteInvoiceCorrectID_Then200() {
    given().pathParam("id", 4).when().delete("http://localhost:8080/invoices/{id}")
        .then().statusCode(200);
  }

  @Test
  public void whenRequestDeleteInvoiceWithBadID_Then404() {
    given().pathParam("id", 100).when().delete("http://localhost:8080/invoices/{id}")
        .then().statusCode(404);
  }

  public static String path = "http://localhost:8080/invoices/dates?startDate=2018-05-06&endDate=2018-08-09";

  @Test
  public void getInvoicesByIssueDateRange() {
    given().when()
        .get(path).then().statusCode(200);
  }

  @Test
  public void getInvoicesByIssueDateRange_Size() {
    given().when()
        .get(path).then().body("$.size()", is(3));
  }

  @Test
  public void getInvoicesByIssueDateRange_hasItems() {
    given().when()
        .get(path).then().body("salePlace", hasItems("Radomsko", "Bydgoszcz", "Krakow"));
  }

  @Test
  public void getInvoicesByIssueDateRange_notHasItem() {
    given().when()
        .get(path).then().body("salePlace", CoreMatchers.not(hasItems("Chelmno")));
  }

  @Test
  public void verifyIdentifierOfFirstInvoice() {
    given().when()
        .get(path).then().body("[0].identifier", is("5/2018"));
  }

  @Test
  public void verifyIdentifierOfSecondInvoice() {
    given().when()
        .get(path).then().body("[1].identifier", is("4/2018"));
  }

  @Test
  public void verifyIdentifierOfThirdInvoice() {
    given().when()
        .get(path).then().body("[2].identifier", is("1/2018"));
  }
}