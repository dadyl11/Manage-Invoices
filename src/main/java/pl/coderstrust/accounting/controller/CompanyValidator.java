package pl.coderstrust.accounting.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Company;

@Service
public class CompanyValidator {

  private NipValidator nipValidator;

  @Autowired
  public CompanyValidator(NipValidator nipValidator) {
    this.nipValidator = nipValidator;
  }

  private boolean checkIfStringIsNullOrEmpty(String string) {
    return string == null || ("").equals(string);
  }

  List<String> validate(Company company, String buyerOrSeller) {
    List<String> validationErrors = new ArrayList<>();
    if (checkIfStringIsNullOrEmpty(company.getName())) {
      validationErrors.add(buyerOrSeller + " name not found");
    }

    if (checkIfStringIsNullOrEmpty(company.getNip())) {
      validationErrors.add(buyerOrSeller + " nip not found");
    }

    if (!nipValidator.isValid(company.getNip())) {
      validationErrors.add(buyerOrSeller + " NIP is not according to pattern");
    }

    if (checkIfStringIsNullOrEmpty(company.getStreet())) {
      validationErrors.add(buyerOrSeller + " street not found");
    }

    if (checkIfStringIsNullOrEmpty(company.getPostalCode())) {
      validationErrors.add(buyerOrSeller + " postal code not found");
    }

    if (checkIfStringIsNullOrEmpty(company.getCity())) {
      validationErrors.add(buyerOrSeller + " city not found");
    }

    if (company.getDiscount().compareTo(BigDecimal.ONE) > 0) {
      validationErrors.add(buyerOrSeller + " - Bad value of discount");
    }
    return validationErrors;
  }
}

