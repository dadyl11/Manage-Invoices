package pl.coderstrust.accounting.database;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public abstract class DataBaseTest {

  public abstract Database getDatabase();

  private Database database = getDatabase();

  @Test
  public void shouldSaveInvoices() {
    //Given
    Invoice invoice = mock(Invoice.class);

    //When
    database.save(invoice);

    //Then
    assertThat(database.getInvoices().isEmpty(), is(false));
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() {
    //When
    Collection<Invoice> invoices = database.getInvoices();

    //Then
    assertThat(invoices.isEmpty(), is(true));
  }

  @Test
  public void shouldRemoveInvoices() {
    //Given
    Invoice invoice = mock(Invoice.class);
    when(invoice.getId()).thenReturn(0);

    //When
    database.save(invoice);
    database.removeInvoiceById(0);

    //Then
    assertTrue(database.getInvoices().isEmpty());
  }

  @Test
  public void shouldUpdateInvoice() {
    //Given
    Invoice invoice = mock(Invoice.class);
    Invoice invoice2 = mock(Invoice.class);
    when(invoice.getId()).thenReturn(0);
    when(invoice2.getId()).thenReturn(0);

    //When
    database.save(invoice);
    database.updateInvoice(invoice2);
    database.removeInvoiceById(0);

    //Then
    assertTrue(database.getInvoices().isEmpty());
  }
}