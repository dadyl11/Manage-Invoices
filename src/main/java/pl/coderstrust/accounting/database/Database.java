package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

public interface Database {

  int saveInvoice(Invoice invoice);

  Collection<Invoice> getInvoices();

  void updateInvoice(Invoice invoice);

  void removeInvoiceById(int id);

}
