package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public class InvoiceEntry {

  private String description;
  private BigDecimal netPrice;
  private VatRate vatRate;
  private BigDecimal quantity;

  public InvoiceEntry(String description, BigDecimal netPrice,
      VatRate vatrate, BigDecimal quantity) {
    this.description = description;
    this.netPrice = netPrice;
    this.vatRate = vatrate;
    this.quantity = quantity;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public String getDescription() {
    return description;
  }

  public VatRate getVatRate() {
    return vatRate;
  }

  public BigDecimal getNetPrice() {
    return netPrice;
  }
}
