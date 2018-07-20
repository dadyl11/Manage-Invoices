package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private static int id = 0;

  private int getNextId() {
    return id++;
  }

  @Override
  public int saveInvoice(Invoice invoice) {
    invoice.setId(getNextId());
    invoices.put(id, invoice);
    return id;
  }

  @Override
  public List<Invoice> getInvoices() {
    List<Invoice> list = new ArrayList<>(invoices.values());
    return list;
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    invoices.put(id, invoice);
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);
  }
}