package pl.coderstrust.accounting.logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  @Mock
  private Database databaseMock;

  @InjectMocks
  private InvoiceBook invoiceBook;

  @Test
  public void shouldSaveInvoice() {
    //given

    //when
    invoiceBook.saveInvoice(INVOICE_KRAKOW_2018);

    //then
    verify(databaseMock).saveInvoice(INVOICE_KRAKOW_2018);
  }

  @Test
  public void shouldGetCollectionOfInvoices() {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_BYDGOSZCZ_2018);
    invoices.add(INVOICE_CHELMNO_2016);
    invoices.add(INVOICE_GRUDZIADZ_2017);
    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    List<Invoice> actual = invoiceBook.getInvoices();

    //then
    verify(databaseMock).getInvoices();
    assertThat(actual, is(invoices));
    assertTrue(actual.contains(INVOICE_BYDGOSZCZ_2018));
    assertTrue(actual.contains(INVOICE_CHELMNO_2016));
    assertTrue(actual.contains(INVOICE_GRUDZIADZ_2017));
  }

  @Test
  public void shouldUpdateInvoice() {
    //when
    invoiceBook.updateInvoice(3, INVOICE_KRAKOW_2018);

    //then
    verify(databaseMock).updateInvoice(3, INVOICE_KRAKOW_2018);
  }

  @Test
  public void shouldRemoveInvoiceById() {
    //given
    int id = 0;

    //when
    invoiceBook.removeInvoiceById(id);

    //then
    verify(databaseMock).removeInvoiceById(id);
  }
}