package pl.coderstrust.accounting.logic;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@Component
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

  public Invoice getInvoiceById(int id) {
    return database.getInvoices().get(id);
  }

  public List<Invoice> getInvoicesByDate(LocalDate startDate, LocalDate endDate) {
    for (Invoice invoice : database.getInvoices()) {

    }
    return database.getInvoices();
  }

  public void updateInvoice(int id, Invoice invoice) {
    database.updateInvoice(id, invoice);
  }

  public void removeInvoiceById(int id) {
    database.removeInvoiceById(id);
  }
}