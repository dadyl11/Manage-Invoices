package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

public class InFileDatabase implements DataBase {


  @Override
  public void save(Invoice invoice) {

  }

  @Override
  public Collection<Invoice> getInvoices() {
    return null;
  }

  @Override
  public void update(Invoice invoice) {

  }

  @Override
  public void removeInvoiceById(int id) {

  }
}
