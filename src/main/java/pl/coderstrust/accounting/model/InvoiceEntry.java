package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public class InvoiceEntry {

  private String description;
  private BigDecimal netPrice;
  private BigDecimal vatRate;
  private BigDecimal quantity;

  public BigDecimal getQuantity() {
    return quantity;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getVatRate() {
    return vatRate;
  }

  public BigDecimal getNetPrice() {
    return netPrice;
  }
}
