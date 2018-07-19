package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public enum VatRate {
  NORMAL(BigDecimal.valueOf(23)),
  REDUCED_8(BigDecimal.valueOf(8)),
  REDUCED_7(BigDecimal.valueOf(7)),
  REDUCED_4(BigDecimal.valueOf(4)),
  ZERO(BigDecimal.ZERO);

  private BigDecimal vatRate;

  private VatRate(BigDecimal vatRate) {
    this.vatRate = vatRate;
  }

  public BigDecimal getVatRate() {
    return vatRate;
  }
}
