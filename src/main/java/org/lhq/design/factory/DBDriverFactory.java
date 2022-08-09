package org.lhq.design.factory;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.impl.MySqlDriver;
import org.lhq.design.factory.impl.OracleDriver;
import org.lhq.design.factory.impl.PostgreSqlDriver;
import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;

import java.sql.Connection;

@Slf4j
public class DBDriverFactory extends AbstractFactory{
    @Override
    public DBDriver getDbDriver(DbEnum dbEnum){
        if (dbEnum ==null){
            log.warn("数据类型为空");
            return null;
        }
        DBDriver dbDriver = null;
        switch (dbEnum){
            case MySql -> dbDriver = new MySqlDriver();
            case Oracle -> dbDriver = new OracleDriver();
            case PostgreSql -> dbDriver = new PostgreSqlDriver();
        }
        return dbDriver;
    }

    @Override
    public IDataSource getDb(DataSource dataSource) {
        return null;
    }


}
