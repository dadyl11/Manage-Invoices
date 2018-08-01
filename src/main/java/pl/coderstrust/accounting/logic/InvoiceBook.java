package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

public class InvoiceBook {

  private Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public void saveInvoice(Invoice invoice) throws Exception {
    if (invoice == null) {
      throw new IllegalArgumentException("invoice cannot be null");
    }
    database.saveInvoice(invoice);
  }

  public List<Invoice> getInvoices() throws Exception {
    return database.getInvoices();
  }

  public void updateInvoice(int id, Invoice invoice) throws Exception {
    database.updateInvoice(id, invoice);
  }

  public void removeInvoiceById(int id) throws Exception {
    database.removeInvoiceById(id);
  }
}