package org.lhq.jdbc.executor.impl;

import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.executor.Executor;
import org.lhq.jdbc.transaction.Transaction;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public abstract class BaseExecutor implements Executor {
    protected Config config;
    protected Transaction transaction;
    protected Executor executor;
    protected boolean closed;
    protected BaseExecutor(Config config, Transaction transaction) {
        this.config = config;
        this.transaction = transaction;
        this.executor = this;
        this.closed = false;
    }

    @Override
    public Transaction getTransaction() {
        if (closed){
            throw new RuntimeException("食物已经关闭");
        }
        return transaction;
    }

    @Override
    public <E> List<E> query() throws SQLException {
        return Collections.emptyList();
    }
}
