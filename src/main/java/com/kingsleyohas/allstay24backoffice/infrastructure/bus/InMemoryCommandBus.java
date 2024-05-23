package com.kingsleyohas.allstay24backoffice.infrastructure.bus;

import com.kingsleyohas.allstay24backoffice.domain.bus.Command;
import com.kingsleyohas.allstay24backoffice.domain.bus.CommandBus;
import com.kingsleyohas.allstay24backoffice.domain.bus.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InMemoryCommandBus implements CommandBus {
    private final List<CommandHandler<? extends Command>> handlers;

    @Autowired
    public InMemoryCommandBus(List<CommandHandler<? extends Command>> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void dispatch(Command command) {
        Optional<CommandHandler<? extends Command>> handler = handlers.stream()
                .filter(h -> h.getClass().isAssignableFrom(getHandlerClass(command.getClass())))
                .findFirst();

        handler.ifPresentOrElse(
                h -> {
                    try {
                        @SuppressWarnings("unchecked")
                        CommandHandler<Command> typedHandler = (CommandHandler<Command>) h;
                        typedHandler.handle(command);
                    } catch (ClassCastException e) {
                        throw new RuntimeException("Failed to cast handler for command: " + command.getClass().getName(), e);

                    }
                },
                () -> {
                    throw new RuntimeException("Handler not found for the message \"" + command.getClass().getName() + "\"");
                }
        );
    }

    private static <T extends Command> Class<? extends CommandHandler<T>> getHandlerClass(Class<T> commandClass) {
        try {
            @SuppressWarnings("unchecked")
            Class<? extends CommandHandler<T>> handlerClass = (Class<? extends CommandHandler<T>>) Class.forName(commandClass.getName() + "Handler");
            return handlerClass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Handler class not found for the message \"" + commandClass.getName() + "\"", e);
        }
    }
}