package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.VatRate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

  public static final List<InvoiceEntry> SPAN_CLAMP = new ArrayList<>(List.of(SPAN, CLAMP));

  public static final List<InvoiceEntry> SUPPORT_LINK = new ArrayList<>(List.of(SPAN, CLAMP));

  public static final List<InvoiceEntry> CLAMP_LINK = new ArrayList<>(List.of(CLAMP, LINK));

  public static final List<InvoiceEntry> SUPPORT_SPAN = new ArrayList<>(List.of(SUPPORT, SPAN));

}