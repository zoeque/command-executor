package zoeque.command.server.adapter;

import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zoeque.command.server.usecase.CommandExecuteService;

@RestController
@CrossOrigin(origins = "*")
public class CommandController {
  CommandExecuteService executeService;

  public CommandController(CommandExecuteService executeService) {
    this.executeService = executeService;
  }

  @GetMapping("/execute")
  public ResponseEntity execute() {
    try {
      Try<String> executionTry = executeService.execute();
      if (executionTry.isFailure()) {
        throw new RuntimeException(executionTry.getCause());
      }
      return ResponseEntity.ok().body(executionTry.get());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e);
    }
  }
}
