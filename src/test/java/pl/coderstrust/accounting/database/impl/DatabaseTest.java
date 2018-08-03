package pl.coderstrust.accounting.database.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public abstract class DatabaseTest {

  protected abstract Database getDatabase();

  private Database database = getDatabase();

  @Test
  public void shouldSaveInvoices() throws Exception {
    //given

    //when
    database.saveInvoice(INVOICE_KRAKOW_2018);
    List<Invoice> invoices = database.getInvoices();

    //then
    assertThat(invoices.size(), is(1));
    assertThat(invoices.get(0), is(INVOICE_KRAKOW_2018));
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() throws Exception {
    //when
    database.saveInvoice(INVOICE_KRAKOW_2018);
    database.saveInvoice(INVOICE_GRUDZIADZ_2017);
    List<Invoice> invoices = database.getInvoices();

    //then
    assertThat(database.getInvoices().size(), is(2));
    assertThat(invoices, hasItem(INVOICE_KRAKOW_2018));
    assertThat(invoices, hasItem(INVOICE_GRUDZIADZ_2017));
  }

  @Test
  public void shouldRemoveInvoices() throws Exception {
    //given
    database.saveInvoice(INVOICE_KRAKOW_2018);
    database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.removeInvoiceById(INVOICE_KRAKOW_2018.getId());

    //then
    assertThat(database.getInvoices().size(), is(1));
    assertThat(database.getInvoices().get(0), is(INVOICE_GRUDZIADZ_2017));
  }

  @Test
  public void shouldUpdateInvoice() throws Exception {
    //given
    int index = database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.updateInvoice(index, INVOICE_BYDGOSZCZ_2018);

    //then
    assertThat(database.getInvoices().size(), is(1));
    assertThat(database.getInvoices().get(0), is(INVOICE_BYDGOSZCZ_2018));
  }
}