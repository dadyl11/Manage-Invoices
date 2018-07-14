package pl.coderstrust.accounting.database;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public abstract class DataBaseTest {

  public abstract DataBase getDatabase();

  DataBase dataBase = getDatabase();

  @Test
  public void shouldSaveInvoices() {
    //Given
    Invoice invoice = mock(Invoice.class);

    //When
    dataBase.save(invoice);

    //Then
    verify(invoice).getId();
    assertTrue(!dataBase.getInvoices().isEmpty());
  }

  @Test
  public void shouldReturnCollectionsOfInvoices() {
    //When
    Collection<Invoice> collectionOfinvoices = dataBase.getInvoices();

    //Then
    assertThat(collectionOfinvoices.getClass().getName(), is("java.util.HashMap$Values"));
  }

  @Test
  public void shouldRemoveInvoices() {
    //Given
    Invoice invoice = mock(Invoice.class);
    when(invoice.getId()).thenReturn(0);

    //When
    dataBase.save(invoice);
    dataBase.removeInvoiceById(0);

    //Then
    assertTrue(dataBase.getInvoices().isEmpty());
  }

  @Test
  public void shouldUpdateInvoice() {
    //Given
    Invoice invoice = mock(Invoice.class);
    Invoice invoice2 = mock(Invoice.class);
    when(invoice.getId()).thenReturn(0);
    when(invoice2.getId()).thenReturn(0);

    //When
    dataBase.save(invoice);
    dataBase.save(invoice2);
    dataBase.removeInvoiceById(0);

    //Then
    assertTrue(dataBase.getInvoices().isEmpty());
  }
}