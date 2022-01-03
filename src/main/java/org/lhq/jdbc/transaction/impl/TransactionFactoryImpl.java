package org.lhq.jdbc.transaction.impl;

import org.lhq.jdbc.transaction.Transaction;
import org.lhq.jdbc.transaction.TransactionFactory;
import org.lhq.jdbc.transaction.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

public class TransactionFactoryImpl implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(ds, level, autoCommit);
    }
}
