package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

  //TODO - add setters and getters, when we're going to need them - will need tests to pass jacoco
  public void setBuyer(Company buyer) {
    this.buyer = buyer;
  }

  public Company getBuyer() {
    return buyer;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  @JsonIgnoreProperties
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

