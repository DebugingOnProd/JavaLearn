package org.lhq.orm.session;

import lombok.AllArgsConstructor;
import org.lhq.orm.binding.MappedStatement;
@AllArgsConstructor
public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;


    @Override
    public <T> T selectOne(String statement) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);

        return (T) ("你被代理了！" + "方法：" + statement );
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.mapperRegistry.getMapper(type, this);
    }
}
