package com.kingsleyohas.allstay24backoffice.infrastructure.bus;

import com.kingsleyohas.allstay24backoffice.domain.bus.Query;
import com.kingsleyohas.allstay24backoffice.domain.bus.QueryBus;
import com.kingsleyohas.allstay24backoffice.domain.bus.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InMemoryQueryBus implements QueryBus {
    private final List<QueryHandler<?, ? extends Query<?>>> handlers;

    @Autowired
    public InMemoryQueryBus(List<QueryHandler<?, ? extends Query<?>>> handlers) {
        this.handlers = handlers;
    }

    @Override
    public <T> T ask(Query<T> query) {
        Optional<QueryHandler<?, ? extends Query<?>>> handler = handlers.stream()
                .filter(h -> h.getClass().isAssignableFrom(getHandlerClass(query)))
                .findFirst();

        return handler.map(h -> {
            try {
                @SuppressWarnings("unchecked")
                QueryHandler<T, Query<T>> typedHandler = (QueryHandler<T, Query<T>>) h;
                return typedHandler.handle(query);
            } catch (ClassCastException e) {
                throw new RuntimeException("Failed to cast handler for query: " + query.getClass().getName(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Handler not found for the message \""
                + query.getClass().getName() + "\""));
    }

    private static <T extends Query<?>> Class<? extends QueryHandler<?, T>> getHandlerClass(T query) {
        try {
            String handlerClassName = query.getClass().getName() + "Handler";
            @SuppressWarnings("unchecked")
            Class<? extends QueryHandler<?, T>> handlerClass = (Class<? extends QueryHandler<?, T>>)
                    Class.forName(handlerClassName);
            return handlerClass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Handler class not found for the message \""
                    + query.getClass().getName() + "\"", e);
        }
    }
}