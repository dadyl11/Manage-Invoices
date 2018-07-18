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
  private List<InvoiceEntry> entries = new ArrayList<>();

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setBuyer(Company buyer) {
    this.buyer = buyer;
  }

  public Company getBuyer() {
    return buyer;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  //Constructor JUST for test purposes
  public Invoice(Company buyer) {
    this.buyer = buyer;
   // this.id = id;
  }


  public BigDecimal getNetValue() {
    BigDecimal netValue = BigDecimal.ZERO;
    for (InvoiceEntry entry : entries) {
      netValue = netValue
          .add(entry.getNetPrice().multiply(entry.getQuantity())
              .multiply(BigDecimal.valueOf(1 - buyer.getDiscount())));
    }
    return netValue;
  }

  public void addInvoiceEntry(InvoiceEntry invoiceEntry) {
    entries.add(invoiceEntry);
  }
}
