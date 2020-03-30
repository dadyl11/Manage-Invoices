package pl.kmisko.accounting.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.kmisko.accounting.model.InvoiceEntry;
import pl.kmisko.accounting.model.VatRate;

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

      boolean isVatRateAccordingToVatRateEnum = Arrays.stream(VatRate.values())
          .anyMatch(vatRate -> vatRate.getVatRateValue().equals(entry.getVatRate()));
      if (!isVatRateAccordingToVatRateEnum) {
        validationErrors.add("Vat rate is not in accordance to current tariffs");
      }
    }
    return validationErrors;
  }
}
