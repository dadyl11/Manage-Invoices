package pl.coderstrust.accounting.database.impl.memory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@Repository
public class InMemoryDatabase implements Database {

  private Map<Integer, Invoice> invoices = new HashMap<>();
  private int id = 0;

  private int getNextId() {
    return id++;
  }

  @Override
  public int saveInvoice(Invoice invoice) {
    invoice.setId(getNextId()); // TODO you cannot modify object you received - good example is Invoice provider -
//    with each test you are changing the invoice so other tests are failing... There is also other aspect of that
//    security - you keep in your collection the same object someone has reference too and he can manipulate this object :)
//    Please correct workaround below to do deep copy (not shallow one as now) - best do copy constructor in class
    Invoice internalInvoice = new Invoice.Invoicebuilder()
        .identifier(invoice.getIdentifier())
        .issueDate(invoice.getIssueDate())
        .saleDate(invoice.getSaleDate())
        .salePlace(invoice.getSalePlace())
        .buyer(invoice.getBuyer())
        .seller(invoice.getSeller())
        .entries(invoice.getEntries())
        .build();
    internalInvoice.setId(getNextId());
    invoices.put(internalInvoice.getId(), internalInvoice);
    return internalInvoice.getId();
  }

  @Override
  public List<Invoice> getInvoices() {
    return new ArrayList<>(invoices.values());
  }

  @Override
  public void updateInvoiceById(int id, Invoice invoice) {
    //invoice.setId(id); // TODO you cannot modify object you received - good example is Invoice provider -
    // with each test you are changing the invoice so other tests are failing... There is also other aspect of that
    // security - you keep in your collection the same object someone has reference too and he can manipulate this object :)
    // Please correct workaround below to do deep copy (not shallow one as now) - best do copy constructor in class

    Invoice internalInvoice = new Invoice.Invoicebuilder()
        .identifier(invoice.getIdentifier())
        .issueDate(invoice.getIssueDate())
        .saleDate(invoice.getSaleDate())
        .salePlace(invoice.getSalePlace())
        .buyer(invoice.getBuyer())
        .seller(invoice.getSeller())
        .entries(invoice.getEntries())
        .build();
    invoices.put(id, internalInvoice);
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);
  }

  @Override
  public void clearDatabase() {
    invoices.clear();
    id = 0;
  }

  public static void main(String[] args) {

  }
}