package org.lhq.jdbc;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.DbConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

@Slf4j
public class ConnectionPool {

    private ConnectionPool(){}

    private static ArrayBlockingQueue<Connection> SOURCE_POOL  = new ArrayBlockingQueue<>(100);
    static {
        // 类加载时初始化数据库连接池
        initPool();
    }

    private static void initPool(){
        log.info("初始化连接池");
        DbConfig dbConfig = DbUtils.loadDbConfig();
		TimeInterval total = DateUtil.timer();
		int connections = dbConfig.getConnections();
        for (int i = 1; i <= connections;++i) {
			TimeInterval timer = DateUtil.timer();
            Connection connection = initConnection(dbConfig);
            SOURCE_POOL.add(connection);
			log.debug("正在初始化第{}个,共{}个,耗时{}毫秒,总耗时{}毫秒",i,connections,timer.intervalRestart(),total.interval());
		}
        log.info("初始化结束");

    }

    private static Connection initConnection(DbConfig dbConfig){
        return JdbcUtil.getConnection(dbConfig);
    }

    public static  Connection getConnection(){
        if (SOURCE_POOL.isEmpty()){
            log.info("没有连接了呢！请耐心守候");
        }
        try {
			Connection connection = SOURCE_POOL.take();
			log.info("获取链接,连接池中剩余{}个链接",SOURCE_POOL.size());
			return connection;
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public static void returnConnection(Connection connection){
        try {
            SOURCE_POOL.put(connection);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.info("归还链接,连接池中剩余{}个链接",SOURCE_POOL.size());
    }

    public static void closeAllConnection(){
        closeAllConnection(SOURCE_POOL);
    }

    public static void closeAllConnection(ArrayBlockingQueue<Connection> pool){
        TimeInterval totalTimer = DateUtil.timer();
        int size = pool.size();
        int count = 0;
        while (true){
            TimeInterval single = DateUtil.timer();
            Connection poll = pool.poll();
            if (poll == null) {
                break;
            }
            try {
                poll.close();
                long singleTime = single.intervalRestart();
                log.info("成功释放第{}/{}链接,耗时{}毫秒,总耗时{}毫秒",++count,size,singleTime,totalTimer.interval());
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

}
