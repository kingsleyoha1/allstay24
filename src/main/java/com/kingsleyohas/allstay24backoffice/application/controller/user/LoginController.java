package com.kingsleyohas.allstay24backoffice.application.controller.user;

import com.kingsleyohas.allstay24backoffice.application.action.command.user.LoginCommand;
import com.kingsleyohas.allstay24backoffice.application.dto.ApiResponse;
import com.kingsleyohas.allstay24backoffice.application.dto.response.LoginResponse;
import com.kingsleyohas.allstay24backoffice.domain.bus.CommandBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final CommandBus commandBus;

    public LoginController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody LoginCommand command) {
        try {
            commandBus.dispatch(command);

            commandBus.dispatch(command);
            LoginResponse loginResponse = command.getLoginResponse();

            return ResponseEntity.ok(ApiResponse.success(loginResponse, "Login successful!", HttpStatus.OK.value()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
