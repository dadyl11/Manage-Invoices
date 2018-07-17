package pl.coderstrust.accounting.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(MockitoJUnitRunner.class)
public abstract class InvoiceBookTest {

  public abstract Database getDatabase();

  Database database = getDatabase();

  Database databaseMock = mock(Database.class);
  Invoice invoice = mock(Invoice.class);
  InvoiceBook invoiceBook = new InvoiceBook(databaseMock);

  @Test
  public void shouldSaveInvoice() {
    //When
    invoiceBook.save(invoice);

    //Then
    verify(databaseMock).save(invoice);
  }

  @Test
  public void shouldGetCollectionOfInvoices() {
    //When
    invoiceBook.getInvoices();

    //Then
    verify(databaseMock).getInvoices();
  }

  @Test
  public void shouldUpdateInvoice() {
    //When
    invoiceBook.update(invoice);

    //Then
    verify(databaseMock).save(invoice);
  }

  @Test
  public void shouldRemoveInvoiceById() {
    //Given
    int id = 0;

    //When
    invoiceBook.removeInvoiceById(id);

    //Then
    verify(databaseMock).removeInvoiceById(0);
  }


}