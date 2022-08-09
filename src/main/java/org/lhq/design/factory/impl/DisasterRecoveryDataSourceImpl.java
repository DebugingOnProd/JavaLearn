package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.IDataSource;

@Slf4j
public class DisasterRecoveryDataSourceImpl implements IDataSource {
    @Override
    public String getConnection() {
        log.info("容灾数据机房的数据源");
        return "容灾数据机房的数据源";
    }
}
