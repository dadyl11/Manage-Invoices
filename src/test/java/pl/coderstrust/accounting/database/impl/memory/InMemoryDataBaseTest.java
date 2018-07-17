package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DataBaseTest;

public class InMemoryDataBaseTest extends DataBaseTest {

  @Override
  public Database getDatabase() {
    return new InMemoryDatabase();
  }
}