package com.kingsleyohas.allstay24backoffice.domain.bus;


public interface CommandHandler<C extends Command> extends MessageHandler {
    void handle(C command);
}