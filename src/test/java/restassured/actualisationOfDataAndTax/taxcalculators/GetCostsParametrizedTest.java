package restassured.actualisationOfDataAndTax.taxcalculators;

import static com.jayway.restassured.RestAssured.given;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import restassured.Data;

@RunWith(JUnitParamsRunner.class)
public class GetCostsParametrizedTest implements Data {

  private String path = "/taxcalculator/getCosts?nip=";

  @Test
  @Parameters(method = "dataForTesting")

  public void verifyCostsByNipNumber(String path, String expectedIncome) {

    given().when().get(path).then().body(Is.is(expectedIncome));
  }

  private Object[] dataForTesting() {
    return new Object[]{
        new Object[]{path + nipCHse, "138.000"},
        new Object[]{path + nipBYse, "62.400"},
        new Object[]{path + nipKRse, "62.400"},
        new Object[]{path + nipGRse, "70.200"},
        new Object[]{path + nipRAse, "70.200"},
        new Object[]{path + nipCHba, "147.000"},
        new Object[]{path + nipBYba, "70.200"},
        new Object[]{path + nipKRba, "138.000"},
        new Object[]{path + nipGRba, "147.000"},
        new Object[]{path + nipRAba, "62.400"}
    };
  }
}
