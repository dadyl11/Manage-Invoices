package pl.coderstrust.accounting.controller;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


  @GetMapping("/getIncome")
  public BigDecimal getIncome(@RequestParam(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @GetMapping("/getTaxDue")
  public BigDecimal getTaxDue(@RequestParam(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @GetMapping("/getTaxIncluded")
  public BigDecimal getTaxIncluded(@RequestParam(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @GetMapping("/getCosts")
  public BigDecimal getCosts(@RequestParam(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @GetMapping("/getProfit")
  public BigDecimal getProfit(@RequestParam(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::incomeToBigDecimal, nip));
  }

  @GetMapping("/getVatPayable")
  public BigDecimal getVatPayable(@RequestParam(name = "nip", required = true) String nip)
      throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::taxToBigDecimal, nip));
  }

  public TaxCalculatorService getTaxCalculatorService() {
    return taxCalculatorService;
  }
}
