package pl.coderstrust.accounting.logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public abstract class InvoiceBookTest {

  @Mock
  Collection<Invoice> collection;
  Database databaseMock = mock(Database.class);
  Invoice invoice = mock(Invoice.class);
  InvoiceBook invoiceBook = new InvoiceBook(databaseMock);

  @Test
  public void shouldSaveInvoice() {
    //When
    invoiceBook.save(invoice);

    //Then
    verify(databaseMock).saveInvoice(invoice);
  }

  @Test
  public void shouldGetCollectionOfInvoices() {
    //Given
    when(databaseMock.getInvoices()).thenReturn(collection);

    //When
    invoiceBook.get();

    //Then
    verify(databaseMock).getInvoices();
    assertThat(databaseMock.getInvoices(), is(invoiceBook.get()));
  }

  @Test
  public void shouldUpdateInvoice() {
    //When
    invoiceBook.update(invoice);

    //Then
    verify(databaseMock).saveInvoice(invoice);
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