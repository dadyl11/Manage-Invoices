package pl.coderstrust.accounting.database;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public abstract class DatabaseTest {

  protected abstract Database getDatabase();

  private Database database = getDatabase();

  @Test
  public void shouldSaveInvoices() {
    //Given
    Invoice invoice = mock(Invoice.class);

    //When
    database.saveInvoice(invoice);

    //Then
    assertThat(database.getInvoices(), is(not(empty())));
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() {
    //When
    Collection<Invoice> invoices = database.getInvoices();

    //Then
    assertThat(database.getInvoices(), is(empty()));
  }

  @Test
  public void shouldRemoveInvoices() {
    //Given
    Invoice invoice = mock(Invoice.class);

    //When
    database.saveInvoice(invoice);
    database.removeInvoiceById(1);

    //Then
    assertThat(database.getInvoices(), is(empty()));
  }

  @Test
  public void shouldUpdateInvoice() {
    //Given
    Invoice invoice = mock(Invoice.class);
    Invoice invoice2 = mock(Invoice.class);

    //When
    database.saveInvoice(invoice);
    database.updateInvoice(invoice2);
    database.removeInvoiceById(1);

    //Then
    assertTrue(database.getInvoices().isEmpty());
  }
}