package org.lhq.design.factory;

import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractFactory {
    public abstract DBDriver getDbDriver(DbEnum dbEnum);
    public abstract Connection getDb(DataSource dataSource) throws SQLException;
}
