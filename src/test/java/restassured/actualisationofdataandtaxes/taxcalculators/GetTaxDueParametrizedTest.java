package restassured.actualisationofdataandtaxes.taxcalculators;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetTaxDueParametrizedTest implements Data {

  String path = "/taxcalculator/getTaxDue?nip=";

  @Test
  @Parameters(method = "dataForTesting")
  public void verifyTaxDueByNipNumber(String path, String expectedIncome) {
    given().when().get(path).then().body(containsString(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "13.44"},
        new Object[]{path + nipBYse, "19.20"},
        new Object[]{path + nipKRse, "19.20"},
        new Object[]{path + nipGRse, "11.59"},
        new Object[]{path + nipRAse, "11.59"},
        new Object[]{path + nipCHba, "0"},
        new Object[]{path + nipBYba, "11.59"},
        new Object[]{path + nipKRba, "13.44"},
        new Object[]{path + nipGRba, "0"},
        new Object[]{path + nipRAba, "19.20"}
    };
  }
}
