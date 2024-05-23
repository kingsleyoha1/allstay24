package com.kingsleyohas.allstay24backoffice.domain.bus;

public interface CommandBus {
    void dispatch(Command command);
}