package org.lhq.jdbc.proxy;

import lombok.extern.slf4j.Slf4j;
import org.lhq.jdbc.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@Slf4j
public class MapperMethod {
        public Object execute(SqlSession sqlSession,Object[] args,String sql,Class<?> returnType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
                log.debug("执行到了着这里");
                return sqlSession.selectOne(sql,args,returnType);
        }
}
