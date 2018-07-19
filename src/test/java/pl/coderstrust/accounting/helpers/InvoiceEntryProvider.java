package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.VatRate;

import java.math.BigDecimal;

public class InvoiceEntryProvider {

  public static final InvoiceEntry InvoiceEntry1 = new InvoiceEntry(
      "przesla",
      BigDecimal.valueOf(10),
      VatRate.NORMAL,
      BigDecimal.valueOf(6));

  public static final InvoiceEntry InvoiceEntry2 = new InvoiceEntry(
      "obejmy",
      BigDecimal.valueOf(6),
      VatRate.NORMAL,
      BigDecimal.valueOf(2));

  public static final InvoiceEntry InvoiceEntry3 = new InvoiceEntry(
      "podpory",
      BigDecimal.valueOf(11),
      VatRate.REDUCED_4,
      BigDecimal.valueOf(6));

  public static final InvoiceEntry InvoiceEntry44 = new InvoiceEntry(
      "spojenia",
      BigDecimal.valueOf(13),
      VatRate.ZERO,
      BigDecimal.valueOf(6));
}