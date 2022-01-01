package org.lhq.jdbc.session.impl;

import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.executor.Executor;
import org.lhq.jdbc.session.SqlSession;
import org.lhq.jdbc.session.SqlSessionFactory;
import org.lhq.jdbc.transaction.Transaction;
import org.lhq.jdbc.transaction.TransactionFactory;
import org.lhq.jdbc.transaction.impl.TransactionFactoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlSessionFactoryImpl implements SqlSessionFactory {

    private final Config configuration;

    public SqlSessionFactoryImpl(Config configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return null;
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return null;
    }

    @Override
    public SqlSession openSession(Connection connection) {
        return openSqlSeeionFromConnection(connection);
    }

    @Override
    public Config getConfiguration() {
        return configuration;
    }


    private SqlSession openSqlSeeionFromConnection(Connection connection) {
        boolean autoCommit;
        try {
            autoCommit = connection.getAutoCommit();

        } catch (SQLException e) {
            autoCommit = true;
        }
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        Transaction transaction = transactionFactory.newTransaction(connection);
        Executor executor = configuration.newExecutor(transaction);
        return new SqlSessionImpl(configuration, executor, autoCommit);
    }
}
