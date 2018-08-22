package pl.coderstrust.accounting.database.impl;

import static org.hamcrest.MatcherAssert.assertThat;
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
    // assertThat(invoices.get(0), is(INVOICE_KRAKOW_2018));
    // TODO assertion not passing because of id - you need to rethink how it's handled - you cannot modify objects in provider
    // alternatively you can modify it but than fields cannot be static and you need to replace it with methods returning
    // new object each time.
    // you can also verify each fields separately here  - then you can easily assert id correctly (using id returned by save method)
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() throws Exception {
    //given
    Database database = getDatabase();

    //when
    database.saveInvoice(INVOICE_KRAKOW_2018);
    database.saveInvoice(INVOICE_GRUDZIADZ_2017);
    List<Invoice> invoices = database.getInvoices();

    //then
    assertThat(database.getInvoices().size(), is(2));
    //    assertThat(invoices, hasItem(INVOICE_KRAKOW_2018));
    //    assertThat(invoices, hasItem(INVOICE_GRUDZIADZ_2017));
    // TODO assertion not passing because of id - you need to rethink how it's handled - you cannot modify objects in provider
    // alternatively you can modify it but than fields cannot be static and you need to replace it with methods returning
    // new object each time.
    // you can also verify each fields separately here  - then you can easily assert id correctly (using id returned by save method)
  }

  @Test
  public void shouldRemoveInvoices() throws Exception {
    //given
    Database database = getDatabase();
    int firsrId = database.saveInvoice(INVOICE_KRAKOW_2018);
    database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.removeInvoiceById(firsrId);

    //then
    assertThat(database.getInvoices().size(), is(1));
    assertThat(database.getInvoices().get(0), is(INVOICE_GRUDZIADZ_2017));
    // TODO assertion not passing because of id - you need to rethink how it's handled - you cannot modify objects in provider
    // alternatively you can modify it but than fields cannot be static and you need to replace it with methods returning
    // new object each time.
    // you can also verify each fields separately here  - then you can easily assert id correctly (using id returned by save method);
  }

  @Test
  public void shouldUpdateInvoice() throws Exception {
    //given
    Database database = getDatabase();
    int returnedId = database.saveInvoice(INVOICE_GRUDZIADZ_2017);

    //when
    database.updateInvoiceById(returnedId, INVOICE_BYDGOSZCZ_2018);
    Invoice savedInvoice = database.getInvoices().get(0);

    //then
    assertThat(database.getInvoices().size(), is(1));

    assertInvoices(returnedId, INVOICE_BYDGOSZCZ_2018, savedInvoice);

    //assertThat(database.getInvoices().get(0), is(INVOICE_BYDGOSZCZ_2018));
    // TODO assertion not passing because of id - you need to rethink how it's handled - you cannot modify objects in provider
    // alternatively you can modify it but than fields cannot be static and you need to replace it with methods returning
    // new object each time.
    // you can also verify each fields separately here  - then you can easily assert id correctly (using id returned by save method)
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
}