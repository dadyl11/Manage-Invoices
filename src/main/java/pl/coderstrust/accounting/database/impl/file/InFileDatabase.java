package pl.coderstrust.accounting.database.impl.file;

import java.io.IOException;
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
    int id = indexHelper.getIdAndSaveToFile();
    invoice.setId(id);
    fileHelper.writeInvoice(invoiceConverter.convertInvoiceToJson(invoice));
    return id;
  }

  @Override
  public List<Invoice> getInvoices() {
    return fileHelper.lines()
        .stream()
        .map(invoiceConverter::convertJsonToInvoice)
        .collect(Collectors.toList());
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) throws IOException {
    List<Invoice> invoiceList = getAllInvoicesExceptWithSpecifiedId(id);
    invoice.setId(id);
    invoiceList.add(invoice);
    fileHelper.replaceFileContent(invoiceConverter.convertListOfInvoicesToJsons(invoiceList));
  }

  @Override
  public void removeInvoiceById(int id) throws IOException {
    List<String> jsonList = invoiceConverter.convertListOfInvoicesToJsons(getAllInvoicesExceptWithSpecifiedId(id));
    fileHelper.replaceFileContent(jsonList);
  }

  private List<Invoice> getAllInvoicesExceptWithSpecifiedId(int id) {
    return getInvoices()
        .stream()
        .filter(n -> n.getId() != id)
        .collect(Collectors.toList());
  }

  @Override
  public void clearDatabase() {
    fileHelper.clearDatabaseFile();
    indexHelper.clearIdFile();
  }

  @Override
  public void clearDatabase() {
    fileHelper.getDataBaseFile().delete();
  }
}