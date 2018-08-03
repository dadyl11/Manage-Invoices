package pl.coderstrust.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


  @GetMapping("/getIncome")
  public BigDecimal getIncome(@RequestParam(name = "nip", required = true) String nip) {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @GetMapping("/getTaxDue")
  public BigDecimal getTaxDue(@RequestParam(name = "nip", required = true) String nip) {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @GetMapping("/getTaxIncluded")
  public BigDecimal getTaxIncluded(@RequestParam(name = "nip", required = true) String nip) {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @GetMapping("/getCosts")
  public BigDecimal getCosts(@RequestParam(name = "nip", required = true) String nip) {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @GetMapping("/getProfit")
  public BigDecimal getProfit(@RequestParam(name = "nip", required = true) String nip) {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::incomeToBigDecimal, nip));
  }

  @GetMapping("/getVatPayable")
  public BigDecimal getVatPayable(@RequestParam(name = "nip", required = true) String nip) {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::taxToBigDecimal, nip));
  }
}
