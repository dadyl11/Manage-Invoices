package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.VatRate;

import java.math.BigDecimal;

public class InvoiceEntryProvider {

  public static final InvoiceEntry SPAN = new InvoiceEntry(
      "span",
      BigDecimal.valueOf(10),
      VatRate.NORMAL,
      BigDecimal.valueOf(6));

  public static final InvoiceEntry CLAMP = new InvoiceEntry(
      "clamp",
      BigDecimal.valueOf(6),
      VatRate.NORMAL,
      BigDecimal.valueOf(2));

  public static final InvoiceEntry SUPPORT = new InvoiceEntry(
      "support",
      BigDecimal.valueOf(11),
      VatRate.REDUCED_4,
      BigDecimal.valueOf(6));

  public static final InvoiceEntry LINK = new InvoiceEntry(
      "link",
      BigDecimal.valueOf(13),
      VatRate.ZERO,
      BigDecimal.valueOf(6));
}