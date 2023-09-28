package zoeque.command.server.usecase;

import io.micrometer.common.util.StringUtils;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The service class use command runner and execute binary files.
 */
@Service
public class CommandExecuteService {
  String commandLine;
  Runtime runtime;

  public CommandExecuteService(@Value("${zoeque.command}")
                               String commandLine,
                               Runtime runtime) {
    this.commandLine = commandLine;
    this.runtime = runtime;
  }

  /**
   * The command execution method.
   * The command must be declared in application.properties.
   *
   * @return The success with message or the failure with an exception.
   */
  public Try<String> execute() {
    try {
      if (StringUtils.isEmpty(commandLine)) {
        throw new IllegalArgumentException("Command is not defined!!");
      }
      Process process = runtime.exec(commandLine);
      return Try.success("Command is executed!!");
    } catch (Exception e) {
      return Try.failure(e);
    }
  }
}
