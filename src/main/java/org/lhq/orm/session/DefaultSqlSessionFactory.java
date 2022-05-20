package org.lhq.orm.session;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    public DefaultSqlSessionFactory() {
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession();
    }
}
