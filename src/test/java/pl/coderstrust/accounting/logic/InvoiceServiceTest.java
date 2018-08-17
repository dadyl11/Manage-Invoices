package pl.coderstrust.accounting.logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_CHELMNO_2016;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_GRUDZIADZ_2017;
import static pl.coderstrust.accounting.helpers.InvoiceProvider.INVOICE_KRAKOW_2018;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Mock
  private Database databaseMock;

  @InjectMocks
  private InvoiceService invoiceService;

  @Test
  public void shouldSaveInvoice() throws Exception {
    //given
    when(databaseMock.saveInvoice(INVOICE_KRAKOW_2018)).thenReturn(INVOICE_KRAKOW_2018.getId());

    //when
    int id = invoiceService.saveInvoice(INVOICE_KRAKOW_2018);

    //then
    assertNotNull(id);
    assertThat(id, is(equalTo(INVOICE_KRAKOW_2018.getId())));
    verify(databaseMock).saveInvoice(INVOICE_KRAKOW_2018);
  }

  @Test
  public void shouldGetCollectionOfInvoices() throws Exception {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_BYDGOSZCZ_2018);
    invoices.add(INVOICE_CHELMNO_2016);
    invoices.add(INVOICE_GRUDZIADZ_2017);

    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    List<Invoice> actual = invoiceService.getInvoices();

    //then
    verify(databaseMock).getInvoices();
    assertThat(actual, is(invoices));
    assertThat(actual, hasItem(INVOICE_BYDGOSZCZ_2018));
    assertThat(actual, hasItem(INVOICE_CHELMNO_2016));
    assertThat(actual, hasItem(INVOICE_GRUDZIADZ_2017));
  }

  @Test
  public void shouldUpdateInvoice() throws Exception {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_CHELMNO_2016);
    invoices.add(INVOICE_GRUDZIADZ_2017);

    int id = INVOICE_CHELMNO_2016.getId();
    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    invoiceService.updateInvoice(id, INVOICE_KRAKOW_2018);
    List<Invoice> actual = invoiceService.getInvoices();

    //then
    System.out.println(actual.toString()); //dlaczego tu sie nie zmienia na Krakow itp ten Chelmn.?
    verify(databaseMock).updateInvoice(id, INVOICE_KRAKOW_2018);
  }

  @Test
  public void shouldRemoveInvoiceById() throws Exception {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_KRAKOW_2018);
    invoices.add(INVOICE_GRUDZIADZ_2017);
    int id = INVOICE_GRUDZIADZ_2017.getId();
    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    invoiceService.removeInvoiceById(id);

    //then
    verify(databaseMock).removeInvoiceById(id);
  }

  @Test
  public void shouldReturnInvoiceById() throws IOException {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_BYDGOSZCZ_2018);
    invoices.add(INVOICE_CHELMNO_2016);
    invoices.add(INVOICE_GRUDZIADZ_2017);

    int id = INVOICE_CHELMNO_2016.getId();

    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    Optional<Invoice> actual = invoiceService.getInvoiceById(id);

    //then
    assertTrue(actual.isPresent()); // TODO what if other was returned? :) add more assertions
  }

  @Test
  public void shouldReturnInvoicesByIssueDate() throws IOException {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_KRAKOW_2018);
    invoices.add(INVOICE_BYDGOSZCZ_2018);
    invoices.add(INVOICE_GRUDZIADZ_2017);

    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    LocalDate fromDate = LocalDate.of(2018, 4, 12);
    LocalDate toDate = LocalDate.of(2018, 6, 25);
    List<Invoice> actual = invoiceService.getInvoicesByIssueDate(fromDate, toDate);

    //then
    assertThat(actual, hasItems(INVOICE_KRAKOW_2018, INVOICE_BYDGOSZCZ_2018));
  }

  @Test
  public void shouldNotReturnInvoicesWithIssueDateOutOfTheRange() throws IOException {
    //given
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(INVOICE_KRAKOW_2018);
    invoices.add(INVOICE_BYDGOSZCZ_2018);
    invoices.add(INVOICE_GRUDZIADZ_2017);

    System.out.println("??????" + INVOICE_GRUDZIADZ_2017);

    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    LocalDate fromDate = LocalDate.of(2018, 4, 12);
    LocalDate toDate = LocalDate.of(2018, 6, 25);
    List<Invoice> actual = invoiceService.getInvoicesByIssueDate(fromDate, toDate);

    //then

    System.out.println("!!!!!!!! " + actual);
    assertThat(actual, not(hasItem(INVOICE_GRUDZIADZ_2017)));
  }

  @Test
  public void shouldThrowExceptionCausedByMissingInvoiceWithProvidedIdForUpdate()
      throws IOException {
    //given
    int id = 0;
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Invoice with id: " + id + " does not exist");

    //when
    invoiceService.updateInvoice(0, INVOICE_GRUDZIADZ_2017);
  }

  @Test
  public void shouldThrowExceptionCausedByMissingInvoiceWithProvidedIdToRemove()
      throws IOException {
    //given
    int id = 0;
    expectedEx.expect(IllegalStateException.class);
    expectedEx.expectMessage("Invoice with id: " + id + " does not exist");

    //when
    invoiceService.removeInvoiceById(0);
  }

  @Test
  public void shouldThrowExceptionCausedByNullInvoice() throws IOException {
    //given
    Invoice invoice = null;
    expectedEx.expect(IllegalArgumentException.class);
    expectedEx.expectMessage("invoice cannot be null");

    //when
    invoiceService.saveInvoice(invoice);
  }
}