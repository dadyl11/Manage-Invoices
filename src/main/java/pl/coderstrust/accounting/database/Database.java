package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.List;

public interface Database {

  int saveInvoice(Invoice invoice) throws IOException;

  List<Invoice> getInvoices() throws IOException;

  void updateInvoice(int id, Invoice invoice) throws IOException;

  void removeInvoiceById(int id) throws IOException;

}