package org.lhq.jdbc.session;

import org.lhq.jdbc.config.Config;

import java.sql.Connection;

public interface SqlSessionFactory {
    SqlSession openSession();

    SqlSession openSession(boolean autoCommit);

    SqlSession openSession(Connection connection);
    Config getConfiguration();

}
