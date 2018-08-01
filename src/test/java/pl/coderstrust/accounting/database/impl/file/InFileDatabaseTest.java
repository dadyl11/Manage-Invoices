package pl.coderstrust.accounting.database.impl.file;

import org.junit.Before;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.DatabaseTest;
import pl.coderstrust.accounting.database.impl.file.helpers.FileHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;

import java.io.File;

public class InFileDatabaseTest extends DatabaseTest {

  private FileHelper fileHelper;
  private InvoiceConverter invoiceConverter;
  private IndexHelper indexHelper;

  @Override
  protected Database getDatabase() {
    fileHelper = new FileHelper();
    invoiceConverter = new InvoiceConverter();
    indexHelper = new IndexHelper();
    return new InFileDatabase(fileHelper, invoiceConverter, indexHelper);
  }

  @Before
  public void beforeMethod() throws Exception {
    File file = new File("invoices.json");
    if (file.exists()) {
      file.delete();
      System.out.println("File deleted");
    }

    File file2 = new File("invoices.json");
    file2.createNewFile();
    new FileHelper().writeInvoice("[]", file2);

//    indexHelper.saveId(0);
  }
}