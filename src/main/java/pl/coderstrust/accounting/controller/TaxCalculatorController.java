package pl.coderstrust.accounting.controller;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.logic.TaxCalculatorService;

@RequestMapping("/taxcalculator")
@RestController
public class TaxCalculatorController {

  @Autowired
  private TaxCalculatorService taxCalculatorService;

  @Autowired
  public TaxCalculatorController(
      TaxCalculatorService taxCalculatorService) {
  }


  @GetMapping("/getIncome/{nip}")
  public BigDecimal getIncome(@PathVariable(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @GetMapping("/getTaxDue/{nip}")
  public BigDecimal getTaxDue(@PathVariable(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @GetMapping("/getTaxIncluded/{nip}")
  public BigDecimal getTaxIncluded(@PathVariable(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @GetMapping("/getCosts/{nip}")
  public BigDecimal getCosts(@PathVariable(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @GetMapping("/getProfit/{nip}")
  public BigDecimal getProfit(@PathVariable(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::incomeToBigDecimal, nip));
  }

  @GetMapping("/getVatPayable/{nip}")
  public BigDecimal getVatPayable(@PathVariable(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::taxToBigDecimal, nip));
  }
}