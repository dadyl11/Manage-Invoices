package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

public class InvoiceBook {

  private Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public void save(Invoice invoice) {
    if (invoice == null) {
      throw new IllegalArgumentException("Incorrect invoice property");
    }
    database.save(invoice);
  }

  public Collection<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public void update(Invoice invoice) {
    database.save(invoice);
  }

  public void removeInvoiceById(int id) {
    database.removeInvoiceById(id);
  }
}