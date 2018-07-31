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
  public TaxCalculatorService(InvoiceService invoiceService, Company company) {
    this.invoiceService = invoiceService;
    this.company = company;
  }

  public BigDecimal getValueFromInvoices(Predicate<Invoice> buyerOrSeller,
      Function<Invoice, BigDecimal> taxOrIncomeToBigDecimal) {
    return invoiceService
        .getInvoices()
        .stream()
        .filter(buyerOrSeller)
        .map(taxOrIncomeToBigDecimal)
        .reduce(BigDecimal.ZERO, (sum, item) -> sum.add(item));
  }


  public boolean predicateBuyer(Invoice invoice) {
    return invoice.getBuyer().equals(company);
  }

  public boolean predicateSeller(Invoice invoice) {
    return invoice.getSeller().equals(company);
  }

  public BigDecimal functionTax(Invoice invoice) {
    return invoice.getVatValue();
  }

  public BigDecimal functionIncome(Invoice invoice) {
    return invoice.getTotalNetValue();
  }
}

