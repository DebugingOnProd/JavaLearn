package org.lhq.jdbc.executor.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.executor.Executor;
import org.lhq.jdbc.mapping.ResultSetToEntity;
import org.lhq.jdbc.transaction.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
            throw new RuntimeException("事务已经关闭");
        }
        return transaction;
    }

    @Override
    public <E> List<E> query(String sql, Object param, Class<?> returnType) throws SQLException, ClassNotFoundException {
        ArrayList<E> list = Lists.newArrayList();
        Connection connection = transaction.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return ResultSetToEntity.rsMapToEntityList(returnType, resultSet, list);
    }
}
