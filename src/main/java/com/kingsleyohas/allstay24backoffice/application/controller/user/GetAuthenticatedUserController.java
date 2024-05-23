package com.kingsleyohas.allstay24backoffice.application.controller.user;


import com.kingsleyohas.allstay24backoffice.application.action.query.user.GetAuthenticatedUser;
import com.kingsleyohas.allstay24backoffice.application.dto.ApiResponse;
import com.kingsleyohas.allstay24backoffice.application.dto.response.UserInfo;
import com.kingsleyohas.allstay24backoffice.domain.bus.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class GetAuthenticatedUserController {

  private final QueryBus queryBus;

  public GetAuthenticatedUserController(QueryBus queryBus) {
    this.queryBus = queryBus;
  }

  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserInfo>> authenticatedUser() {
    UserInfo userInfo = queryBus.ask(new GetAuthenticatedUser());
    return ResponseEntity.ok(ApiResponse.success(userInfo, "Authenticated user fetched successfully", HttpStatus.OK.value()));
  }
}