package pl.coderstrust.accounting.logic;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/invoices")
@RestController
public class InvoiceController {

  private InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @PostMapping
  public int saveInvoice(@RequestBody Invoice invoice) {
    invoiceService.saveInvoice(invoice);
    return invoice.getId();
  }

  @GetMapping
  public List<Invoice> getInvoices(
      @RequestParam(name = "identifierContains", required = false) String identifierQuery) {
    return invoiceService.getInvoices().stream()
        .filter(invoice -> invoice.getIdentifier().contains(identifierQuery))
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public Invoice getSingleInvoice(@PathVariable(name = "id", required = true) int id) {
    return invoiceService.getInvoiceById(id);
  }

  @PutMapping("/{id}")
  public void updateInvoice(@PathVariable(name = "id", required = true) int id,
      @RequestBody Invoice invoice) {
    invoice.setId(id);
    invoiceService.updateInvoice(id, invoice);
  }

  @DeleteMapping("/{id}")
  public void removeInvoiceById(@PathVariable(name = "id", required = true) int id) {
    invoiceService.removeInvoiceById(id);
  }
}
