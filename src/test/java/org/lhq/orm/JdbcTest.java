package org.lhq.orm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.entity.User;
import org.lhq.jdbc.ConnectionPool;
import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.dao.UserDao;
import org.lhq.jdbc.mapping.MapperScan;
import org.lhq.jdbc.session.SqlSession;
import org.lhq.jdbc.session.SqlSessionFactory;
import org.lhq.jdbc.session.SqlSessionFactoryBuilder;

import java.sql.Connection;
import java.util.Set;

@Slf4j
class JdbcTest {
    @Test
    void getConnit(){
        Config config = new Config();
        Set<Class<?>> classes = MapperScan.mapperScan("org.lhq.jdbc.dao");
        classes.forEach(config::addMapper);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(config);
        Connection connection = ConnectionPool.getConnection();
        SqlSession sqlSession = build.openSession(connection);
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.selectOne();
        log.info(String.valueOf(user));

    }
}
