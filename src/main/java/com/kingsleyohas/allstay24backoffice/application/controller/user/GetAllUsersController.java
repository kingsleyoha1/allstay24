package com.kingsleyohas.allstay24backoffice.application.controller.user;

import com.kingsleyohas.allstay24backoffice.application.action.query.user.GetAllUsers;
import com.kingsleyohas.allstay24backoffice.application.dto.ApiResponse;
import com.kingsleyohas.allstay24backoffice.application.dto.response.UserInfo;
import com.kingsleyohas.allstay24backoffice.domain.bus.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class GetAllUsersController {

    private final QueryBus queryBus;

    public GetAllUsersController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserInfo>>> allUsers() {
        List<UserInfo> users = queryBus.ask(new GetAllUsers());
        return ResponseEntity.ok(ApiResponse.success(users, "All users fetched successfully", HttpStatus.OK.value()));
    }
}
