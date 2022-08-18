package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.DBDriver;
@Slf4j
public class PostgreSqlDriver implements DBDriver {
    @Override
    public void getConnection() {
        log.info("获取PostgreSql链接");
    }
}
