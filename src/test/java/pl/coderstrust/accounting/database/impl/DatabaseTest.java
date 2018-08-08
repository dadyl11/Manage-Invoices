package pl.coderstrust.accounting.database.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import java.util.List;
import org.junit.Test;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

public abstract class DatabaseTest {


  protected abstract Database getDatabase();


  @Test
  public void shouldSaveInvoices() throws Exception {
    //given
    Database database = getDatabase();

    //when
    database.saveInvoice(INVOICE_KRAKOW_2018);
    List<Invoice> invoices = database.getInvoices();

    //then
    assertThat(invoices.size(), is(1));
    assertThat(invoices.get(0), is(INVOICE_KRAKOW_2018));
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() throws Exception {

    Database database = getDatabase();

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
    Database database = getDatabase();
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
    Database database = getDatabase();
    int index = database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.updateInvoice(index, INVOICE_BYDGOSZCZ_2018);

    //then
    assertThat(database.getInvoices().size(), is(1));
    assertThat(database.getInvoices().get(0), is(INVOICE_BYDGOSZCZ_2018));
  }
}