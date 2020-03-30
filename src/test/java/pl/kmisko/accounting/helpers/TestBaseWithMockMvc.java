package pl.kmisko.accounting.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class TestBaseWithMockMvc {

  @Autowired
  public MockMvc mockMvc;
}
