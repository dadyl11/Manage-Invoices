package pl.coderstrust.accounting.database.impl.file;

import static pl.coderstrust.accounting.database.impl.file.helpers.FileHelper.dataBaseFile;
import static pl.coderstrust.accounting.database.impl.file.helpers.FileHelper.temporaryDataBaseFile;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.file.helpers.FileHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InFileDatabase implements Database {

  private FileHelper fileHelper;
  private InvoiceConverter invoiceConverter;
  private IndexHelper indexHelper;

  public InFileDatabase(FileHelper fileHelper,
      InvoiceConverter invoiceConverter, IndexHelper indexHelper) {
    this.fileHelper = fileHelper;
    this.invoiceConverter = invoiceConverter;
    this.indexHelper = indexHelper;
  }

  private InFileDatabase inFileDatabase;

  @Override
  public void saveInvoice(Invoice invoice) throws Exception {
    int id = indexHelper.generateId();
    invoice.setId(id);
    fileHelper.writeInvoice(invoiceConverter.writeJson(invoice), dataBaseFile);
  }

  @Override
  public List<Invoice> getInvoices() throws Exception {
    List<Invoice> invoiceList = new ArrayList<>();
    List<String> jsonList = fileHelper.readLines(dataBaseFile);
    for (String invoice : jsonList) {
      invoiceList.add(invoiceConverter.readJson(invoice));
    }
    return invoiceList;
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) throws Exception {
    List<Invoice> list = new ArrayList<>(inFileDatabase.getInvoices());
    for (Invoice inv : list) {
      if (inv.getId() != id) {
        fileHelper.writeInvoice(invoiceConverter.writeJson(inv), temporaryDataBaseFile);
      }
      if (inv.getId() == id) {
        list.remove(id);
        invoice.setId(id);
        fileHelper.writeInvoice(invoiceConverter.writeJson(inv), temporaryDataBaseFile);
      } else {
        throw new NoSuchElementException("Can't update invoice, such id doesn't exist");
      }
    }
    fileHelper.replaceInvoicesFiles();
  }

  @Override
  public void removeInvoiceById(int id) throws Exception {
    List<Invoice> list = new ArrayList<>(getInvoices());
    list.remove(id);
    for (Invoice inv : list) {
      fileHelper.writeInvoice(invoiceConverter.writeJson(inv), temporaryDataBaseFile);
    }
    fileHelper.replaceInvoicesFiles();
  }
}
