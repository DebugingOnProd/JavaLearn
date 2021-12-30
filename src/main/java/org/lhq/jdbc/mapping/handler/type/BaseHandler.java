package org.lhq.jdbc.mapping.handler.type;

import cn.hutool.db.meta.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseHandler<T> {
    void setParameter(PreparedStatement preparedStatement,int i, T parameter, JdbcType jdbcType);

    T getResult(ResultSet resultSet, String columnName);

    T getResult(ResultSet rs, int columnIndex) throws SQLException;

    T getResult(CallableStatement cs, int columnIndex) throws SQLException;

}
