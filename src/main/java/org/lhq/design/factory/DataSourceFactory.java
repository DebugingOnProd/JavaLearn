package org.lhq.design.factory;

import cn.hutool.db.ds.pooled.ConnectionWraper;
import cn.hutool.db.ds.pooled.PooledConnection;
import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceFactory extends AbstractFactory{
    @Override
    DBDriver getDbDriver(DbEnum dbEnum) {
        return null;
    }

    @Override
    Connection getDb(DataSource dataSource) throws SQLException {
        if (dataSource==null){
            return null;
        }
        Connection connection = null;
        switch (dataSource){
            case SameCity -> connection = new PooledConnection(null);
            case DisasterRecovery -> connection = new PooledConnection(null);
        }
        return connection;
    }
}
