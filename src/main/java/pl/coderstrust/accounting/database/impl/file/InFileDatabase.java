package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.model.Invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

public class InFileDatabase implements DataBase {

  @Override
  public void save(Invoice invoice) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"))) {
      String invoiceAsString = invoice.toString();
      bufferedWriter.write(invoiceAsString);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public Collection<Invoice> getInvoices() {
    return null;
  }

  @Override
  public void update(Invoice invoice) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"))) {
      String invoiceAsString = invoice.toString();
      bufferedWriter.write(invoiceAsString);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void removeInvoiceById(int id) {
  }
}
