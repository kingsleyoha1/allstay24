package com.kingsleyohas.allstay24backoffice.config;

import com.kingsleyohas.allstay24backoffice.domain.bus.CommandBus;
import com.kingsleyohas.allstay24backoffice.domain.bus.CommandHandler;
import com.kingsleyohas.allstay24backoffice.domain.bus.QueryBus;
import com.kingsleyohas.allstay24backoffice.domain.bus.QueryHandler;
import com.kingsleyohas.allstay24backoffice.domain.user.UserRepository;
import com.kingsleyohas.allstay24backoffice.infrastructure.bus.InMemoryCommandBus;
import com.kingsleyohas.allstay24backoffice.infrastructure.bus.InMemoryQueryBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public CommandBus commandBus(List<CommandHandler<?>> handlers) {
        return new InMemoryCommandBus(handlers);
    }

    @Bean
    public QueryBus queryBus(List<QueryHandler<?, ?>> handlers) {
        return new InMemoryQueryBus(handlers);
    }
}