package org.lhq.orm;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
class JdbcTest {
	@Test
	void getConnit() throws InterruptedException {
		Config config = new Config();
		Set<Class<?>> classes = BeanScan.mapperScan("org.lhq.jdbc.dao");
		classes.forEach(config::addMapper);
		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(config);
		ExecutorService executorService = Executors.newFixedThreadPool(100);

		ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
		ListenableFuture<User> future = null;
		for (int i = 0; i < 100; i++) {
			future = listeningExecutorService.submit(() -> {
				TimeInterval timer = DateUtil.timer();
				Connection connection = ConnectionPool.getConnection();
				SqlSession sqlSession = build.openSession(connection);
				UserDao mapper = sqlSession.getMapper(UserDao.class);
				User user = mapper.selectOne();
				ConnectionPool.returnConnection(connection);
				long time = timer.intervalRestart();
				log.info("执行耗时:{}毫秒", time);
				return user;
			});
			Futures.addCallback(future, new FutureCallback<User>() {
				@Override
				public void onSuccess(User result) {
					log.info(">>>>>{}", result);
				}

				@Override
				public void onFailure(Throwable t) {
					log.error(String.valueOf(t));
				}
			}, listeningExecutorService);

		}
		while (true) {
			log.info("任务是否结束{}", future.isDone());
			TimeUnit.SECONDS.sleep(1);
			if (future.isDone()) {
				ConnectionPool.closeAllConnection();
				break;
			}
		}

	}


}
