package org.lhq.design.agent.dao;

import org.lhq.anno.orm.Select;

public interface UserDao {
    @Select(sql = "select * from wb_user where user_id = #{userId}")
    void queryUserById(Long userId);
}
