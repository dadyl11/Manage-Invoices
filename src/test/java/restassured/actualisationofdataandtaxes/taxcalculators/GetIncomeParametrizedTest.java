package restassured.actualisationofdataandtaxes.taxcalculators;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetIncomeParametrizedTest implements Data {

  String path = "/taxcalculator/getIncome?nip=";

  @Test
  @Parameters(method = "dataForTesting")
  public void verifyIncomeByNipNumber(String path, String expectedIncome) {
    given().when().get(path).then().body(containsString(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "96.60"},
        new Object[]{path + nipBYse, "208.20"},
        new Object[]{path + nipKRse, "208.20"},
        new Object[]{path + nipGRse, "112.80"},
        new Object[]{path + nipRAse, "112.80"},
        new Object[]{path + nipCHba, "0"},
        new Object[]{path + nipBYba, "112.80"},
        new Object[]{path + nipKRba, "96.60"},
        new Object[]{path + nipGRba, "0"},
        new Object[]{path + nipRAba, "208.20"}
    };
  }
}
