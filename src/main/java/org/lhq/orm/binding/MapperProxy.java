package org.lhq.orm.binding;

import org.lhq.orm.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

    public class MapperProxy<T> implements InvocationHandler {
    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
