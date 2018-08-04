package pl.coderstrust.accounting.database.impl.file.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.List;
import pl.coderstrust.accounting.model.Invoice;

public class InvoiceConverter {

  private ObjectMapper mapper;

  public InvoiceConverter() {
    this.mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public String writeJson(List<Invoice> list) throws IOException {
    return mapper.writeValueAsString(list);
  }

  public List<Invoice> readJson(String json) throws IOException {
    return mapper.readValue(json, new TypeReference<List<Invoice>>() {
    });
  }
}
