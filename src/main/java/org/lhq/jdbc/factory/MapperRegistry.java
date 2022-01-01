package org.lhq.jdbc.factory;

import lombok.extern.slf4j.Slf4j;
import org.lhq.jdbc.config.Config;
import org.lhq.jdbc.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class MapperRegistry {
    private final Config config;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Config config) {
        this.config = config;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSessioon) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("类型未知");
        }
        return mapperProxyFactory.newInstance(sqlSessioon);
    }


    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> void addMapper(Class<T> type) {
        log.debug("添加Mapper映射{}",type);
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("类型已经纯在");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(type, new MapperProxyFactory<>(type));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }
}
