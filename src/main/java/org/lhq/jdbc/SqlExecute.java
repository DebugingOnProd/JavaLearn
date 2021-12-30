package org.lhq.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class SqlExecute {
    public Statement selectStatement(String sql){
        Connection connection = ConnectionPool.getConnection();
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        return  preparedStatement;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }finally {
            ConnectionPool.returnConnection(connection);
        }
    }
}
