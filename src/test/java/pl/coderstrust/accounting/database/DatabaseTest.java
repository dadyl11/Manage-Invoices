package pl.coderstrust.accounting.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.helpers.InvoiceProvider;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public abstract class DatabaseTest {

  protected abstract Database getDatabase();

  private Database database = getDatabase();

  @Test
  public void shouldSaveInvoices() {
    //given

    //when
    database.saveInvoice(InvoiceProvider.invoice1);
    List<Invoice> invoices = database.getInvoices();

    //then
    assertThat(invoices.get(0), is(InvoiceProvider.invoice1));
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() {
    //when
    database.saveInvoice(InvoiceProvider.invoice1);
    database.saveInvoice(InvoiceProvider.invoice2);
    List<Invoice> invoices = database.getInvoices();

    //then
    assertThat(database.getInvoices().size(), is(2));
  }

  @Test
  public void shouldRemoveInvoices() {
    //given
    database.saveInvoice(InvoiceProvider.invoice1);
    database.saveInvoice(InvoiceProvider.invoice2);

    //when
    database.removeInvoiceById(1);

    //then
    assertThat(database.getInvoices().get(0), is(InvoiceProvider.invoice2));
  }

  @Test
  public void shouldUpdateInvoice() {
    //given
    database.saveInvoice(InvoiceProvider.invoice2);
    database.saveInvoice(InvoiceProvider.invoice1);

    //when
    database.updateInvoice(InvoiceProvider.invoice4);

    //then
    assertThat(database.getInvoices().get(1), is(InvoiceProvider.invoice4));
  }
}