package org.lhq.jdbc.session;

import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.session.impl.SqlSessionFactoryImpl;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(Config config){
        return new SqlSessionFactoryImpl(config);
    }
}
