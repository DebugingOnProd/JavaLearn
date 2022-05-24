package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.IDataSource;

import java.sql.Connection;
@Slf4j
public class DisasterRecoveryDataSourceImpl implements IDataSource {
    @Override
    public Connection getConnection() {
        log.info("容灾数据机房的数据源");
        return null;
    }
}
