package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public enum VatRate {
  NORMAL(BigDecimal.valueOf(23)), REDUCED8(BigDecimal.valueOf(8)), REDUCED7(
      BigDecimal.valueOf(7)), REDUCED4(BigDecimal.valueOf(4)), ZERO(BigDecimal.ZERO);

  private BigDecimal vatRate;

  VatRate(BigDecimal vatRate) {
    this.vatRate = vatRate;
  }

  public BigDecimal getVatRate() {
    return vatRate;
  }
}
