package org.lhq.design.factory;


import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;

import java.sql.Connection;

public class DataSourceFactory extends AbstractFactory{
    @Override
    public DBDriver getDbDriver(DbEnum dbEnum) {
        return null;
    }

    @Override
    public Connection getDb(DataSource dataSource) {
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
