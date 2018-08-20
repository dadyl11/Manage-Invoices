package pl.coderstrust.accounting.database;

import java.io.IOException;
import java.util.List;
import pl.coderstrust.accounting.model.Invoice;

public interface Database {

  int saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

  void updateInvoice(int id, Invoice invoice);

  void removeInvoiceById(int id); // TODO you remove byId but update without id - be consistent please :)

  void clearDatabase();
}