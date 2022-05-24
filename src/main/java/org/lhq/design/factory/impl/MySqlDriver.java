package org.lhq.design.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.DBDriver;

@Slf4j
public class MySqlDriver implements DBDriver {
    @Override
    public void getConnection() {
        log.info("获取MySQL链接");
    }
}
