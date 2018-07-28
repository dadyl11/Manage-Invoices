package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.database.impl.memory.InMemoryDatabase;

public class InMemoryDatabaseTest extends DatabaseTest {

  @Override
  public Database getDatabase() {
    return new InMemoryDatabase();
  }
}