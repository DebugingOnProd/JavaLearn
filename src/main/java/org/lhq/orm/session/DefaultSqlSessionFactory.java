package org.lhq.orm.session;

import org.lhq.orm.binding.MapperRegistry;

public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
