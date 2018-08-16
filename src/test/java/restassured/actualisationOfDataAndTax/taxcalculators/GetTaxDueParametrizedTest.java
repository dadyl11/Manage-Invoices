package restassured.actualisationOfDataAndTax.taxcalculators;

import static com.jayway.restassured.RestAssured.given;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetTaxDueParametrizedTest implements Data {

  String path = "/taxcalculator/getTaxDue?nip=";

  @Test
  @Parameters(method = "dataForTesting")
  public void verifyTaxDueByNipNumber(String path, String expectedIncome) {
    given().when().get(path).then().body(Is.is(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "13.44000"},
        new Object[]{path + nipBYse, "19.20000"},
        new Object[]{path + nipKRse, "19.20000"},
        new Object[]{path + nipGRse, "11.59200"},
        new Object[]{path + nipRAse, "11.59200"},
        new Object[]{path + nipCHba, "0"},
        new Object[]{path + nipBYba, "11.59200"},
        new Object[]{path + nipKRba, "13.44000"},
        new Object[]{path + nipGRba, "0"},
        new Object[]{path + nipRAba, "19.20000"}
    };
  }
}
