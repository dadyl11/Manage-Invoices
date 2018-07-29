package pl.coderstrust.accounting.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<Invoice> getInvoicesByIssueDate(LocalDate startDate, LocalDate endDate) {
    List<Invoice> result = new ArrayList<>();
    for (Invoice invoice : database.getInvoices()) {
      if (invoice.getIssueDate().isAfter(startDate) && invoice.getIssueDate().isBefore(endDate)) {
        result.add(invoice);
      }
    }
    return result;
  }

  public Invoice getInvoiceById(int id) {
    List<Invoice> result = database.getInvoices().stream()
        .filter(item -> item.getId() == id)
        .collect(Collectors.toList());
    return result.get(0);
  }
}