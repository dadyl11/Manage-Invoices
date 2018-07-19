package pl.coderstrust.accounting.model;

import static org.junit.Assert.*;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

public class CompanyTest {

  @Test
  public void shouldPassAllPojoTestsForGettersAndSetters() {
    // given
    final Class<?> classUnderTest = Company.class;

    // when

    // then
    assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER).areWellImplemented();
  }

}