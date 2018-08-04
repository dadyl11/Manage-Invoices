package pl.coderstrust.accounting.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.VatRate;

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

  public static final List<InvoiceEntry> SPAN_CLAMP = new ArrayList<>(Arrays.asList(SPAN, CLAMP));

  public static final List<InvoiceEntry> SPAN_CLAMP_SUPPORT = new ArrayList<>(
      Arrays.asList(SPAN, CLAMP, SUPPORT));

  public static final List<InvoiceEntry> EMPTY = new ArrayList<>(Arrays.asList());

  public static final List<InvoiceEntry> ONE_LINK = new ArrayList<>(Arrays.asList(LINK));

}