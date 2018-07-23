package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {

  private int id;
  private String identifier;
  private LocalDate issueDate;
  private LocalDate saleDate;
  private String salePlace;
  private Company buyer;
  private Company seller;
  private List<InvoiceEntry> entries = new ArrayList<>();

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(LocalDate issueDate) {
    this.issueDate = issueDate;
  }

  public LocalDate getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(LocalDate saleDate) {
    this.saleDate = saleDate;
  }

  public String getSalePlace() {
    return salePlace;
  }

  public void setSalePlace(String salePlace) {
    this.salePlace = salePlace;
  }

  public Company getSeller() {
    return seller;
  }

  public void setSeller(Company seller) {
    this.seller = seller;
  }

  public void setEntries(List<InvoiceEntry> entries) {
    this.entries = entries;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Company getBuyer() {
    return buyer;
  }

  public void setBuyer(Company buyer) {
    this.buyer = buyer;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  public void addInvoiceEntry(InvoiceEntry invoiceEntry) {
    entries.add(invoiceEntry);
  }

  public Invoice(int id, String identifier, LocalDate issueDate,
      LocalDate saleDate, String salePlace, Company buyer,
      Company seller, List<InvoiceEntry> entries) {
    this.id = id;
    this.identifier = identifier;
    this.issueDate = issueDate;
    this.saleDate = saleDate;
    this.salePlace = salePlace;
    this.buyer = buyer;
    this.seller = seller;
    this.entries = entries;
  }

  public Invoice() {
  }

  @JsonIgnore
  public BigDecimal getNetValue() {
    BigDecimal netValue = BigDecimal.ZERO;
    for (InvoiceEntry entry : entries) {
      netValue = netValue
          .add(entry.getNetPrice().multiply(entry.getQuantity())
              .multiply(BigDecimal.valueOf(1).subtract(buyer.getDiscount())));
    }
    return netValue;
  }
}