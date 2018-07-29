package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public enum VatRate {
  NORMAL(BigDecimal.valueOf(0.23)),
  REDUCED_8(BigDecimal.valueOf(0.8)),
  REDUCED_7(BigDecimal.valueOf(0.7)),
  REDUCED_4(BigDecimal.valueOf(0.4)),
  ZERO(BigDecimal.ZERO);

  private BigDecimal vatRate;

  private VatRate(BigDecimal vatRate) {
    this.vatRate = vatRate;
  }

  public BigDecimal getVatRateValue() {
    return vatRate;
  }
}
