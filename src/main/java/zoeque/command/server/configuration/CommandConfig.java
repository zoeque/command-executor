package zoeque.command.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * The class to create beans.
 */
@Component
public class CommandConfig {
  @Bean
  public Runtime runtime() {
    return Runtime.getRuntime();
  }
}
