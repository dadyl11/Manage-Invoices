package pl.coderstrust.accounting.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.DataBase;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(MockitoJUnitRunner.class)
public abstract class InvoiceBookTest {

  public abstract DataBase getDatabase();

  DataBase dataBase = getDatabase();

  DataBase dataBaseMock = mock(dataBase.getClass());
  Invoice invoice = mock(Invoice.class);
  InvoiceBook invoiceBook = new InvoiceBook(dataBaseMock);

  @Test
  public void shouldSaveInvoice() {
    //When
    invoiceBook.save(invoice);

    //Then
    verify(dataBaseMock).save(invoice);
  }

  @Test
  public void shouldGetCollectionOfInvoices() {
    //When
    invoiceBook.getInvoices();

    //Then
    verify(dataBaseMock).getInvoices();
  }

  @Test
  public void shouldUpdateInvoice() {
    //When
    invoiceBook.update(invoice);

    //Then
    verify(dataBaseMock).save(invoice);
  }

  @Test
  public void shouldRemoveInvoiceByID() {
    //Given
    int id = 0;

    //When
    invoiceBook.removeInvoiceById(id);

    //Then
    verify(dataBaseMock).removeInvoiceById(0);
  }


}