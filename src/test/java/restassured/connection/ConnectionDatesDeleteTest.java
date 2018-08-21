package restassured.connection;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import restassured.Data;

public class ConnectionDatesDeleteTest implements Data {

  @Test
  public void whenRequestGetInvoicesThen200() {
    given().when().get(invoicesUrl).then().statusCode(200);
  }

  @Test
  public void whenRequestGetSimpleInvoiceWithCorrectId_Then200() {
    given().pathParam("id", 0).when().get(invoicesUrl+"/{id}").then()
        .statusCode(200);
  }

  @Test
  public void whenRequestGetSimpleInvoiceWithBadId_Then404() {
    given().pathParam("id", 100).when().get(invoicesUrl+"/{id}").then()
        .statusCode(404);
  }

  @Test
  public void whenRequestTaxcalculatorGetIncome_Then200() {
    given().when().get(taxCalculatorUrl+"/Income/"+nipKRba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Income/"+nipGRse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Income/"+nipBYba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Income/"+nipCHba).then().statusCode(200);
  }

  @Test
  public void whenRequestTaxcalculatorTaxDue_Then200() {
    given().when().get(taxCalculatorUrl+"/TaxDue/"+nipBYse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/TaxDue/"+nipGRba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/TaxDue/"+nipBYse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/TaxDue/"+nipCHse).then().statusCode(200);
  }


  @Test
  public void whenRequestTaxcalculatorCosts_Then200() {
    given().when().get(taxCalculatorUrl+"/Costs/"+nipCHba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Costs/"+nipCHse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Costs/"+nipBYba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Costs/"+nipKRba).then().statusCode(200);
  }

  @Test
  public void whenRequestTaxcalculatorVatPayable_Then200() {
    given().when().get(taxCalculatorUrl+"/VatPayable/"+nipGRba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/VatPayable/"+nipKRba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/VatPayable/"+nipCHse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/VatPayable/"+nipGRse).then().statusCode(200);
  }

  @Test
  public void whenRequestTaxcalculatorGetprofit_Then200() {
    given().when().get(taxCalculatorUrl+"/Profit/"+nipRAba).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Profit/"+nipRAse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Profit/"+nipBYse).then().statusCode(200);
    given().when().get(taxCalculatorUrl+"/Profit/"+nipKRba).then().statusCode(200);
  }

  @Test
  public void whenRequestPutInvoiceWithCorrectID_Then200() {
    given().contentType(ContentType.JSON).body(invoiceKrakow).pathParam("id", 2)
        .when().put(invoicesUrl+"/{id}")
        .then().statusCode(200);
  }

  @Test
  public void whenRequestDeleteInvoiceCorrectID_Then200() {
    given().pathParam("id", 0).when().delete(invoicesUrl+"/{id}")
        .then().statusCode(200);
  }

  @Test
  public void whenRequestDeleteInvoiceWithBadID_Then404() {
    given().pathParam("id", 100).when().delete(invoicesUrl+"/{id}")
        .then().statusCode(404);
  }

  public static String path = invoicesUrl+"/dates?startDate=2018-05-06&endDate=2018-08-09";

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
        .get(path).then().body("salePlace", not(hasItems("Chelmno")));
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