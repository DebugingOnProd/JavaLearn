package org.lhq.design.factory;


import org.lhq.design.factory.impl.DisasterRecoveryDataSourceImpl;
import org.lhq.design.factory.impl.SameCitySourceImpl;
import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;

import java.sql.Connection;

public class DataSourceFactory extends AbstractFactory{
    @Override
    public DBDriver getDbDriver(DbEnum dbEnum) {
        return null;
    }

    @Override
    public IDataSource getDb(DataSource dataSource) {
        if (dataSource==null){
            return null;
        }
        IDataSource dataSourceConnection = null;
        switch (dataSource){
            case SameCity -> dataSourceConnection = new SameCitySourceImpl();
            case DisasterRecovery -> dataSourceConnection = new DisasterRecoveryDataSourceImpl();
        }
        return dataSourceConnection;
    }
}
