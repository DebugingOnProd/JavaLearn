package org.lhq.orm;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.dao.IUserDao;
import org.lhq.orm.binding.MapperProxyFactory;
import org.lhq.orm.binding.MapperRegistry;
import org.lhq.orm.session.DefaultSqlSessionFactory;
import org.lhq.orm.session.SqlSession;
import org.lhq.orm.session.SqlSessionFactory;



@Slf4j
class JdbcTest {
    @Test
    void getConnit() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("org.lhq.dao");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String res = userDao.queryUserName("10001");
        sqlSession.selectOne("sssss");
        log.info("测试结果：{}", res);
    }


}
