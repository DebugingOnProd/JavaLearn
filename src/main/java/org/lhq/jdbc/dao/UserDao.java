package org.lhq.jdbc.dao;

import org.lhq.anno.orm.Select;
import org.lhq.entity.User;

import java.util.List;

public interface UserDao {
    @Select(sql = "select * from wd_user where id = 1")
    User selectOne();
    @Select(sql = "select * from wd_user")
    List<User> selectUserList();
}
