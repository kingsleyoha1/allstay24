package com.kingsleyohas.allstay24backoffice.application.action.query.user;

import com.kingsleyohas.allstay24backoffice.domain.bus.QueryHandler;
import com.kingsleyohas.allstay24backoffice.application.dto.response.UserInfo;
import com.kingsleyohas.allstay24backoffice.domain.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class GetAuthenticatedUserHandler implements QueryHandler<UserInfo, GetAuthenticatedUser > {
    @Override
    public UserInfo handle(GetAuthenticatedUser query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return new UserInfo(currentUser.getFullName());
    }
}