package com.kingsleyohas.allstay24backoffice.application.dto.response;

import com.kingsleyohas.allstay24backoffice.application.action.query.user.GetAuthenticatedUser;
import com.kingsleyohas.allstay24backoffice.domain.bus.Query;

public record UserInfo(String fullName) { }