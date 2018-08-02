package pl.coderstrust.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.logic.TaxCalculatorService;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/taxcalculator")
@RestController
public class TaxCalculatorController {

  @Autowired
  private TaxCalculatorService taxCalculatorService;

  @Autowired
  public TaxCalculatorController(
      TaxCalculatorService taxCalculatorService) {
  }

  @PostMapping("/setCompany")
  public void setCompany(@RequestBody Company company) {
    taxCalculatorService.setCompany(company);
  }

  @GetMapping("/getIncome")
  public BigDecimal getIncome() {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::incomeToBigDecimal);
  }

  @GetMapping("/getTaxDue")
  public BigDecimal getTaxDue() {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::taxToBigDecimal);
  }

  @GetMapping("/getTaxIncluded")
  public BigDecimal getTaxIncluded() {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterBuyer,
            taxCalculatorService::taxToBigDecimal);
  }

  @GetMapping("/getCosts")
  public BigDecimal getCosts() {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterBuyer,
            taxCalculatorService::incomeToBigDecimal);
  }

  @GetMapping("/getProfit")
  public BigDecimal getProfit() {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::incomeToBigDecimal).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::filterBuyer,
                taxCalculatorService::incomeToBigDecimal));
  }

  @GetMapping("/getVatPayable")
  public BigDecimal getVatPayable() {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::filterSeller,
            taxCalculatorService::taxToBigDecimal).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::filterBuyer,
                taxCalculatorService::taxToBigDecimal));
  }


}
