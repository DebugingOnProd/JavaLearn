package org.lhq.orm.session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultSqlSession implements SqlSession{


    /**
     * 映射器注册机
     */


    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement );
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);

    }

    @Override
    public <T> T getMapper(Class<T> type) {
		return null;
    }
}
