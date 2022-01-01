package org.lhq.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.DbConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Wallace
 */
@Slf4j
public class DbUtils {
    public static final String DB_CONFIG = "db.properties";
    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CONNECTIONS = "connections";
    public static final String DAO = "dao";

    public static DbConfig loadDbConfig(){
        log.info("加载数据库配置");
        Properties properties = new Properties();
        File file = new File(DB_CONFIG);
        log.info("当前目录是:{}",file.getAbsolutePath());
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            properties.load(fileInputStream);
            DbConfig dbConfig = new DbConfig();
            String driver = properties.getProperty(DRIVER);
            String url = properties.getProperty(URL);
            String username = properties.getProperty(USERNAME);
            String password = properties.getProperty(PASSWORD);
            String connections = properties.getProperty(CONNECTIONS);
            String dao = properties.getProperty(DAO);
            dbConfig.setDriver(driver);
            dbConfig.setUrl(url);
            dbConfig.setUsername(username);
            dbConfig.setPassword(password);
            dbConfig.setConnections(Integer.parseInt(connections));
            log.info("加载完毕");
            return dbConfig;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }

    }
}
