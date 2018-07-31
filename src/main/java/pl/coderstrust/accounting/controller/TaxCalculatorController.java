package pl.coderstrust.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.logic.TaxCalculatorService;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

@RequestMapping("/calculator")
@RestController
public class TaxCalculatorController {

  private TaxCalculatorService taxCalculatorService;

  @Autowired
  public TaxCalculatorController(
      TaxCalculatorService taxCalculatorService) {

  }
}
