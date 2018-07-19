package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

public class InvoiceBook {

  private Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public void saveInvoice(Invoice invoice) {
    if (invoice == null) {
      throw new IllegalArgumentException("invoice cannot be null");
    }
    database.saveInvoice(invoice);
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public void updateInvoice(Invoice invoice) {
    database.saveInvoice(invoice);
  }

  public void removeInvoiceById(int id) {
    database.removeInvoiceById(id);
  }
}