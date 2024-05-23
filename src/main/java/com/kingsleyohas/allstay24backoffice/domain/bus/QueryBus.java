package com.kingsleyohas.allstay24backoffice.domain.bus;

public interface QueryBus {
    <T> T ask(Query<T> query);
}