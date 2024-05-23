package com.kingsleyohas.allstay24backoffice.application.controller.user;


import com.kingsleyohas.allstay24backoffice.application.action.command.user.RegisterCommand;
import com.kingsleyohas.allstay24backoffice.application.dto.ApiResponse;
import com.kingsleyohas.allstay24backoffice.domain.bus.CommandBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final CommandBus commandBus;

    public RegisterController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> createUser(@RequestBody RegisterCommand command) {
        try {
            commandBus.dispatch(command);
            return ResponseEntity.ok(ApiResponse
                    .success(null, "User created successfully", HttpStatus.OK.value() ));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}