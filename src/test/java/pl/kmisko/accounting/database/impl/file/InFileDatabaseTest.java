package pl.kmisko.accounting.database.impl.file;


import org.junit.Before;
import pl.kmisko.accounting.configuration.JacksonProvider;
import pl.kmisko.accounting.database.Database;
import pl.kmisko.accounting.database.impl.DatabaseTest;
import pl.kmisko.accounting.database.impl.file.helpers.FileHelper;
import pl.kmisko.accounting.database.impl.file.helpers.IndexHelper;
import pl.kmisko.accounting.database.impl.file.helpers.InvoiceConverter;


public class InFileDatabaseTest extends DatabaseTest {

  private FileHelper fileHelper = new FileHelper("invoicesTest.json");
  private IndexHelper indexHelper = new IndexHelper("currentIdFileTest.txt");

  private InvoiceConverter invoiceConverter = new InvoiceConverter(JacksonProvider.getObjectMapper());
  private InFileDatabase inFileDatabase = new InFileDatabase(fileHelper, invoiceConverter, indexHelper);

  @Override
  protected Database getDatabase() {
    return inFileDatabase;
  }

  @Before
  public void beforeMethod() {
    inFileDatabase.clearDatabase();
  }
}