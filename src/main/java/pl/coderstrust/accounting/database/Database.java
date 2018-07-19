package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

public interface Database {

  int saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

  void updateInvoice(Invoice invoice);

  void removeInvoiceById(int id);

}
