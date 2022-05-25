package org.lhq.design.factory;

import cn.hutool.db.ds.pooled.ConnectionWraper;
import cn.hutool.db.ds.pooled.PooledConnection;
import cn.hutool.db.ds.pooled.PooledDataSource;
import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceFactory extends AbstractFactory{
    @Override
    public DBDriver getDbDriver(DbEnum dbEnum) {
        return null;
    }

    @Override
    public Connection getDb(DataSource dataSource) throws SQLException {
        if (dataSource==null){
            return null;
        }
        Connection connection = null;
        switch (dataSource){
            case SameCity, DisasterRecovery -> {
            }
        }
        return connection;
    }
}
