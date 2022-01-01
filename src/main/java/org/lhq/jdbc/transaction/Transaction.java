package org.lhq.jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction {
    Connection getConnection() throws SQLException;

    /**
     * Commit inner database connection.
     * @throws SQLException
     *           the SQL exception
     */
    void commit() throws SQLException;

    /**
     * Rollback inner database connection.
     * @throws SQLException
     *           the SQL exception
     */
    void rollback() throws SQLException;

    /**
     * Close inner database connection.
     * @throws SQLException
     *           the SQL exception
     */
    void close() throws SQLException;

    /**
     * Get transaction timeout if set.
     *
     * @return the timeout
     * @throws SQLException
     *           the SQL exception
     */
    Integer getTimeout() throws SQLException;
}
