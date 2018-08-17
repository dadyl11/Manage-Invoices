package pl.coderstrust.accounting.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.VatRate;

// TODO use Builder instead of constructor - with so many argument it's hard to know what is what.
public class InvoiceEntryProvider {

  public static final InvoiceEntry SPAN = new InvoiceEntry(
      "span",
      BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_HALF_UP),
      VatRate.NORMAL,
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry CLAMP = new InvoiceEntry(
      "clamp",
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP),
      VatRate.NORMAL,
      BigDecimal.valueOf(2).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry SUPPORT = new InvoiceEntry(
      "support",
      BigDecimal.valueOf(11).setScale(2, BigDecimal.ROUND_HALF_UP),
      VatRate.REDUCED_4,
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry LINK = new InvoiceEntry(
      "link",
      BigDecimal.valueOf(13).setScale(2, BigDecimal.ROUND_HALF_UP),
      VatRate.ZERO,
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry BLANK_DESCRIPTION = new InvoiceEntry(
      "",
      BigDecimal.valueOf(13).setScale(2, BigDecimal.ROUND_HALF_UP),
      VatRate.ZERO,
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry NULL_NET_PRICE = new InvoiceEntry(
      "link",
      null,
      VatRate.ZERO,
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry NULL_VAT_RATE = new InvoiceEntry(
      "link",
      BigDecimal.valueOf(13).setScale(2, BigDecimal.ROUND_HALF_UP),
      null,
      BigDecimal.valueOf(6).setScale(2, BigDecimal.ROUND_HALF_UP));

  public static final InvoiceEntry NULL_QUANTITY = new InvoiceEntry(
      "link",
      BigDecimal.valueOf(13).setScale(2, BigDecimal.ROUND_HALF_UP),
      VatRate.ZERO,
      null);

  public static final List<InvoiceEntry> SPAN_CLAMP = new ArrayList<>(Arrays.asList(SPAN, CLAMP));

  public static final List<InvoiceEntry> SPAN_CLAMP_SUPPORT = new ArrayList<>(Arrays.asList(SPAN, CLAMP, SUPPORT));

  public static final List<InvoiceEntry> EMPTY = new ArrayList<>(Arrays.asList());

  public static final List<InvoiceEntry> ONE_LINK = new ArrayList<>(Arrays.asList(LINK));

  public static final List<InvoiceEntry> EMPTY_QUANTITY = new ArrayList<>(Arrays.asList(NULL_QUANTITY));

  public static final List<InvoiceEntry> EMPTY_DESCRIPTION = new ArrayList<>(Arrays.asList(BLANK_DESCRIPTION));

  public static final List<InvoiceEntry> EMPTY_NET_PRICE = new ArrayList<>(Arrays.asList(NULL_NET_PRICE));

  public static final List<InvoiceEntry> EMPTY_VAT_RATE = new ArrayList<>(Arrays.asList(NULL_VAT_RATE));


}