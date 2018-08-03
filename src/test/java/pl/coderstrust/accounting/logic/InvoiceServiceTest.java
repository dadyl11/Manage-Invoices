package pl.coderstrust.accounting.logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

  @Mock
  private Database databaseMock;

  @InjectMocks
  private InvoiceService invoiceService;

  @Test
  public void shouldSaveInvoice() throws Exception {
    //given

    //when
    invoiceService.saveInvoice(INVOICE_KRAKOW_2018);

    //then
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
    invoices.add(INVOICE_BYDGOSZCZ_2018);
    invoices.add(INVOICE_CHELMNO_2016);
    invoices.add(INVOICE_GRUDZIADZ_2017);
    int id = 3;
    when(databaseMock.getInvoices()).thenReturn(invoices);
<<<<<<<HEAD
        =======

>>>>>>>61 b88c8...made taxcontroller stateless & used ReflectiveStringbuilder @some minor changes
    //when
    invoiceService.getInvoiceById(id);
    invoiceService.updateInvoice(id, INVOICE_KRAKOW_2018);

    //then
    verify(databaseMock).updateInvoice(id, INVOICE_KRAKOW_2018);
  }

  @Test
  public void shouldRemoveInvoiceById() throws Exception {
    //given
    int id = 0;

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
    int id = 3;
    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    Optional<Invoice> expected = invoiceService.getInvoiceById(id);
    Optional<Invoice> actual = Optional.ofNullable(invoices.get(1));

    //then
    assertThat(actual, is(expected));
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
    List<Invoice> actual = invoiceService
        .getInvoicesByIssueDate(LocalDate.of(2018, 04, 12), LocalDate.of(2018, 06, 25));

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
    when(databaseMock.getInvoices()).thenReturn(invoices);

    //when
    List<Invoice> actual = invoiceService
        .getInvoicesByIssueDate(LocalDate.of(2018, 04, 12), LocalDate.of(2018, 06, 25));

    //then
    assertThat(actual, not(hasItem(INVOICE_GRUDZIADZ_2017)));
  }
}