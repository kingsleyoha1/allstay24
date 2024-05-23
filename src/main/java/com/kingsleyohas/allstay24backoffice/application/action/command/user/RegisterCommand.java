package com.kingsleyohas.allstay24backoffice.application.action.command.user;

import com.kingsleyohas.allstay24backoffice.domain.bus.Command;
import com.kingsleyohas.allstay24backoffice.domain.user.entity.Role;

public record RegisterCommand(String fullName,
                              String email,
                              String password,
                              Role role
                             ) implements Command {
}