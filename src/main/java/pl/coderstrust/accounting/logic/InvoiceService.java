package pl.coderstrust.accounting.logic;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@Service
public class InvoiceService {

  private Database database;

  public InvoiceService(Database database) {
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

  public void updateInvoice(int id, Invoice invoice) {
    database.updateInvoice(id, invoice);
  }

  public void removeInvoiceById(int id) {
    database.removeInvoiceById(id);
  }
}