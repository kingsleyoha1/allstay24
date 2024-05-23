package com.kingsleyohas.allstay24backoffice.domain.bus;


public interface QueryHandler<T, Q extends Query<T>> extends MessageHandler {
    T handle(Q query);
}