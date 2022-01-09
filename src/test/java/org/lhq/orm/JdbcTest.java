package org.lhq.orm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.entity.User;
import org.lhq.jdbc.ConnectionPool;
import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.dao.UserDao;
import org.lhq.jdbc.mapping.BeanScan;
import org.lhq.jdbc.session.SqlSession;
import org.lhq.jdbc.session.SqlSessionFactory;
import org.lhq.jdbc.session.SqlSessionFactoryBuilder;

import java.sql.Connection;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
class JdbcTest {
    @Test
    void getConnit() throws InterruptedException {
        Config config = new Config();
        Set<Class<?>> classes = BeanScan.mapperScan("org.lhq.jdbc.dao");
        classes.forEach(config::addMapper);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(config);
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Future<?> future = null;
        for (int i = 0; i < 200; i++) {
            future = executorService.submit(() -> {
                Connection connection = ConnectionPool.getConnection();
                SqlSession sqlSession = build.openSession(connection);
                UserDao mapper = sqlSession.getMapper(UserDao.class);
                User user = mapper.selectOne();
                ConnectionPool.returnConnection(connection);
                log.info(String.valueOf(user));
            });
        }


        while (true){
            log.info("任务是否结束{}",future.isDone());
            TimeUnit.SECONDS.sleep(1);
            if (future.isDone()){
                ConnectionPool.closeAllConnection();
                break;
            }
        }



    }


}
