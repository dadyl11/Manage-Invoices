package pl.coderstrust.accounting.logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.helpers.InvoiceProvider;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  @Mock
  Database databaseMock;

  @InjectMocks
  InvoiceBook invoiceBook;

  @Test
  public void shouldSaveInvoice() {
    //given

    //when
    invoiceBook.saveInvoice(InvoiceProvider.invoice1);

    //then
    verify(databaseMock).saveInvoice(InvoiceProvider.invoice1);
  }

  @Test
  public void shouldGetCollectionOfInvoices() {
    //given
    List<Invoice> invoices = new ArrayList<>();
    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    invoiceBook.getInvoices();

    //then
    verify(databaseMock).getInvoices();
    assertThat(databaseMock.getInvoices(), is(invoiceBook.getInvoices()));
    assertThat(invoiceBook.getInvoices(), is(invoices));
  }

  @Test
  public void shouldUpdateInvoice() {
    //when
    invoiceBook.updateInvoice(InvoiceProvider.invoice1);

    //then
    verify(databaseMock).saveInvoice(InvoiceProvider.invoice1);
  }

  @Test
  public void shouldRemoveInvoiceById() {
    //given
    int id = 0;

    //when
    invoiceBook.removeInvoiceById(id);

    //then
    verify(databaseMock).removeInvoiceById(0);
  }


}