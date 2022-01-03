package org.lhq.jdbc.session.impl;

import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.executor.ErrorContext;
import org.lhq.jdbc.executor.Executor;
import org.lhq.jdbc.session.SqlSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SqlSessionImpl implements SqlSession {

    private final Config configuration;


    private final Executor executor;

    private final boolean autoCommit;

    private boolean dirty;

    public SqlSessionImpl(Config configuration, Executor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.dirty = false;
        this.autoCommit = autoCommit;
    }

    @Override
    public <T> T selectOne(String statement) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Object> query = executor.query(statement, null, null);
        if (query.size()>1) {
            throw new RuntimeException("结果太多了");
        }
        return (T)query.get(0);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter,Class<?> returnType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Object> query = executor.query(statement, parameter, returnType);
        if (query.size()>1) {
            throw new RuntimeException("结果太多了");
        }
        return (T)query.get(0);
    }

    @Override
    public <E> List<E> selectList(String statement) {
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Connection getConnection() {
        try {
            return executor.getTransaction().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a new connection.  Cause: " + e, e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            executor.close(isCommitOrRollbackRequired(false));
            dirty = false;
        } finally {
            ErrorContext.instance().reset();
        }
    }

    private boolean isCommitOrRollbackRequired(boolean force) {
        return (!autoCommit && dirty) || force;
    }
}
