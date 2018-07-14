package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Arrays;
import java.util.Collection;

public class InvoiceBook {

  private DataBase dataBase;

  public InvoiceBook(DataBase dataBase) {
    this.dataBase = dataBase;
  }

  public void save(Invoice invoice) {
    if (invoice != null) {
      dataBase.save(invoice);
    }
  }

  public Collection<Invoice> getInvoices() {
    return Arrays.asList();
  }

  public void update(Invoice invoice) {
    dataBase.save(invoice);
  }

  public void removeInvoiceById(int id) {
    dataBase.removeInvoiceById(id);
  }
}
