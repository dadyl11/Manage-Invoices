package pl.coderstrust.accounting.database.impl.file;


import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.DatabaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InFileDatabaseTest extends DatabaseTest {

  @Autowired
  private InFileDatabase inFileDatabase;

  @Override
  protected Database getDatabase() {
    return inFileDatabase;
  }

  @Before
  public void beforeMethod() throws IOException {
    File file = inFileDatabase.getFileHelper().getDataBaseFile();
    if (file.exists()) {
      inFileDatabase.clearDatabase();
    }

    File file1 = inFileDatabase.getIndexHelper().getCurrentIdFile();
    if (file1.exists()) {
      file1.delete();
    }

  }

}