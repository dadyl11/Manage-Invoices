package pl.coderstrust.accounting.logic;

import static jdk.nashorn.internal.runtime.ECMAErrors.getMessage;

import org.springframework.stereotype.Component;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceValidator {

  public List<String> validate(Invoice invoice) {
    List<String> validationErrors = new ArrayList<>();

    if (invoice.getIdentifier() == null || invoice.getIdentifier().trim().equals("")) {
      validationErrors.add(getMessage("Empty identifier"));
    }

    if (invoice.getBuyer() == null) {
      validationErrors.add(getMessage("Empty buyer field"));
    }

    if (invoice.getSeller() == null) {
      validationErrors.add(getMessage("Empty seller field"));
    }

    return validationErrors;
  }
}
