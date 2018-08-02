package pl.coderstrust.accounting.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class TaxCalculatorService {

  private InvoiceService invoiceService;
  private Company company;

  @Autowired
  public TaxCalculatorService(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  public BigDecimal getValueFromInvoices(Predicate<Invoice> buyerOrSeller,
      Function<Invoice, BigDecimal> taxOrIncomeToBigDecimal) throws IllegalArgumentException {
    if (this.getCompany() == null) {
      throw new IllegalArgumentException("No company was specified!");
    } else {
      return invoiceService
          .getInvoices()
          .stream()
          .filter(buyerOrSeller)
          .map(taxOrIncomeToBigDecimal)
          .reduce((sum, item) -> sum.add(item))
          .orElse(BigDecimal.ZERO);
    }
  }

  public boolean filterBuyer(Invoice invoice) {
    return invoice.getBuyer().equals(company);
  }

  public boolean filterSeller(Invoice invoice) {
    return invoice.getSeller().equals(company);
  }

  public BigDecimal taxToBigDecimal(Invoice invoice) {
    return invoice.getVatValue();
  }

  public BigDecimal incomeToBigDecimal(Invoice invoice) {
    return invoice.getTotalNetValue();
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}