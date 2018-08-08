package pl.coderstrust.accounting.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;


@RunWith(JUnitParamsRunner.class)
@SpringBootTest
public class NipValidatorTest {

  @ClassRule
  public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

  @Rule
  public final SpringMethodRule springMethodRule = new SpringMethodRule();

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Autowired
  NipValidator nipValidator;

  @Test
  @Parameters(method = "wrongNip")
  public void shouldRejectWrongNip(String nip) {
    assertThat(nipValidator.isValid(nip), is(false));
  }

  public Object[] wrongNip() {
    return new String[]{
        "1234567890",
        "",
        "31783123k",
        "   ",
        "@#$%F",
        "000000000"
    };
  }

  @Test
  @Parameters(method = "rightNip")
  public void shouldAcceptRightNip(String nip) {
    assertThat(nipValidator.isValid(nip), is(true));
  }

  public Object[] rightNip() {
    return new String[]{
        "6780038167",
        "8722410183",
        "5240107036",
        "6770065406   ",
    };
  }

  @Test
  public void shouldThrowIllegalArgumentExceptionWhenNipIsNull() {
    //given
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Nip is null!");

    //when
    nipValidator.isValid(null);
  }

}