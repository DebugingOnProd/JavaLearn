package org.lhq.jdbc.mapping.handler.resultset.impl;

import org.lhq.jdbc.mapping.handler.resultset.ResultSetHandler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DefaultResultHandler implements ResultSetHandler {
    private final

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        ResultSet reultSet = getReultSet(stmt);

        return null;
    }

    @Override
    public void handleOutputParameters(CallableStatement cs) throws SQLException {

    }

    private ResultSet getReultSet(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getResultSet();
        while (resultSet == null){
            if (statement.getMoreResults()) {
                resultSet =statement.getResultSet();
            }else {
                if (statement.getUpdateCount()==-1) {
                    break;
                }
            }
        }
        return resultSet;
    }
}
