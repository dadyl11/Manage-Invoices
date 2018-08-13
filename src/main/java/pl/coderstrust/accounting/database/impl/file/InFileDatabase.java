package pl.coderstrust.accounting.database.impl.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.file.helpers.FileHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;
import pl.coderstrust.accounting.model.Invoice;

@Primary
@Repository
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
    fileHelper.writeInvoice(invoiceConverter.writeJson(invoice));
    return id;
  }

  @Override
  public List<Invoice> getInvoices() throws IOException {
    List<String> jsonList = fileHelper.readLines();
    List<Invoice> invoiceList = new ArrayList<>();
    for (String jsonInvoice : jsonList) {
      invoiceList.add(invoiceConverter.readJson(jsonInvoice));
    }
    return invoiceList;
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) throws IOException {
    List<Invoice> invoiceList = getInvoices()
        .stream()
        .filter(n -> n.getId() != id)
        .collect(Collectors.toList());
    invoice.setId(id);
    invoiceList.add(invoice);
    fileHelper.clearDatabaseFile();
    for (Invoice invoices : invoiceList) {
      fileHelper.writeInvoice(invoiceConverter.writeJson(invoices));
    }
  }

  @Override
  public void removeInvoiceById(int id) throws IOException {
    List<Invoice> invoiceList = getInvoices()
        .stream()
        .filter(invoice -> invoice.getId() != id)
        .collect(Collectors.toList());
    fileHelper.clearDatabaseFile();
    for (Invoice invoice : invoiceList) {
      fileHelper.writeInvoice(invoiceConverter.writeJson(invoice));
    }
  }

  @Override
  public void clearDatabase() {
    fileHelper.clearDatabaseFile();
    indexHelper.deleteIdFileContent();
  }
}