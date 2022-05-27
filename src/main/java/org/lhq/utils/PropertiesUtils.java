package org.lhq.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesUtils {
    private static Properties properties;

    private PropertiesUtils(){

    }

    static {
        try {
            properties = new Properties();
            properties.load(PropertiesUtils.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            log.info("获取配置文件出错",e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
