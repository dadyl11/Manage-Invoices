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

  @Override
  public void saveInvoice(Invoice invoice) throws Exception {
    int id = indexHelper.generateId();
    invoice.setId(id);
    List<Invoice> allInvoices = getInvoices();
    allInvoices.add(invoice);
    fileHelper.writeInvoice(invoiceConverter.writeJson(allInvoices), dataBaseFile);
  }

  @Override
  public List<Invoice> getInvoices() throws Exception {
    List<Invoice> invoiceList = new ArrayList<>();
    String jsonList = fileHelper.readLines(dataBaseFile);
    return invoiceConverter.readJson(jsonList);
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) throws Exception {
    List<Invoice> invoiceList = new ArrayList<>(getInvoices());
    for (int i = 0; i < invoiceList.size(); i++) {
      Invoice inv = invoiceList.get(i);
      if (inv.getId() == id) {
        invoiceList.remove(inv);
        invoice.setId(id);
        invoiceList.add(invoice);
      }
    }
    fileHelper.writeInvoice(invoiceConverter.writeJson(invoiceList), temporaryDataBaseFile);
    fileHelper.replaceInvoicesFiles();
  }

  @Override
  public void removeInvoiceById(int id) throws Exception {
    List<Invoice> invoiceList = new ArrayList<>(getInvoices());
    invoiceList.remove(id);
    fileHelper.writeInvoice(invoiceConverter.writeJson(invoiceList), temporaryDataBaseFile);
    fileHelper.replaceInvoicesFiles();
  }
}
