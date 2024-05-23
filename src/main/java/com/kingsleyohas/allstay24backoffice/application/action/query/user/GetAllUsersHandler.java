package com.kingsleyohas.allstay24backoffice.application.action.query.user;

import com.kingsleyohas.allstay24backoffice.domain.bus.QueryHandler;
import com.kingsleyohas.allstay24backoffice.application.dto.response.UserInfo;
import com.kingsleyohas.allstay24backoffice.domain.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class GetAllUsersHandler implements QueryHandler<List<UserInfo>,GetAllUsers> {
    private final UserRepository userRepository;

    public GetAllUsersHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserInfo> handle(GetAllUsers query) {
        return userRepository.findAll().stream()
                .map(user -> new UserInfo(user.getFullName()))
                .collect(Collectors.toList());
    }
}