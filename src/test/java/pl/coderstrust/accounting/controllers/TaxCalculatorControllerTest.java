package pl.coderstrust.accounting.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaxCalculatorControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TaxCalculatorController taxCalculatorController;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void contexLoads() throws Exception {
    assertThat(taxCalculatorController).isNotNull();
  }

  @Test
  public void greetingShouldReturnDefaultMessage() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
        String.class)).contains("Hello World");
  }
}