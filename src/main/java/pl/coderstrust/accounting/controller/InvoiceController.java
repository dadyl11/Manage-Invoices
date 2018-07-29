package pl.coderstrust.accounting.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.logic.InvoiceService;
import pl.coderstrust.accounting.logic.InvoiceValidator;
import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/invoices")
@RestController
public class InvoiceController {

  @ModelAttribute
  LocalDate startDate() {
    return LocalDate.now();
  }

  private InvoiceValidator invoiceValidator;
  private InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService, InvoiceValidator invoiceValidator) {
    this.invoiceService = invoiceService;
    this.invoiceValidator = invoiceValidator;
  }

  @PostMapping
  public ResponseEntity<?> saveInvoice(@RequestBody Invoice invoice) {
    List<String> validationResult = invoiceValidator.validate(invoice);
    if (!validationResult.isEmpty()) {
      return ResponseEntity.badRequest().body(validationResult);
    }
    invoiceService.saveInvoice(invoice);
    return ResponseEntity.ok(invoice.getId());
  }

  @GetMapping
  public List<Invoice> getInvoices() {
    return invoiceService.getInvoices();
  }

  @GetMapping("/dates")
  public List<Invoice> getInvoicesByIssueDateRange(
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
      @RequestParam @ModelAttribute LocalDate startDate, LocalDate endDate) {
    return invoiceService.getInvoicesByIssueDate(startDate, endDate);
  }

  @GetMapping("/{id}")
  public Invoice getSingleInvoice(@PathVariable(name = "id", required = true) int id) {
    return invoiceService.getInvoiceById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateInvoice(@PathVariable(name = "id", required = true) int id,
      @RequestBody Invoice invoice) {
    List<String> validationResult = invoiceValidator.validate(invoice);
    if (!validationResult.isEmpty()) {
      return ResponseEntity.badRequest().body(validationResult);
    }
    invoiceService.updateInvoice(id, invoice);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public void removeInvoiceById(@PathVariable(name = "id", required = true) int id) {
    invoiceService.removeInvoiceById(id);
  }
}
