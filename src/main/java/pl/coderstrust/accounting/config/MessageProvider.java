package pl.coderstrust.accounting.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageProvider {

  public static final String EMPTY_IDENTIFIER = "invoiceValidator.emptyIdentifier";

}
