package pl.coderstrust.accounting.database.impl.file.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;

public class InvoiceConverter {

  private ObjectMapper mapper = new ObjectMapper();

  public String writeJson(Invoice invoice) throws IOException {
    return mapper.writeValueAsString(invoice);
  }

  public Invoice readJson(String json) throws IOException {
    return mapper.readValue(json, Invoice.class);
  }
}
