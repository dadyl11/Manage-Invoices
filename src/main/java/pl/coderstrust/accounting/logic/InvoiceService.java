package pl.coderstrust.accounting.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@Service
public class InvoiceService {

  private Database database;

  public InvoiceService(Database database) {
    this.database = database;
  }

  public int saveInvoice(Invoice invoice) {
    if (invoice == null) {
      throw new IllegalArgumentException("invoice cannot be null");
    }
    return database.saveInvoice(invoice);
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public Optional<Invoice> getInvoiceById(int id) {
    return database.getInvoices().stream().filter(invoice -> invoice.getId() == id).findAny();
  }

  public List<Invoice> getInvoicesByIssueDate(LocalDate startDate, LocalDate endDate) {
    List<Invoice> result = new ArrayList<>();
    for (Invoice invoice : database.getInvoices()) {
      if (invoice.getIssueDate().isAfter(startDate) && invoice.getIssueDate().isBefore(endDate)) {
        result.add(invoice);
      }
    }
    return result;
  }

  public void updateInvoice(int id, Invoice invoice) {
    database.updateInvoice(id, invoice);
  }

  public void removeInvoiceById(int id) {
    database.removeInvoiceById(id);
  }
}