package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private static int id = 0;

  private int getNextId() {
    id++;
    return id;
  }

  @Override
  public int saveInvoice(Invoice invoice) {
    invoice.setId(getNextId());
    invoices.put(invoice.getId(), invoice);
    return id;
  }

  @Override
  public Collection<Invoice> getInvoices() {
    return invoices.values();
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);
  }
}