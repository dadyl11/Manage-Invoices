package pl.coderstrust.accounting.model;

public enum VatRate {
  NORMAL(23), REDUCED8(8), REDUCED7(7), REDUCED4(4), ZERO(0);

  private double vatRate;

  VatRate(double vatRate) {
    this.vatRate = vatRate;
  }

  public double getVatRateDouble() {
    return vatRate;
  }
}
