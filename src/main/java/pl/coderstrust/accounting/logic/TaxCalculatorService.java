package pl.coderstrust.accounting.logic;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.BiPredicate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.controller.NipValidator;
import pl.coderstrust.accounting.model.Invoice;

@Service
public class TaxCalculatorService {


  private InvoiceService invoiceService;
  private NipValidator nipValidator;

  @Autowired
  public TaxCalculatorService(InvoiceService invoiceService,
      NipValidator nipValidator) {
    this.invoiceService = invoiceService;
    this.nipValidator = nipValidator;
  }

  public BigDecimal getValueFromInvoices(BiPredicate<Invoice, String> buyerOrSeller,
      Function<Invoice, BigDecimal> taxOrIncomeToBigDecimal, String nip)
      throws IllegalArgumentException, IOException {
    if (!nipValidator.isValid(nip)) {
      throw new IllegalArgumentException("Nip does not match specified pattern");
    }
    return invoiceService
        .getInvoices()
        .stream()
        .filter(invoice -> buyerOrSeller.test(invoice, nip))
        .map(taxOrIncomeToBigDecimal)
        .reduce((sum, item) -> sum.add(item))
        .orElse(BigDecimal.ZERO);
  }

  public boolean biFilterBuyer(Invoice invoice, String nip) {
    return invoice.getBuyer().getNip().equals(nip);
  }

  public boolean biFilterSeller(Invoice invoice, String nip) {
    return invoice.getSeller().getNip().equals(nip);
  }

  public BigDecimal taxToBigDecimal(Invoice invoice) {
    return invoice.getVatValue();
  }

  public BigDecimal incomeToBigDecimal(Invoice invoice) {
    return invoice.getTotalNetValue();
  }

}