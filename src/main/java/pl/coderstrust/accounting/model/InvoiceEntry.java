package pl.coderstrust.accounting.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

  private String description;
  private BigDecimal netPrice;
  private VatRate vatRate;
  private BigDecimal quantity;

  public void setDescription(String description) {
    this.description = description;
  }

  public void setNetPrice(BigDecimal netPrice) {
    this.netPrice = netPrice;
  }

  public void setVatRate(VatRate vatRate) {
    this.vatRate = vatRate;
  }

  public void setQuantity(BigDecimal quantity) {
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
