package org.lhq.orm.session;

import org.lhq.orm.binding.MappedStatement;
import org.lhq.orm.binding.MapperRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type){
       return mapperRegistry.getMapper(type,null);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }
    public MappedStatement getMappedStatement(String statement){
        return mappedStatements.get(statement);
    }
}
