package pl.coderstrust.accounting.database.impl.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.file.helpers.FileHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;
import pl.coderstrust.accounting.model.Invoice;

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
  public int saveInvoice(Invoice invoice) throws IOException {
    int id = indexHelper.generateId();
    invoice.setId(id);
    List<Invoice> allInvoices = getInvoices();
    allInvoices.add(invoice);
    fileHelper.writeInvoice(invoiceConverter.writeJson(allInvoices), fileHelper.getDataBaseFile());
    return id;
  }

  @Override
  public List<Invoice> getInvoices() throws IOException {
    String jsonList = fileHelper.readLines(fileHelper.getDataBaseFile());
    return invoiceConverter.readJson(jsonList);
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) throws IOException {
    List<Invoice> invoiceList = new ArrayList<>(getInvoices());
    for (int i = 0; i < invoiceList.size(); i++) {
      Invoice inv = invoiceList.get(i);
      if (inv.getId() == id) {
        invoiceList.remove(inv);
        invoice.setId(id);
        invoiceList.add(invoice);
      }
    }
    fileHelper.writeInvoice(invoiceConverter.writeJson(invoiceList), fileHelper.getTemporaryDataBaseFile());
    fileHelper.replaceInvoicesFiles();
  }

  @Override
  public void removeInvoiceById(int id) throws IOException {
    List<Invoice> invoiceList = new ArrayList<>(getInvoices());
    invoiceList = invoiceList.stream()
        .filter(invoice -> invoice.getId() != id)
        .collect(Collectors.toList());
    fileHelper.writeInvoice(invoiceConverter.writeJson(invoiceList), fileHelper.getTemporaryDataBaseFile());
    fileHelper.replaceInvoicesFiles();
  }
}
