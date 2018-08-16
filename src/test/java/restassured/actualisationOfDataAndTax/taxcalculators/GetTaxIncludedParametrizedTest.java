package restassured.actualisationOfDataAndTax.taxcalculators;

import static com.jayway.restassured.RestAssured.given;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetTaxIncludedParametrizedTest implements Data {

  String path = "/taxcalculator/getTaxIncluded?nip=";

  @Test
  @Parameters(method = "dataForTesting")
  public void verifyTaxIncludedByNipNumber(String path, String expectedIncome) {
    given().when().get(path).then().body(Is.is(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "19.20000"},
        new Object[]{path + nipBYse, "0.000"},
        new Object[]{path + nipKRse, "0.000"},
        new Object[]{path + nipGRse, "0.000"},
        new Object[]{path + nipRAse, "0.000"},
        new Object[]{path + nipCHba, "25.03200"},
        new Object[]{path + nipBYba, "0.000"},
        new Object[]{path + nipKRba, "19.20000"},
        new Object[]{path + nipGRba, "25.03200"},
        new Object[]{path + nipRAba, "0.000"}
    };
  }
}
