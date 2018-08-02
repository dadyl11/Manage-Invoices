package pl.coderstrust.accounting.controller;

import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.util.ArrayList;
import java.util.Collections;
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

    if (invoice.getIssueDate() == null || invoice.getIdentifier().trim().equals("")) {
      validationErrors.add("Issue date not found");
    }

    if (invoice.getSalePlace() == null || invoice.getSalePlace().trim().equals("")) {
      validationErrors.add("Sale place not found");
    }

    if (invoice.getBuyer().getName() == null || invoice.getBuyer().getName().equals("")) {
      validationErrors.add("Buyer name not found");
    }

    if (invoice.getBuyer().getNip() == null || invoice.getBuyer().getNip().equals("")) {
      validationErrors.add("Buyer nip not found");
    }

    if (invoice.getBuyer().getStreet() == null || invoice.getBuyer().getStreet().equals("")) {
      validationErrors.add("Buyer street not found");
    }

    if (invoice.getBuyer().getPostalCode() == null || invoice.getBuyer().getPostalCode()
        .equals("")) {
      validationErrors.add("Buyer postal code not found");
    }

    if (invoice.getBuyer().getCity() == null || invoice.getBuyer().getCity().equals("")) {
      validationErrors.add("Buyer city name not found");
    }

    if (invoice.getBuyer().getDiscount() == null) {
      validationErrors.add("Discount not found");
    }

    //seller
    if (invoice.getSeller().getName() == null || invoice.getSeller().getName().equals("")) {
      validationErrors.add("Seller name not found");
    }

    if (invoice.getSeller().getNip() == null || invoice.getSeller().getNip().equals("")) {
      validationErrors.add("Buyer nip not found");
    }

    if (invoice.getSeller().getStreet() == null || invoice.getSeller().getStreet().equals("")) {
      validationErrors.add("Buyer street not found");
    }

    if (invoice.getSeller().getPostalCode() == null || invoice.getSeller().getPostalCode()
        .equals("")) {
      validationErrors.add("Buyer postal code not found");
    }

    if (invoice.getSeller().getCity() == null || invoice.getSeller().getCity().equals("")) {
      validationErrors.add("Buyer city name not found");
    }

    if (invoice.getSeller().getDiscount() == null) {
      validationErrors.add("Discount not found");
    }

    if (invoice.getEntries().equals(Collections.emptyList())) {
      validationErrors.add("Entries not found");
    }

    for (InvoiceEntry entry : invoice.getEntries()) {
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
