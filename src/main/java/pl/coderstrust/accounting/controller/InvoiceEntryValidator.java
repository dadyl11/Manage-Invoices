package pl.coderstrust.accounting.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.InvoiceEntry;

@Service
public class InvoiceEntryValidator {

  List<String> validate(List<InvoiceEntry> entries) {
    List<String> validationErrors = new ArrayList<>();
    if (entries.isEmpty()) {
      validationErrors.add("Entries not found");
    }
    for (InvoiceEntry entry : entries) {
      if (entry.getDescription() == null || entry.getDescription().equals("")) {
        validationErrors.add("Entry description not found");
      }
      if (entry.getNetPrice() == null) {
        validationErrors.add("Net price for entry not found");
      }
      if (entry.getQuantity() == null) {
        validationErrors.add("Quantity for entry not found");
      }
      if (entry.getVatRate() == null) {
        validationErrors.add("Vat rate for entry not found");
      }
    }
    return validationErrors;
  }
}
