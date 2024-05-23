package com.kingsleyohas.allstay24backoffice.application.action.command.user;

import com.kingsleyohas.allstay24backoffice.application.dto.response.LoginResponse;
import com.kingsleyohas.allstay24backoffice.domain.bus.Command;


public class LoginCommand implements Command {
    private final String email;
    private final String password;
    private LoginResponse loginResponse;

    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }
}
