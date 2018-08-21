package restassured.actualisationofdataandtaxes.taxcalculators;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetTaxIncludedParametrizedTest implements Data {

  String path = taxCalculatorUrl+"/TaxIncluded/";

  @Test
  @Parameters(method = "dataForTesting")
  public void verifyTaxIncludedByNipNumber(String path, String expectedIncome) {
    given().when().get(path).then().body(containsString(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "19.20"},
        new Object[]{path + nipBYse, "0"},
        new Object[]{path + nipKRse, "0"},
        new Object[]{path + nipGRse, "0"},
        new Object[]{path + nipRAse, "0"},
        new Object[]{path + nipCHba, "25.03"},
        new Object[]{path + nipBYba, "0"},
        new Object[]{path + nipKRba, "19.20"},
        new Object[]{path + nipGRba, "25.03"},
        new Object[]{path + nipRAba, "0"}
    };
  }
}
