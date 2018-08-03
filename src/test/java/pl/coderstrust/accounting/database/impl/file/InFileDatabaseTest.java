package pl.coderstrust.accounting.database.impl.file;

import static pl.coderstrust.accounting.database.impl.file.helpers.FileHelper.dataBaseFile;
import static pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper.currentIdFile;

import org.junit.Before;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.DatabaseTest;
import pl.coderstrust.accounting.database.impl.file.helpers.FileHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;

import java.io.File;
import java.io.IOException;

public class InFileDatabaseTest extends DatabaseTest {

  @Override
  protected Database getDatabase() {
    FileHelper fileHelper = new FileHelper();
    InvoiceConverter invoiceConverter = new InvoiceConverter();
    IndexHelper indexHelper = new IndexHelper();
    return new InFileDatabase(fileHelper, invoiceConverter, indexHelper);
  }

  @Before
  public void beforeMethod() throws IOException {
    File file = dataBaseFile;
    if (file.exists()) {
      file.delete();
      System.out.println("File deleted");
    }

    File file1 = currentIdFile;
    if (file1.exists()) {
      file1.delete();
      System.out.println("File deleted");
    }

    File file2 = file;
    file2.createNewFile();
    new FileHelper().writeInvoice("[]", file2);
  }
}