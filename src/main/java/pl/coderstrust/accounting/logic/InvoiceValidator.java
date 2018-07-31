package pl.coderstrust.accounting.logic;

import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceValidator {

  public InvoiceValidator() {
  }

  public List<String> validate(Invoice invoice) {
    List<String> validationErrors = new ArrayList<>();

    if (invoice.getIdentifier() == null || invoice.getIdentifier().trim().equals("")) {
      validationErrors.add("Identifier not found");
    }

    if (invoice.getBuyer() == null) {
      validationErrors.add("Buyer not found");
    }

    if (invoice.getSeller() == null) {
      validationErrors.add("Seller not found");
    }
    return validationErrors;
  }
}
