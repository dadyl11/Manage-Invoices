package pl.coderstrust.accounting;

import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataBaseTest {

  @Before
  public void setUp() {
    Map<Integer, Invoice> map = new HashMap<>();
  }

  @Test
  public void Save() {
  }

  @Test
  public void getInvoices() {
  }

  @Test
  public void update() {
  }

  @Test
  public void removeInvoiceById() {
  }
}