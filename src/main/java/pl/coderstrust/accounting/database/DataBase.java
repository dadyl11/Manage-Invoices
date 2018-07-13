package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

public interface DataBase {

  void save(Invoice invoice);

  Collection<Invoice> getInvoices();

  void update(Invoice invoice);

  void removeInvoiceById(int id);

}
