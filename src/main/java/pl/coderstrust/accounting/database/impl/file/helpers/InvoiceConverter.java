package pl.coderstrust.accounting.database.impl.file.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Invoice;

@Service
public class InvoiceConverter {

  private ObjectMapper mapper;

  @Autowired
  public InvoiceConverter(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public String writeJson(Invoice invoice) throws IOException {
    return mapper.writeValueAsString(invoice);
  }

  public Invoice readJson(String json) throws IOException {
    return mapper.readValue(json, Invoice.class);
  }
}
