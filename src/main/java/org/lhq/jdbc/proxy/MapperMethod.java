package org.lhq.jdbc.proxy;

import org.lhq.jdbc.session.SqlSession;

public class MapperMethod {
        public Object execute(SqlSession sqlSession,Object[] args){
                Object reult;
                reult = sqlSession.selectOne("select",args);

                return reult;
        }
}
