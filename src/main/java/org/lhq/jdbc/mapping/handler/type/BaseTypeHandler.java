package org.lhq.jdbc.mapping.handler.type;

import cn.hutool.db.meta.JdbcType;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  abstract class BaseTypeHandler<T> extends TypeReference<T> implements BaseHandler<T> {
    @Override
    public void setParameter(PreparedStatement preparedStatement,int i, T parameter, JdbcType jdbcType) {
        if (parameter == null) {
            if (jdbcType == null) {
                throw new RuntimeException("JDBC 要求必须为所有可为空的参数指定 JdbcType.");
            }
            try {
                preparedStatement.setNull(i, jdbcType.typeCode);
            } catch (SQLException e) {
                throw new RuntimeException("为参数设置 null 时出错 #" + i + " with JdbcType " + jdbcType + " . "
                        + "尝试为此参数设置不同的 JdbcType 或不同的 jdbcTypeForNull 配置属性. "
                        + "Cause: " + e, e);
            }
        } else {
            try {
                setNonNullParameter(preparedStatement, i, parameter, jdbcType);
            } catch (Exception e) {
                throw new RuntimeException("Error setting non null for parameter #" + i + " with JdbcType " + jdbcType + " . "
                        + "Try setting a different JdbcType for this parameter or a different configuration property. "
                        + "Cause: " + e, e);
            }
        }
    }

    @Override
    public T getResult(ResultSet resultSet, String columnName) {
        return null;
    }

    @Override
    public T getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public T getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }




    public abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

    /**
     * Gets the nullable result.
     *
     * @param rs
     *          the rs
     * @param columnName
     *          Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
     * @return the nullable result
     * @throws SQLException
     *           the SQL exception
     */
    public abstract T getNullableResult(ResultSet rs, String columnName) throws SQLException;

    public abstract T getNullableResult(ResultSet rs, int columnIndex) throws SQLException;

    public abstract T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException;
}
