package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataBase implements DataBase {

  private final Map<Integer, Invoice> invoices = new HashMap<>();

  @Override
  public void save(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);
  }

  @Override
  public Collection<Invoice> getInvoices() {
    return invoices.values();
  }

  @Override
  public void update(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);
  }
}
