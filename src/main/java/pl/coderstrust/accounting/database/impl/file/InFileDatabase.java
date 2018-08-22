package pl.coderstrust.accounting.database.impl.file;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.file.helpers.FileHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.IndexHelper;
import pl.coderstrust.accounting.database.impl.file.helpers.InvoiceConverter;
import pl.coderstrust.accounting.model.Invoice;

@Repository
public class InFileDatabase implements Database {

  private FileHelper fileHelper;
  private InvoiceConverter invoiceConverter;
  private IndexHelper indexHelper;

  public InFileDatabase(FileHelper fileHelper, InvoiceConverter invoiceConverter, IndexHelper indexHelper) {
    this.fileHelper = fileHelper;
    this.invoiceConverter = invoiceConverter;
    this.indexHelper = indexHelper;
  }

  @Override
  public int saveInvoice(Invoice invoice) {
    int id = indexHelper.getIdAndSaveToFile();
    Invoice internalInvoice = new Invoice(invoice);
    internalInvoice.setId(id);
    fileHelper.writeInvoice(invoiceConverter.convertInvoiceToJson(internalInvoice));
    return id;
  }

  @Override
  public List<Invoice> getInvoices() {
    return fileHelper.lines() // TODO this method convert stream to list and then you convert back - can you optimize?
        .stream()
        .map(invoiceConverter::convertJsonToInvoice)
        .collect(Collectors.toList()); // TODO invoice converter can convert list of invoices to strings, why cannot opposite?
  }

  @Override
  public void updateInvoiceById(int id, Invoice invoice) {

    List<Invoice> invoiceList = getAllInvoicesExceptInvoiceWithSpecifiedId(id);
    Invoice internalInvoice = new Invoice(invoice);
    internalInvoice.setId(id);
    invoiceList.add(internalInvoice);
    fileHelper.replaceFileContent(invoiceConverter.convertListOfInvoicesToListOfStrings(invoiceList));
  }

  @Override
  public void removeInvoiceById(int id) {
    // TODO this method and this above are almist identical but organised different way e.g. invoiceList should be variable here
    List<String> jsonList = invoiceConverter.convertListOfInvoicesToListOfStrings(getAllInvoicesExceptInvoiceWithSpecifiedId(id));
    fileHelper.replaceFileContent(jsonList);
  }

  private List<Invoice> getAllInvoicesExceptInvoiceWithSpecifiedId(int id) {
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

}