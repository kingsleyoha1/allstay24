package com.kingsleyohas.allstay24backoffice.application.action.command.user;

import com.kingsleyohas.allstay24backoffice.domain.bus.CommandHandler;
import com.kingsleyohas.allstay24backoffice.domain.user.UserRepository;
import com.kingsleyohas.allstay24backoffice.domain.user.entity.Role;
import com.kingsleyohas.allstay24backoffice.domain.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class RegisterCommandHandler implements CommandHandler<RegisterCommand> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void handle(RegisterCommand command) {
        User user = new User()
                .setFullName (command.fullName())
                .setEmail (command.email())
                .setPassword(passwordEncoder.encode(command.password()))
                        .setRole(command.role() != null ? command.role() : Role.USER);

        userRepository.save(user);
    }
}
