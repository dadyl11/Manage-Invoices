package pl.coderstrust.accounting.database.impl.file.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.Invoice;

@Service
public class InvoiceConverter {

  private ObjectMapper mapper;

  @Autowired
  public InvoiceConverter() {
    this.mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public String writeJson(List<Invoice> list) throws IOException {
    return mapper.writeValueAsString(list);
  }

  public List<Invoice> readJson(String json) throws IOException {
    try {
      return mapper.readValue(json, new TypeReference<List<Invoice>>() {
      });
    } catch (MismatchedInputException exception) {
      return new ArrayList<>();
    }
  }
}
