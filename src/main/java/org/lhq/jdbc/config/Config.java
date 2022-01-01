package org.lhq.jdbc.config;

import org.lhq.jdbc.executor.Executor;
import org.lhq.jdbc.executor.impl.SimpleExecutor;
import org.lhq.jdbc.factory.MapperRegistry;
import org.lhq.jdbc.session.SqlSession;
import org.lhq.jdbc.transaction.Transaction;

public class Config {
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this,transaction);
    }
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }
    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

}
