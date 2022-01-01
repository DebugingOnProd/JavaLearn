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

    private static ArrayBlockingQueue<Connection> SOURCE_POOL  = new ArrayBlockingQueue<>(100);
    static {
        // 类加载时初始化数据库连接池
        initPool();
    }

    private static void initPool(){
        log.info("初始化连接池");
        DbConfig dbConfig = DbUtils.loadDbConfig();
        int connections = dbConfig.getConnections();
        for (int i = 0; i < connections; i++) {
            log.debug("正在初始化第{}个,共{}个",i,connections);
            Connection connection = initConnection(dbConfig);
            SOURCE_POOL.add(connection);
        }
        log.info("初始化结束");

    }

    private static Connection initConnection(DbConfig dbConfig){
        return JdbcUtil.getConnection(dbConfig);
    }

    public static Connection getConnection(){
        log.info("获取链接");
        try {
            return SOURCE_POOL.take();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static void returnConnection(Connection connection){
        log.info("归还链接");
        try {
            SOURCE_POOL.put(connection);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
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
                log.info("成功释放第{}/{}链接,耗时{}毫秒,总耗时{}毫秒",count++,size,singleTime,totalTimer.interval());
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

}
