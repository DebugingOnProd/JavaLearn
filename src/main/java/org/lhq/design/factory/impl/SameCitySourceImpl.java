package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.IDataSource;

import java.sql.Connection;
@Slf4j
public class SameCitySourceImpl implements IDataSource {
    @Override
    public Connection getConnection() {
        log.info("同城数据库机房的数据源");
        return null;
    }
}
