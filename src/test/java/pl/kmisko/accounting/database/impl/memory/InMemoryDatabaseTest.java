package pl.kmisko.accounting.database.impl.memory;

import pl.kmisko.accounting.database.Database;
import pl.kmisko.accounting.database.impl.DatabaseTest;

public class InMemoryDatabaseTest extends DatabaseTest {

  @Override
  public Database getDatabase() {
    return new InMemoryDatabase();
  }
}