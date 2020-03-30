package pl.kmisko.accounting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;

@ApiModel(value = "InvoiceEntryModel", description = "Sample model for the Invoice Entry")
public class InvoiceEntry {

  @ApiModelProperty(value = "Description of entry", example = "Clamp")
  private String description;

  @ApiModelProperty(value = "Bigdecimal, net price", example = "10.86")
  private BigDecimal netPrice;

  @ApiModelProperty(value = "Vat rate enum", example = "0.23")
  private BigDecimal vatRate;

  @ApiModelProperty(value = "quantity", example = "10")
  private BigDecimal quantity;

  public InvoiceEntry() {
  }

  public InvoiceEntry(InvoiceEntryBuilder invoiceEntryBuilder) {
    this.description = invoiceEntryBuilder.description;
    this.netPrice = invoiceEntryBuilder.netPrice;
    this.vatRate = invoiceEntryBuilder.vatRate;
    this.quantity = invoiceEntryBuilder.quantity;
  }

  public InvoiceEntry(InvoiceEntry invoiceEntry) {
    this.description = invoiceEntry.description;
    this.netPrice = invoiceEntry.netPrice;
    this.vatRate = invoiceEntry.vatRate;
    this.quantity = invoiceEntry.quantity;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getNetPrice() {
    return netPrice;
  }

  public void setNetPrice(BigDecimal netPrice) {
    this.netPrice = netPrice;
  }

  public BigDecimal getVatRate() {
    return vatRate;
  }

  public void setVatRate(BigDecimal vatRate) {
    this.vatRate = vatRate;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    InvoiceEntry that = (InvoiceEntry) obj;

    return new EqualsBuilder()
        .append(description, that.description)
        .append(netPrice, that.netPrice)
        .append(vatRate, that.vatRate)
        .append(quantity, that.quantity)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDescription());
  }

  @JsonIgnore
  public BigDecimal getNetValue() {
    return getNetPrice().multiply(getQuantity());
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this,
        ToStringStyle.MULTI_LINE_STYLE, true, true);
  }

  public static class InvoiceEntryBuilder {

    private String description;
    private BigDecimal netPrice;
    private BigDecimal vatRate;
    private BigDecimal quantity;

    public InvoiceEntryBuilder description(String description) {
      this.description = description;
      return this;
    }

    public InvoiceEntryBuilder netPrice(BigDecimal netPrice) {
      this.netPrice = netPrice;
      return this;
    }

    public InvoiceEntryBuilder vatRate(BigDecimal vatRate) {
      this.vatRate = vatRate;
      return this;
    }

    public InvoiceEntryBuilder quantity(BigDecimal quantity) {
      this.quantity = quantity;
      return this;
    }

    public InvoiceEntry build() {
      return new InvoiceEntry(this);
    }
  }

  public static List<InvoiceEntry> deepCopyListOfEntries(List<InvoiceEntry> entries) {
    return entries.stream()
        .map(n -> new InvoiceEntry(n))
        .collect(Collectors.toList());
  }
}