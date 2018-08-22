package pl.coderstrust.accounting.database.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import java.util.List;
import java.util.Optional;
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
    int actualId = database.saveInvoice(INVOICE_KRAKOW_2018);
    List<Invoice> invoices = database.getInvoices();
    Invoice savedInvoice = getInvoiceById(actualId, invoices);

    //then
    assertThat(invoices.size(), is(1));
    assertInvoices(actualId, INVOICE_KRAKOW_2018, savedInvoice);
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() throws Exception {
    //given
    Database database = getDatabase();

    //when
    int actualIdA = database.saveInvoice(INVOICE_KRAKOW_2018);
    int actualIdB = database.saveInvoice(INVOICE_GRUDZIADZ_2017);
    List<Invoice> invoices = database.getInvoices();
    Invoice savedInvoiceA = getInvoiceById(actualIdA, invoices);
    Invoice savedInvoiceB = getInvoiceById(actualIdB, invoices);

    //then
    assertThat(database.getInvoices().size(), is(2));
    assertInvoices(actualIdA, INVOICE_KRAKOW_2018, savedInvoiceA);
    assertInvoices(actualIdB, INVOICE_GRUDZIADZ_2017, savedInvoiceB);
  }

  @Test
  public void shouldRemoveInvoices() throws Exception {
    //given
    Database database = getDatabase();
    int idA = database.saveInvoice(INVOICE_KRAKOW_2018);
    int idB = database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.removeInvoiceById(idA);
    List<Invoice> invoices = database.getInvoices();
    Invoice savedInvoiceB = getInvoiceById(idB, invoices);

    //then
    assertThat(database.getInvoices().size(), is(1));
    assertInvoices(idB, INVOICE_GRUDZIADZ_2017, savedInvoiceB);
  }

  @Test
  public void shouldUpdateInvoice() throws Exception {
    //given
    Database database = getDatabase();
    int returnedId = database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.updateInvoiceById(returnedId, INVOICE_BYDGOSZCZ_2018);
    List<Invoice> invoices = database.getInvoices();
    Invoice savedInvoice = getInvoiceById(returnedId, invoices);

    //then
    assertThat(database.getInvoices().size(), is(1));
    assertInvoices(returnedId, INVOICE_BYDGOSZCZ_2018, savedInvoice);
  }

  public void assertInvoices(int returnedId, Invoice invoice, Invoice savedInvoice) {

    assertThat(savedInvoice.getId(), is(returnedId));
    assertThat(savedInvoice.getIdentifier(), is(invoice.getIdentifier()));
    assertThat(savedInvoice.getSalePlace(), is(invoice.getSalePlace()));
    assertThat(savedInvoice.getBuyer().getName(), is(invoice.getBuyer().getName()));
    assertThat(savedInvoice.getBuyer().getNip(), is(invoice.getBuyer().getNip()));
    assertThat(savedInvoice.getBuyer().getStreet(), is(invoice.getBuyer().getStreet()));
    assertThat(savedInvoice.getBuyer().getPostalCode(), is(invoice.getBuyer().getPostalCode()));
    assertThat(savedInvoice.getBuyer().getDiscount().doubleValue(), is(invoice.getBuyer().getDiscount().doubleValue()));
    assertThat(savedInvoice.getSeller().getName(), is(invoice.getSeller().getName()));
    assertThat(savedInvoice.getSeller().getNip(), is(invoice.getSeller().getNip()));
    assertThat(savedInvoice.getSeller().getStreet(), is(invoice.getSeller().getStreet()));
    assertThat(savedInvoice.getSeller().getPostalCode(), is(invoice.getSeller().getPostalCode()));
    assertThat(savedInvoice.getSeller().getDiscount().doubleValue(), is(invoice.getSeller().getDiscount().doubleValue()));
    assertThat(savedInvoice.getEntries().get(0).getDescription(), is(invoice.getEntries().get(0).getDescription()));
    assertThat(savedInvoice.getEntries().get(0).getNetPrice().intValue(), is(invoice.getEntries().get(0).getNetPrice().intValue()));
    assertThat(savedInvoice.getEntries().get(0).getVatRate().toString(), is(invoice.getEntries().get(0).getVatRate().toString()));
    assertThat(savedInvoice.getEntries().get(0).getQuantity().intValue(), is(invoice.getEntries().get(0).getQuantity().intValue()));
  }

  public Invoice getInvoiceById(int id, List<Invoice> invoices) {
    return invoices
        .stream()
        .filter(invoice -> invoice.getId() == id)
        .findAny()
        .get();
  }


}