package restassured.actualisationofdataandtaxes.taxcalculators;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetCostsParametrizedTest implements Data {

  private String path = "/taxcalculator/getCosts?nip=";

  @Test
  @Parameters(method = "dataForTesting")

  public void verifyCostsByNipNumber(String path, String expectedIncome) {

    given().when().get(path).then().body(containsString(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "138.00"},
        new Object[]{path + nipBYse, "62.40"},
        new Object[]{path + nipKRse, "62.40"},
        new Object[]{path + nipGRse, "70.20"},
        new Object[]{path + nipRAse, "70.20"},
        new Object[]{path + nipCHba, "147.00"},
        new Object[]{path + nipBYba, "70.20"},
        new Object[]{path + nipKRba, "138.00"},
        new Object[]{path + nipGRba, "147.00"},
        new Object[]{path + nipRAba, "62.40"}
    };
  }
}
