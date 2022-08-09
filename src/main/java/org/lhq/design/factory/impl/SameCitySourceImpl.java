package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.IDataSource;

@Slf4j
public class SameCitySourceImpl implements IDataSource {
    @Override
    public String getConnection() {
        log.info("同城数据库机房的数据源");
        return "同城数据库机房的数据源";
    }
}
