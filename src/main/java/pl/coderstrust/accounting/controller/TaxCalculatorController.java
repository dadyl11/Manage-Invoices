package pl.coderstrust.accounting.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.logic.TaxCalculatorService;

@Api(value = "/taxcalculator", description = "Operations regarding tax calculation")
@RequestMapping("/taxcalculator")
@RestController
public class TaxCalculatorController {

  @Autowired
  private TaxCalculatorService taxCalculatorService;

  @Autowired
  public TaxCalculatorController(TaxCalculatorService taxCalculatorService) {
  }

  @ApiOperation(value = "Gets income by company",
      notes = "Returns invoice value where company specified is the seller",
      response = BigDecimal.class,
      responseContainer = "")
  @GetMapping("/Income/{nip}")
  public BigDecimal getIncome(@PathVariable(name = "nip", required = true) String nip) throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @ApiOperation(value = "Gets tax due",
      notes = "Tax due is the tax where company specified is the seller",
      response = BigDecimal.class,
      responseContainer = "")
  @GetMapping("/TaxDue/{nip}")
  public BigDecimal getTaxDue(@PathVariable(name = "nip", required = true) String nip) throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @ApiOperation(value = "Gets tax included",
      notes = "Tax due is the tax where company specified is the buyer",
      response = BigDecimal.class,
      responseContainer = "")
  @GetMapping("/TaxIncluded/{nip}")
  public BigDecimal getTaxIncluded(@PathVariable(name = "nip", required = true) String nip) throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::taxToBigDecimal, nip);
  }

  @ApiOperation(value = "Gets costs by company",
      notes = "Returns invoice value where company specified is the buyer",
      response = BigDecimal.class,
      responseContainer = "")
  @GetMapping("/Costs/{nip}")
  public BigDecimal getCosts(@PathVariable(name = "nip", required = true) String nip) throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
            taxCalculatorService::incomeToBigDecimal, nip);
  }

  @ApiOperation(value = "Gets profit = income - costs",
      notes = "Substracts Costs from Income for company specified",
      response = BigDecimal.class,
      responseContainer = "")
  @GetMapping("/Profit/{nip}")
  public BigDecimal getProfit(@PathVariable(name = "nip", required = true) String nip) throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::incomeToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::incomeToBigDecimal, nip));
  }

  @ApiOperation(value = "Gets tax payable",
      notes = "Substracts tax included from tax due for specified company",
      response = BigDecimal.class,
      responseContainer = "")
  @GetMapping("/VatPayable/{nip}")
  public BigDecimal getVatPayable(@PathVariable(name = "nip", required = true) String nip) throws IOException {
    return taxCalculatorService
        .getValueFromInvoices(taxCalculatorService::biFilterSeller,
            taxCalculatorService::taxToBigDecimal, nip).subtract(taxCalculatorService
            .getValueFromInvoices(taxCalculatorService::biFilterBuyer,
                taxCalculatorService::taxToBigDecimal, nip));
  }
}