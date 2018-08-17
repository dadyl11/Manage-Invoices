package pl.coderstrust.accounting.database;

import java.io.IOException;
import java.util.List;
import pl.coderstrust.accounting.model.Invoice;

public interface Database {

  int saveInvoice(Invoice invoice) throws IOException; // TODO can use do something with this exception?

  List<Invoice> getInvoices() throws IOException;

  void updateInvoice(int id, Invoice invoice) throws IOException;

  void removeInvoiceById(int id) throws IOException; // TODO you remove byId but update without id - be consistent please :)

  void clearDatabase();
}