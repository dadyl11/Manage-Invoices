package pl.coderstrust.accounting.database.impl.file;

import static org.junit.Assert.*;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.database.DataBaseTest;
import pl.coderstrust.accounting.database.impl.memory.InMemoryDataBase;

public class InFileDatabaseTest extends DataBaseTest {

  @Override
  public DataBase getDatabase() {
    return new InFileDatabase();
  }
}