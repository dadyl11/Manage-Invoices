package pl.kmisko.accounting.database;

import java.util.List;
import pl.kmisko.accounting.model.Invoice;

public interface Database {

  int saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();


  void updateInvoiceById(int id, Invoice invoice);

  void removeInvoiceById(int id);


  void clearDatabase();
}