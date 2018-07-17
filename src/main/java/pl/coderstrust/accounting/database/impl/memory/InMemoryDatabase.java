package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private int id = 0;

  private int getNextId() {
//    Scanner scanner = new Scanner("invoiceId.txt");
//    id = scanner.nextInt();
    id++;
//    DataOutputStream dataStream = new DataOutputStream(new FileOutputStream("invoice.txt"));
//    dataStream.write(id);
    return id;
  }

  @Override
  public int save(Invoice invoice) {
    id = getNextId();
    invoices.put(id, invoice);
    return id;
  }

  @Override
  public Collection<Invoice> getInvoices() {
    return invoices.values();
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);
  }
}
