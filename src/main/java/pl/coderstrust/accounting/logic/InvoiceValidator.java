package pl.coderstrust.accounting.logic;

import static jdk.nashorn.internal.runtime.ECMAErrors.getMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;
import static pl.coderstrust.accounting.config.MessageProvider.EMPTY_IDENTIFIER;

@Service
public class InvoiceValidator {

  public InvoiceValidator(){
  }

  public List<String> validate(Invoice invoice) {
    List<String> validationErrors = new ArrayList<>();

    if (invoice.getIdentifier() == null || invoice.getIdentifier().trim().equals("")) {
      validationErrors.add(getMessage(EMPTY_IDENTIFIER));
    }

    if (invoice.getBuyer() == null) {
      validationErrors.add(getMessage("EMPTY_BUYER"));
    }

    if (invoice.getSeller() == null) {
      validationErrors.add(getMessage("EMPTY_SELLER"));
    }

    return validationErrors;
  }
}
