package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice) throws Exception;

  List<Invoice> getInvoices() throws Exception;

  void updateInvoice(int id, Invoice invoice) throws Exception;

  void removeInvoiceById(int id) throws Exception;

}