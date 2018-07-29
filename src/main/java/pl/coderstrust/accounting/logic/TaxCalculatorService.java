package pl.coderstrust.accounting.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;

import java.math.BigDecimal;

@Service
public class TaxCalculatorService {

  private InvoiceService invoiceService;
  private Company company;
  private boolean vat = false;
  private boolean buyer = false;

  @Autowired
  public TaxCalculatorService(InvoiceService invoiceService, Company company) {
    this.invoiceService = invoiceService;
    this.company = company;
  }

  public BigDecimal getValueFromInvoices() {
    return invoiceService
        .getInvoices()
        .stream()
        .filter(this::buyerOrSeller)
        .map(this::taxOrIncome)
        .reduce((sum, item) -> sum.add(item))
        .get();
  }

  public BigDecimal taxOrIncome(Invoice invoice) {
    return (vat) ? invoice.getVatValue() : invoice.getTotalNetValue();
  }

  public boolean buyerOrSeller(Invoice invoice) {
    return (buyer) ? invoice.getBuyer().equals(company) : invoice.getSeller().equals(company);
  }

  public BigDecimal getDueVat() {
    buyer = false;
    vat = true;
    return this.getValueFromInvoices();
  }

  public BigDecimal getIncludedVat() {
    buyer = true;
    vat = true;
    return this.getValueFromInvoices();
  }

  public BigDecimal getPayableVat() {
    return getDueVat().subtract(getIncludedVat());
  }

  public BigDecimal getRevenue() {
    buyer = false;
    vat = false;
    return this.getValueFromInvoices();
  }

  public BigDecimal getCosts() {
    buyer = true;
    vat = false;
    return this.getValueFromInvoices();
  }

  public BigDecimal getIncome() {
    return getRevenue().subtract(getCosts());
  }
}