package pl.coderstrust.accounting.logic;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.List;

@RequestMapping("/invoices")
@RestController
public class InvoiceController {

  private Database database;

  public InvoiceController(Database database) {
    this.database = database;
  }

  @PostMapping
  public int saveInvoice(@RequestBody Invoice invoice) {
    if (invoice == null) {
      throw new IllegalArgumentException("invoice cannot be null");
    }
    database.saveInvoice(invoice);
    return invoice.getId();
  }

  @GetMapping
  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  @GetMapping("/{id}")
  public Invoice getSingleInvoices(@PathVariable(name = "id", required = true) int id) {
    return database.getInvoices().get(id);
  }

  @PutMapping("/{id}")
  public void updateInvoice(@PathVariable(name = "id", required = true) int id,
      @RequestBody Invoice invoice) {
    invoice.setId(id);
    database.updateInvoice(id, invoice);
  }

  @DeleteMapping("/{id}")
  public void removeInvoiceById(@PathVariable(name = "id", required = true) int id) {
    database.removeInvoiceById(id);
  }
}
