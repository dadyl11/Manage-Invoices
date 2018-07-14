package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.database.impl.memory.InMemoryDataBase;

public class InMemoryDataBaseForInvoiceBookTest extends InvoiceBookTest {

  @Override
  public DataBase getDatabase() {
    return new InMemoryDataBase();
  }
}
