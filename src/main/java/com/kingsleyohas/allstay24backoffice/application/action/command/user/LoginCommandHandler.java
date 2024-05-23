package com.kingsleyohas.allstay24backoffice.application.action.command.user;


import com.kingsleyohas.allstay24backoffice.application.dto.request.Login;
import com.kingsleyohas.allstay24backoffice.application.dto.response.LoginResponse;
import com.kingsleyohas.allstay24backoffice.domain.auth.JwtService;
import com.kingsleyohas.allstay24backoffice.domain.bus.CommandHandler;
import com.kingsleyohas.allstay24backoffice.domain.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import com.kingsleyohas.allstay24backoffice.domain.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class LoginCommandHandler implements CommandHandler<LoginCommand> {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginCommandHandler(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void handle(LoginCommand command) {
        Login login = new Login(command.getEmail(), command.getPassword());
        User authenticatedUser = authenticate(login);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        command.setLoginResponse(loginResponse);
    }

    private User authenticate(Login login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.email(),
                        login.password()
                )
        );

        return userRepository.findByEmail(login.email()).orElseThrow();
    }
}
