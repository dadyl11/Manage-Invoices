package pl.coderstrust.accounting.database.impl.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private int id = 0;

  private int getNextId() {
    return id++;
  }

  @Override
  public int saveInvoice(Invoice invoice) {
    invoice.setId(getNextId());
    invoices.put(invoice.getId(), invoice);
    return invoice.getId();
  }

  @Override
  public List<Invoice> getInvoices() {
    return new ArrayList<>(invoices.values());
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) {
    invoices.put(id, invoice);
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);
  }
}