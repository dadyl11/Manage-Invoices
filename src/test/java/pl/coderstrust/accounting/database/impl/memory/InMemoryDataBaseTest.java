package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.database.DataBaseTest;

public class InMemoryDataBaseTest extends DataBaseTest {

  @Override
  public DataBase getDatabase() {
    return new InMemoryDataBase();
  }
}