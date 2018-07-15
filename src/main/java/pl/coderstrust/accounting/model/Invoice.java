package pl.coderstrust.accounting.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

  private int id;
  private String identifier;
  private String type;
  private LocalDate issueDate;
  private LocalDate saleDate;
  private String salesPlace;
  private Company buyer;
  private Company seller;

  //Constructor JUST for test purposes
  public Invoice(int id, Company buyer) {
    this.buyer = buyer;
    this.id = id;
  }

  private List<InvoiceEntry> entries = new ArrayList<>();

  public BigDecimal getNetValue() {
    BigDecimal netValue = BigDecimal.ZERO;
    for (InvoiceEntry entry : entries) {
      netValue = netValue
          .add(entry.getNetPrice().multiply(entry.getQuantity())
              .multiply(BigDecimal.valueOf(1 - buyer.getDiscount())));
    }
    return netValue;
  }

//  public BigDecimal getDiscountValue() {
//    BigDecimal discountValue = BigDecimal.ZERO;
//    for (InvoiceEntry entry : entries) {
//      discountValue = discountValue.add(entry.getNetPrice().multiply(entry.getQuantity())
//          .subtract(new Invoice().getNetValue()));
//    }
//    return discountValue;
//  }

//  public BigDecimal getVatValue() {
//    BigDecimal vatValue = BigDecimal.ZERO;
//    for (InvoiceEntry entry : entries) {
//      vatValue = vatValue.add(new Invoice().getNetValue()
//          .multiply(BigDecimal.valueOf(entry.getVatRate().getVatRateDouble())));
//    }
//    return vatValue;
//  }

//  public BigDecimal getTotalValue() {
//    return new Invoice().getNetValue().add(new Invoice().getVatValue());
//  }

  public void addsInvoiceEntry(InvoiceEntry invoiceEntry) {
    entries.add(invoiceEntry);
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  //  @Override
  //  public String toString() {
  //    return "Invoice{"
  //        + "id="
  //        + id
  //        + ", identifier='"
  //        + identifier
  //        + '\''
  //        + ", type='"
  //        + type
  //        + '\''
  //        + ", issueDate="
  //        + issueDate
  //        + ", saleDate="
  //        + saleDate
  //        + ", salesPlace='"
  //        + salesPlace
  //        + '\''
  //        + ", buyer="
  //        + buyer
  //        + ", seller="
  //        + seller
  //        + ", entries="
  //        + entries + '}';

  public int getId() {
    return id;
  }
}
