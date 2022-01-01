package org.lhq.jdbc.executor.impl;

import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.transaction.Transaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class SimpleExecutor extends BaseExecutor{
    public SimpleExecutor(Config configuration, Transaction transaction) {
        super(configuration, transaction);
    }



    public <E> List<E> doQuery() {
        return Collections.emptyList();
    }


    @Override
    public void commit(boolean required) throws SQLException {

    }

    @Override
    public void rollback(boolean required) throws SQLException {

    }

    @Override
    public void close(boolean forceRollback) {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void clearLocalCache() {

    }
}
